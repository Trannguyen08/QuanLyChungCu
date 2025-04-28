package main.java.utc2_apartmentManage.repository.managerRepository;

import main.java.utc2_apartmentManage.databaseConnect.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class apartmentImageRepository {

    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.img_id) + 1 AS next_id
                       FROM apartment_images a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM apartment_images a2 WHERE a2.img_id = a1.img_id + 1
                       );""";
        int ans = 1;
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

    public boolean saveApartmentImages(int apartmentID, List<String> imagePaths) {
        String sql = "INSERT INTO apartment_images (img_id, apartment_id, image_url) VALUES (?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (String path : imagePaths) {
                stmt.setInt(1, getIDMinNotExist());
                stmt.setInt(2, apartmentID);
                stmt.setString(3, path);
                stmt.addBatch();
                stmt.executeBatch();
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean deleteImage(int id, String path) {
        String query = "DELETE FROM apartment_images WHERE img_id = ? AND image_url = ?";
        try(Connection con = ConnectDB.getConnection();
            PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.setString(2, path);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa căn hộ: " + e.getMessage());
        }
        return false;
    }

}
