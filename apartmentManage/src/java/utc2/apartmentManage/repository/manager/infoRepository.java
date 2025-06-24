package java.utc2.apartmentManage.repository.manager;

import utc2.apartmentManage.db.databaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class infoRepository {
    public int getNewID() {
        String query = """
                       SELECT MIN(a1.person_id) + 1 AS next_id
                       FROM personal_info a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM personal_info a2 WHERE a2.person_id = a1.person_id + 1
                       );""";
        int ans = 0;
        try (Connection con = databaseConnect.getConnection();
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
