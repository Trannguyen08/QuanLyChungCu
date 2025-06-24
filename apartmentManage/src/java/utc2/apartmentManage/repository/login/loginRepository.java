package java.utc2.apartmentManage.repository.login;

import org.mindrot.jbcrypt.BCrypt;
import utc2.apartmentManage.db.databaseConnect;
import utc2.apartmentManage.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class loginRepository {
    public Account getAccountByUsername(String username) {
        String query = "SELECT * FROM accounts WHERE username = ?";
        try (Connection con = databaseConnect.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet res = pstmt.executeQuery();

            if( res.next() ) {
                return new Account(res.getInt("id"),
                                    res.getString("username"),
                                    res.getString("password"),
                                    res.getString("email"),
                                    res.getString("phoneNum"),
                                    res.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validateLogin(String username, String password) {
        Account account = getAccountByUsername(username);
        if( account != null ) {
            return BCrypt.checkpw(password, account.getPassword());
        }
        return false;
    }
    
    public List<Account> getAllAccount() {
        String query = "SELECT * FROM accounts";
        List<Account> list = new ArrayList<>();
        
        try (Connection con = databaseConnect.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            ResultSet res = pstmt.executeQuery();

            if( res.next() ) {
                list.add( new Account(res.getInt("id"),
                                    res.getString("username"),
                                    res.getString("password"),
                                    res.getString("email"),
                                    res.getString("phoneNum"),
                                    res.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
}
