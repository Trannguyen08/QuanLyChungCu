
package dao.managerDAO;

import DatabaseConnect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Resident;

/**
 *
 * @author nghia
 */
public class ResidentDAO {
 private int getRowCount() {
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
        String sql = "UPDATE residents SET full_name = ?, phone_number = ?, email = ?, " +
                     "id_card = ?, date_of_birth = ?, gender = ?, apartment_id = ? WHERE resident_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, resident.getName());
            pstmt.setString(2, resident.getPhoneNumber());
            pstmt.setString(3, resident.getEmail());
            pstmt.setString(4, resident.getIdCard());
            pstmt.setString(5, resident.getBirthDate());
            pstmt.setString(6, resident.getGender());
            pstmt.setInt(7, resident.getApartmentID());
            pstmt.setInt(8, resident.getResidentID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật cư dân: " + e.getMessage());
        }
        return false; 
    }

    public boolean addResident(Resident resident) {
        String sql = "INSERT INTO residents (full_name, phone_number, email, " +
                "id_card, date_of_birth, gender, apartment_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, resident.getName());
            pstmt.setString(2, resident.getPhoneNumber());
            pstmt.setString(3, resident.getEmail());
            pstmt.setString(4, resident.getIdCard());
            pstmt.setString(5, resident.getBirthDate());
            pstmt.setString(6, resident.getGender());
            pstmt.setInt(7, resident.getApartmentID());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm cư dân: " + e.getMessage());
        }
        return false;
    }
    
    public boolean deleteResident(int id) {
        String sql = "DELETE FROM residents WHERE apartment_id = ?";

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

    public Object[] getLastRow() {
        String query = "SELECT * FROM residents ORDER BY resident_id DESC LIMIT 1";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if (res.next()) {
                return new Object[]{
                        res.getInt("resident_id"),
                        res.getString("full_name"),
                        res.getString("phone_number"),
                        res.getString("email"),
                        res.getString("id_card"),
                        res.getString("date_of_birth"),
                        res.getDouble("gender"),
                        res.getInt("apartment_id")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDataToTable(JTable table) {
        String query = "SELECT * FROM residents";
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while( res.next() ) {
                model.addRow( new Object[]{
                        res.getInt("resident_id"),
                        res.getString("full_name"),
                        res.getString("phone_number"),
                        res.getString("email"),
                        res.getString("id_card"),
                        res.getString("date_of_birth"),
                        res.getDouble("gender"),
                        res.getInt("apartment_id")}
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}

