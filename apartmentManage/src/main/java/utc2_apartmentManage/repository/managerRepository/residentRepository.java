package main.java.utc2_apartmentManage.repository.managerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.utc2_apartmentManage.databaseConnect.ConnectDB;
import main.java.utc2_apartmentManage.model.Resident;



public class residentRepository {
    public int getRowCount() {
        String query = "SELECT COUNT(*) FROM residents";
        int count = 0;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if( res.next() ) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public boolean updateResident(Resident resident) {
        String sql = "UPDATE residents SET apartment_id = ?,full_name = ?, phoneNum = ?, email = ?, " +
                     "id_card = ?, date_of_birth = ?, gender = ? WHERE resident_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, resident.getApartmentID());
            pstmt.setString(2, resident.getName());
            pstmt.setString(3, resident.getPhoneNumber());
            pstmt.setString(4, resident.getEmail());
            pstmt.setString(5, resident.getIdCard());
            pstmt.setDate(6, resident.getBirthDate());
            pstmt.setString(7, resident.getGender());
            pstmt.setInt(8, resident.getResidentID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật cư dân: " + e.getMessage());
        }
        return false; 
    }
    
    public boolean deleteResident(int id) {
        String sql = "DELETE FROM residents WHERE resident_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa cư dân: " + e.getMessage());
        }
        return false;
    }

    public List<Resident> getAllResident() {
        String query = "SELECT * FROM residents";
        List<Resident> residentList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while( res.next() ) {
                residentList.add( new Resident(
                        res.getInt("resident_id"),
                        res.getString("full_name"),
                        res.getString("gender"),
                        res.getDate("date_of_birth"),
                        res.getString("phoneNum"),
                        res.getString("email"),
                        res.getString("id_card"),
                        res.getInt("apartment_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return residentList;
    }
    
    public List<Resident> getFilterResidentWithKeyword(String keyword) {
        List<Resident> residentList = new ArrayList<>();
        String sql = "SELECT * FROM residents WHERE resident_id LIKE ? OR full_name LIKE ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%"); 
            pstmt.setString(2, "%" + keyword + "%"); 

            ResultSet res = pstmt.executeQuery();
            while( res.next() ) {
                residentList.add(new Resident(
                    res.getInt("resident_id"),
                        res.getString("full_name"),
                        res.getString("gender"),
                        res.getDate("date_of_birth"),
                        res.getString("phoneNum"),
                        res.getString("email"),
                        res.getString("id_card"),
                        res.getInt("apartment_id")));
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return residentList;
    }

    
    public int isDuplicateResident(Resident resident) {
        String sql = "SELECT * FROM residents "
               + "WHERE (apartment_id = ? OR email = ? OR phoneNum = ? OR id_card = ?) "
               + "AND resident_id <> ?";  

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, resident.getApartmentID());
            stmt.setString(2, resident.getEmail());
            stmt.setString(3, resident.getPhoneNumber());
            stmt.setString(4, resident.getIdCard());
            stmt.setInt(5, resident.getResidentID());  

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Kiểm tra trùng căn hộ
                if (rs.getInt("apartment_id") == resident.getApartmentID()) {
                    return 1;  
                }
                // Kiểm tra trùng email
                if (rs.getString("email").equals(resident.getEmail())) {
                    return 2; 
                }
                // Kiểm tra trùng số điện thoại
                if (rs.getString("phoneNum").equals(resident.getPhoneNumber())) {
                    return 3; 
                }
                // Kiểm tra trùng CCCD
                if (rs.getString("id_card").equals(resident.getIdCard())) {
                    return 4;  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean isStillContract(int id) {
        String query = "SELECT contract_status FROM contracts WHERE resident_id = ?";
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if(rs.getString("contract_status").equals("Hiệu lực")) {
                    return true;
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    public List<Resident> getAllFilterResident(Resident resident, Date toDate) {
        List<Resident> residentList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM residents WHERE 1=1");

        // Danh sách tham số để set vào câu truy vấn
        List<Object> params = new ArrayList<>();
        
        if (resident.getResidentID() > 0) {
            sql.append(" AND resident_id = ?");
            params.add(resident.getResidentID());
        }
        if (resident.getApartmentID() > 0) {
            sql.append(" AND apartment_id = ?");
            params.add(resident.getApartmentID());
        }
        if (resident.getName() != null && !resident.getName().trim().isEmpty()) {
            sql.append(" AND full_name LIKE ?");
            params.add("%" + resident.getName().trim() + "%");
        }
        if (resident.getEmail() != null && !resident.getEmail().trim().isEmpty()) {
            sql.append(" AND email = ?");
            params.add(resident.getEmail().trim());
        }
        if (resident.getPhoneNumber() != null && !resident.getPhoneNumber().trim().isEmpty()) {
            sql.append(" AND phoneNum = ?");
            params.add(resident.getPhoneNumber().trim());
        }
        if (resident.getIdCard() != null && !resident.getIdCard().trim().isEmpty()) {
            sql.append(" AND id_card = ?");
            params.add(resident.getIdCard().trim());
        }
        if (resident.getGender() != null && !resident.getGender().trim().isEmpty()) {
            sql.append(" AND gender = ?");
            params.add(resident.getGender().trim());
        }
        if (resident.getBirthDate() != null) {
            sql.append(" AND date_of_birth >= ?");
            params.add(new java.sql.Date(resident.getBirthDate().getTime()));
        }
        if (toDate != null) {
            sql.append(" AND date_of_birth <= ?");
            params.add(new java.sql.Date(toDate.getTime()));
        }

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            // Gán các tham số vào câu lệnh SQL
            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Resident res = new Resident(
                    rs.getInt("resident_id"),
                    rs.getString("full_name"),
                    rs.getString("gender"),
                    rs.getDate("date_of_birth"),
                    rs.getString("phoneNum"),
                    rs.getString("email"),
                    rs.getString("id_card"),
                    rs.getInt("apartment_id")
                );
                residentList.add(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return residentList;
    }


}

