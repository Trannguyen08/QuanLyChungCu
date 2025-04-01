package main.java.com.utc2.apartmentManage.repository.managerRepository;


import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import main.java.com.utc2.apartmentManage.databaseConnect.ConnectDB;
import main.java.com.utc2.apartmentManage.model.Contract;

public class contractRepository {

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
        String query = "SELECT ct.contract_id, r.full_name, " +
                "CONCAT(ap.apartmentIndex, '-', ap.floor, '-', ap.building) AS indexs, " +
                "ct.contract_type, ct.start_date, ct.end_date, ct.contract_value, ct.contract_status " +
                "FROM contracts ct " +
                "JOIN apartments ap ON ct.apartment_id = ap.apartment_id " +
                "JOIN residents r ON ct.resident_id = r.resident_id";

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
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getLong("contract_value"),
                        rs.getString("contract_status")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contractList;
    }
    
    public List<Contract> getFilteredContracts(Contract contract, JDateChooser startDate, 
                                           JDateChooser endDate, double fromValue, double toValue) {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT c.contract_id, r.full_name AS resident_name, " +
                     "a.apartmentIndex, a.floor, a.building, " +
                     "c.contract_type, c.start_date, c.end_date, c.contract_value, c.contract_status " +
                     "FROM contracts c " +
                     "JOIN residents r ON c.resident_id = r.resident_id " +
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

        // Lọc theo khoảng ngày từ JDateChooser
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (startDate.getDate() != null) {
            sql += " AND c.start_date >= ?";
            parameters.add(dateFormat.format(startDate.getDate()));
        }
        if (endDate.getDate() != null) {
            sql += " AND c.end_date <= ?";
            parameters.add(dateFormat.format(endDate.getDate()));
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
                    rs.getString("start_date"),
                    rs.getString("end_date"),
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
    
    
    public List<Contract> getFilteredContractsByKeyword(String keyword) {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT c.contract_id, r.full_name AS resident_name, " +
                     "a.apartmentIndex, a.floor, a.building, " +
                     "c.contract_type, c.start_date, c.end_date, c.contract_value, c.contract_status " +
                     "FROM contracts c " +
                     "JOIN residents r ON c.resident_id = r.resident_id " +
                     "JOIN apartments a ON c.apartment_id = a.apartment_id " +
                     "WHERE c.contract_id LIKE ? OR r.full_name LIKE ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%"); 
            pstmt.setString(2, "%" + keyword + "%"); 

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
                    rs.getString("start_date"),
                    rs.getString("end_date"),
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
