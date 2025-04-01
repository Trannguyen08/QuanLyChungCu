package main.java.com.utc2.apartmentManage.repository.managerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.utc2.apartmentManage.databaseConnect.ConnectDB;
import main.java.com.utc2.apartmentManage.model.Resident;



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

    
    public int checkDuplicateResident(Resident resident) {
        String sql = "SELECT email, phoneNum, id_card FROM residents WHERE email = ? OR phoneNum = ? OR id_card = ?";
        
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, resident.getEmail());
            stmt.setString(2, resident.getPhoneNumber());
            stmt.setString(3, resident.getIdCard());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("email").equals(resident.getEmail())) {
                    return 1;
                }
                if (rs.getString("phoneNum").equals(resident.getPhoneNumber())) {
                    return 2;
                }
                if (rs.getString("id_card").equals(resident.getIdCard())) {
                    return 3;
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

}

