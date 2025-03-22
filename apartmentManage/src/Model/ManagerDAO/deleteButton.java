package Model.ManagerDAO;

import DatabaseConnect.ConnectDB;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.sql.*;

public class deleteButton {
    public static void deleteRowInTable(String tableName, int id) {
        try (Connection con = ConnectDB.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName + " LIMIT 1")) {
            
            ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData();
            String firstColumnName = metaData.getColumnName(1); 
            String query = "DELETE FROM " + tableName + " WHERE " + firstColumnName + " = ?";
        
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();

                if( rowsAffected > 0 ) {
                    System.out.println("Xóa thành công!");
                } else {
                    System.out.println("Không tìm thấy dữ liệu để xóa.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
