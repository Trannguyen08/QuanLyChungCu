package main.java.utc2.apartmentManage.repository.LoginRepository;

import java.sql.*;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.db.ConnectDB;
import org.mindrot.jbcrypt.BCrypt;

public class registerRepository {
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.id) + 1 AS next_id
                       FROM accounts a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM accounts a2 WHERE a2.id = a1.id + 1
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
    
    public static int isUserExists(Account acc) {
        String checkQuery = "SELECT username, email, phoneNum FROM accounts WHERE username = ? OR email = ? OR phoneNum = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(checkQuery)) {

            stmt.setString(1, acc.getUsername());
            stmt.setString(2, acc.getEmail());
            stmt.setString(3, acc.getPhoneNum());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (acc.getUsername().equals(rs.getString("username"))) {
                    return 1; 
                }
                if (acc.getEmail().equals(rs.getString("email"))) {
                    return 2; 
                }
                if (acc.getPhoneNum().equals(rs.getString("phoneNum"))) {
                    return 3; 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0; // Không trùng username, email, phone
    }


    public static boolean insertUser(Account acc) {
        String query = "INSERT INTO accounts (id, username, password, email, phoneNum, role) VALUES(?, ?, ?, ?, ?, ?)";
        
        try (Connection con = ConnectDB.getConnection();
            PreparedStatement stmt = con.prepareStatement(query)) {
            String hashedPassword = BCrypt.hashpw(acc.getPassword(), BCrypt.gensalt(12));
            
            stmt.setInt(1, acc.getId());
            stmt.setString(2, acc.getUsername());
            stmt.setString(3, hashedPassword);
            stmt.setString(4, acc.getEmail());
            stmt.setString(5, acc.getPhoneNum());
            stmt.setString(6, "customer");

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; 
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
