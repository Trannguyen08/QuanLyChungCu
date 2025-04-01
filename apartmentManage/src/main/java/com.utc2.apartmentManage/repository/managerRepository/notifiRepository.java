package main.java.com.utc2.apartmentManage.repository.managerRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import main.java.com.utc2.apartmentManage.databaseConnect.ConnectDB;
import main.java.com.utc2.apartmentManage.model.Notification;


public class notifiRepository {
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

    public List<Notification> getAllNotification() {
        String query = "SELECT * FROM notification";

        List<Notification> notificationList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
                notificationList.add(new Notification(
                        res.getInt("ID"),
                        res.getInt("ownerID"),
                        res.getString("title"),
                        res.getString("mess"),
                        res.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notificationList;
    }

    public List<Notification> getNotificationsByFilter(Integer notificationID, Integer ownerID, String title, String mess, String type) {
        List<Notification> notifications = new ArrayList<>();
        String query = "SELECT * FROM notification WHERE 1=1";

        List<Object> params = new ArrayList<>();

        if (notificationID != null) {
            query += " AND ID = ?";
            params.add(notificationID);
        }
        if (ownerID != null) {
            query += " AND ownerID = ?";
            params.add(ownerID);
        }
        if (title != null && !title.isEmpty()) {
            query += " AND title LIKE ?";
            params.add("%" + title + "%");
        }
        if (mess != null && !mess.isEmpty()) {
            query += " AND mess LIKE ?";
            params.add("%" + mess + "%");
        }
        if (type != null && !type.isEmpty()) {
            query += " AND type = ?";
            params.add(type);
        }

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                notifications.add(new Notification(
                        rs.getInt("ID"),
                        rs.getInt("ownerID"),
                        rs.getString("title"),
                        rs.getString("mess"),
                        rs.getString("type")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }


}
