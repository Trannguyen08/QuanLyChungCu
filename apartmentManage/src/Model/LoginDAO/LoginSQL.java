package Model.LoginDAO;

import DatabaseConnect.ConnectDB;
import Model.Entities.Account;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

public class LoginSQL {
    public static Account getAccountByUsername(String username) {
        String query = "SELECT password FROM accounts WHERE username = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery();

            if( res.next() ) {
                return new Account(username, res.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean validateLogin(String username, String password) {
        Account account = getAccountByUsername(username);
        if( account != null ) {
            return BCrypt.checkpw(password, account.getPassword());
        }
        return false;
    }
}
