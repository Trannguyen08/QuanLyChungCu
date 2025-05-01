package main.java.utc2.apartmentManage.repository.ManagerRepository;

import main.java.utc2.apartmentManage.model.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2.apartmentManage.db.ConnectDB;
import main.java.utc2.apartmentManage.util.ScannerUtil;

public class notificationRepository {
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.notification_id) + 1 AS next_id
                       FROM notifications a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM notifications a2 WHERE a2.notification_id = a1.notification_id + 1
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

    public boolean addNoti(Notification notification) {
        String query = "INSERT INTO notifications (notification_id, recipant, title, message, notification_type, sentDate) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, notification.getID());
            pstmt.setString(2, notification.getRecipant());
            pstmt.setString(3, notification.getTitle());
            pstmt.setString(4, notification.getMess());
            pstmt.setString(5, notification.getType());
            pstmt.setString(6, ScannerUtil.convertDateFormat1(notification.getSentDate()));

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNoti(int notificationId) {
        String query = "DELETE FROM notifications WHERE notification_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, notificationId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateNoti(Notification notification) {
        String query = "UPDATE notifications SET title = ?, message = ?, notification_type = ?, sentDate = ? WHERE notification_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, notification.getTitle());
            pstmt.setString(2, notification.getMess());
            pstmt.setString(3, notification.getType());
            pstmt.setString(4, ScannerUtil.convertDateFormat1(notification.getSentDate()));
            pstmt.setInt(5, notification.getID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Notification getNotiByID(int notificationId) {
        String query = "SELECT * FROM notifications WHERE notification_id = ?";
        Notification notification = null;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, notificationId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                notification = new Notification(
                        rs.getInt("notification_id"),
                        rs.getString("recipant"),
                        rs.getString("notification_type"),
                        rs.getString("title"),
                        rs.getString("message"),
                        ScannerUtil.convertDateFormat2(rs.getString("sentDate")),
                        rs.getInt("seen")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notification;
    }

    
    public List<Notification> getAllNotifications() {
        String query = "SELECT * FROM notifications";
        List<Notification> notificationList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                notificationList.add( new Notification(
                        rs.getInt("notification_id"),
                        rs.getString("recipant"),
                        rs.getString("notification_type"),
                        rs.getString("title"),
                        rs.getString("message"),
                        ScannerUtil.convertDateFormat2(rs.getString("sentDate")),
                        rs.getInt("seen")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notificationList;
    }
    
    public List<Notification> filterNotifications(Notification noti) {
        List<Notification> notifications = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM notifications WHERE 1=1");
        List<Object> params = new ArrayList<>();

        if (noti.getTitle() != null && !noti.getTitle().trim().isEmpty()) {
            query.append(" AND title LIKE ?");
            params.add("%" + noti.getTitle().trim() + "%");
        }

        if (noti.getType() != null && !noti.getType().trim().isEmpty()) {
            query.append(" AND notification_type = ?");
            params.add(noti.getType().trim());
        }
        
        if (noti.getRecipant() != null && !noti.getRecipant().trim().isEmpty()) {
            query.append(" AND recipant = ?");
            params.add(noti.getRecipant().trim());
        }

        if (noti.getSentDate() != null && !noti.getSentDate().trim().isEmpty()) {
            query.append(" AND sentDate = ?");
            params.add(ScannerUtil.convertDateFormat1(noti.getSentDate().trim())); 
        }

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query.toString())) {

            // Gán tham số vào câu lệnh
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                notifications.add( new Notification(
                        rs.getInt("notification_id"),
                        rs.getString("recipant"),
                        rs.getString("notification_type"),
                        rs.getString("title"),
                        rs.getString("message"),
                        ScannerUtil.convertDateFormat2(rs.getString("sentDate")),
                        rs.getInt("seen")
                ));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notifications;
    }
    
    public List<Notification> getAllNotificationForUser(String object) {
        String query = "SELECT * FROM notifications WHERE recipant = ?";
        List<Notification> notificationList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setString(1, object);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                notificationList.add( new Notification(
                        rs.getInt("notification_id"),
                        rs.getString("recipant"),
                        rs.getString("notification_type"),
                        rs.getString("title"),
                        rs.getString("message"),
                        ScannerUtil.convertDateFormat2(rs.getString("sentDate")),
                        rs.getInt("seen")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notificationList;
    }
    

    
}
