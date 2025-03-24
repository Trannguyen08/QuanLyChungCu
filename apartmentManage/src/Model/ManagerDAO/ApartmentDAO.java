package Model.ManagerDAO;

import DatabaseConnect.ConnectDB;

import java.sql.*;

public class ApartmentDAO {
    private int getRowCount() {
        String query = "SELECT COUNT(*) FROM apartments";
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
}
