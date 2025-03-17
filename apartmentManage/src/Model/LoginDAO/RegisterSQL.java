package Model.LoginDAO;

import DatabaseConnect.ConnectDB;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterSQL {
    public static int isUserExists(String username, String email) {
        String checkUserQuery = "SELECT COUNT(*) FROM accounts WHERE username = ?";
        String checkEmailQuery = "SELECT COUNT(*) FROM account WHERE email = ?";

        try (Connection con = ConnectDB.getConnection()) {
            // Kiểm tra username
            try (PreparedStatement stmt = con.prepareStatement(checkUserQuery)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if( rs.next() && rs.getInt(1) > 0 ) {
                    return 1; 
                }
            }

            // Kiểm tra email
            try (PreparedStatement stmt = con.prepareStatement(checkEmailQuery)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                if( rs.next() && rs.getInt(1) > 0 ) {
                    return 2; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean insertUser(String username, String password, String email) {
        String query = "INSERT INTO accounts (username, password, email, role) VALUES(?, ?, ?, ?)";
        
        try (Connection con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)) {
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
            
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);
            stmt.setString(4, "customer");

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
