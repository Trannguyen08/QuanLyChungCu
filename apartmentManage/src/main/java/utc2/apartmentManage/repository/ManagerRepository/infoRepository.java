package main.java.utc2.apartmentManage.repository.ManagerRepository;

import main.java.utc2.apartmentManage.util.ConnectDB;
import java.sql.*;

public class infoRepository {
    public int getNewID() {
        String query = "SELECT MAX(person_id) FROM personal_info";
        try (Connection con  = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                int maxId = rs.getInt(1);
                return maxId + 1; 
            } 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 1;
    }
}
