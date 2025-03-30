package dao.managerDAO;

import DatabaseConnect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Notification;

public class NotificationDAO {
    private int getRowCount(){
        String query = "SELECT COUNT(*) FROM notification";
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
    
    public boolean updateNotification(Notification notification){
        String sql = "UPDATE notification SET notification_id = ?,  message = ?, " +
                     "notification_type = ?, title = ?, WHERE recipient_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, notification.getID());
            pstmt.setString(2, notification.getMess());
            pstmt.setString(3, notification.getTitle());
            pstmt.setString(4, notification.getType());
            pstmt.setInt(5, notification.getOwnerID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật thông tin: " + e.getMessage());
        }
        return false; 
    }
    
    public boolean addNotification(Notification notification) {
        String sql = "UPDATE notification SET notification_id = ?,  message = ?, " +
                     "notification_type = ?, title = ?, WHERE recipient_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, notification.getID());
            pstmt.setString(2, notification.getMess());
            pstmt.setString(3, notification.getTitle());
            pstmt.setString(4, notification.getType());
            pstmt.setInt(5, notification.getOwnerID());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm thông tin: " + e.getMessage());
        }
        return false;
    }
    
    public boolean deleteNotification(int id) {
        String sql = "DELETE FROM notification WHERE notification_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa thông báo: " + e.getMessage());
        }
        return false;
    }
    
    public Object[] getLastRow() {
        String query = "SELECT * FROM notification ORDER BY notification_id DESC LIMIT 1";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if (res.next()) {
                return new Object[]{
                        res.getInt("notification_id"),
                        res.getString("title"),
                        res.getInt("recipient_id"),
                        res.getString("phone_number"),
                        res.getString("email"),
                        res.getString("notification_type")
                        
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void addDataToTable(JTable table) {
        String query = "SELECT * FROM notification";
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while( res.next() ) {
                model.addRow( new Object[]{
                        res.getInt("notification_id"),
                        res.getString("title"),
                        res.getString("message"),
                        res.getString("notification_type"),
                        res.getInt("recipient_id")}
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
