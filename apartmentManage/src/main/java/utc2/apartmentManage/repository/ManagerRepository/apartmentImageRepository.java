package main.java.utc2.apartmentManage.repository.ManagerRepository;

import main.java.utc2.apartmentManage.util.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class apartmentImageRepository {

    public void deleteImage(int apartmentId, String imagePath) {
        String query = "DELETE FROM apartment_images WHERE apartment_id = ? AND image_url = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setInt(1, apartmentId);
            pstmt.setString(2, imagePath);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Hình ảnh đã được xóa thành công.");
            } else {
                System.out.println("Không tìm thấy hình ảnh cần xóa.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean saveApartmentImages(int apartmentId, List<String> imagePaths) {
        String query = "INSERT INTO apartment_images (apartment_id, image_url) VALUES (?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            for (String imagePath : imagePaths) {
                pstmt.setInt(1, apartmentId);
                pstmt.setString(2, imagePath);
                pstmt.addBatch();  
            }

            int[] rowsAffected = pstmt.executeBatch();
            int rowsInserted = 0;
            for (int rows : rowsAffected) {
                if (rows > 0) {
                    rowsInserted++;
                }
            }

            return rowsInserted == imagePaths.size();  

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}