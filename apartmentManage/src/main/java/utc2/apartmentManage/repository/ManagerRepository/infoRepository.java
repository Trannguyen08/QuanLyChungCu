package main.java.utc2.apartmentManage.repository.ManagerRepository;

import main.java.utc2.apartmentManage.db.ConnectDB;
import java.sql.*;

public class infoRepository {
    public int getNewID() {
        String query = """
                       SELECT MIN(a1.person_id) + 1 AS next_id
                       FROM personal_info a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM personal_info a2 WHERE a2.person_id = a1.person_id + 1
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
}
