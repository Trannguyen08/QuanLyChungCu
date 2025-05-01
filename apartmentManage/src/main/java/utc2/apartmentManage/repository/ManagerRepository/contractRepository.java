package main.java.utc2.apartmentManage.repository.ManagerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2.apartmentManage.model.Contract;
import main.java.utc2.apartmentManage.util.ConnectDB;
import main.java.utc2.apartmentManage.util.ScannerUtil;

public class contractRepository {
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.contract_id) + 1 AS next_id
                       FROM contracts a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM contracts a2 WHERE a2.contract_id = a1.contract_id + 1
                       );""";
        int ans = 0;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if( res.next() ) {
                ans = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
        
    }
    
    public boolean isNotValidContract(int id) {
        String query = "SELECT contract_status FROM contract WHERE contract_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String status =  rs.getString("contract_status");
                return status.equals("Hết hạn") || status.equals("Đã hủy");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addContract(Contract contract, int aptID, int resID) {
        String query = "INSERT INTO contracts(contract_id, apartment_id, resident_id, contract_type, "
                        + "start_date, end_date, contract_value, contract_status) "
                        + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            // Thiết lập các tham số theo thứ tự trong câu SQL
            ps.setInt(1, contract.getId());
            ps.setInt(2, aptID);
            ps.setInt(3, resID);
            ps.setString(4, contract.getContractType());
            ps.setString(5, ScannerUtil.convertDateFormat1(contract.getStartDate()));
            ps.setString(6, ScannerUtil.convertDateFormat1(contract.getEndDate()));
            ps.setDouble(7, contract.getContractValue());
            ps.setString(8, contract.getContractStatus());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteContract(int id) {
        String query = "DELETE FROM contracts WHERE contract_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)){

            ps.setInt(1, id);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Contract> getAllContract() {
        String query = "SELECT ct.contract_id, p.full_name, " +
               "CONCAT(ap.apartmentIndex, '-', ap.floor, '-', ap.building) AS indexs, " +
               "ct.contract_type, ct.start_date, ct.end_date, ct.contract_value, ct.contract_status " +
               "FROM contracts ct " +
               "JOIN apartments ap ON ct.apartment_id = ap.apartment_id " +
               "JOIN residents r ON ct.resident_id = r.resident_id " +
               "JOIN personal_info p ON r.person_id = p.person_id";  


        List<Contract> contractList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstm = con.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                contractList.add(new Contract(
                        rs.getInt("contract_id"),
                        rs.getString("full_name"),
                        rs.getString("indexs"),
                        rs.getString("contract_type"),
                        ScannerUtil.convertDateFormat2(rs.getString("start_date")),
                        ScannerUtil.convertDateFormat2(rs.getString("end_date")),
                        rs.getLong("contract_value"),
                        rs.getString("contract_status")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contractList;
    }
    
    public List<Contract> getFilteredContracts(Contract contract, String startDate, 
                                           String endDate, double fromValue, double toValue) {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT c.contract_id, p.full_name AS resident_name, " +
             "a.apartmentIndex, a.floor, a.building, " +
             "c.contract_type, c.start_date, c.end_date, c.contract_value, c.contract_status " +
             "FROM contracts c " +
             "JOIN residents r ON c.resident_id = r.resident_id " +
             "JOIN personal_info p ON r.person_id = p.person_id " +  // << thêm JOIN
             "JOIN apartments a ON c.apartment_id = a.apartment_id " +
             "WHERE 1=1";


        List<Object> parameters = new ArrayList<>();

        // Thêm điều kiện tìm kiếm từ `contract`
        if (contract.getId() > 0) {  
            sql += " AND c.contract_id = ?";
            parameters.add(contract.getId());
        }
        if (contract.getOwnerName() != null && !contract.getOwnerName().trim().isEmpty()) {
            sql += " AND r.full_name LIKE ?";
            parameters.add("%" + contract.getOwnerName().trim() + "%");
        }
        if (contract.getContractType() != null && !contract.getContractType().trim().isEmpty()) {
            sql += " AND c.contract_type = ?";
            parameters.add(contract.getContractType().trim());
        }
        if (contract.getContractStatus() != null && !contract.getContractStatus().trim().isEmpty()) {
            sql += " AND c.contract_status = ?";
            parameters.add(contract.getContractStatus().trim());
        }

        // Lọc theo khoảng giá trị hợp đồng
        if (fromValue >= 0 && toValue > 0) {  
            sql += " AND c.contract_value BETWEEN ? AND ?";
            parameters.add(fromValue);
            parameters.add(toValue);
        }


        if (startDate != null) {
            sql += " AND c.start_date >= ?";
            parameters.add(ScannerUtil.convertDateFormat1(startDate));
        }
        if (endDate != null) {
            sql += " AND c.end_date <= ?";
            parameters.add(ScannerUtil.convertDateFormat1(endDate));
        }

        // Kết nối DB và thực thi truy vấn
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String apartmentInfo = rs.getString("apartmentIndex") + " - " + 
                                       rs.getString("floor") + " - " + 
                                       rs.getString("building");
                contracts.add(new Contract(
                    rs.getInt("contract_id"),
                    rs.getString("resident_name"),  
                    apartmentInfo,  
                    rs.getString("contract_type"),
                    ScannerUtil.convertDateFormat2(rs.getString("start_date")),
                    ScannerUtil.convertDateFormat2(rs.getString("end_date")),
                    rs.getLong("contract_value"),  
                    rs.getString("contract_status")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    } 
}
