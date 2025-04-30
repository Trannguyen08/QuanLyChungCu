
package main.java.utc2.apartmentManage.repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.com.utc2.apartmentManage.databaseConnect.ConnectDB;
import main.java.utc2.apartmentManage.model.Utilities;


public class utilitiesRepository {
    public List<Utilities> getFilteredUtilitiesByName(String name) {
        List<Utilities> utilities = new ArrayList<>();
        String sql = "SELECT * FROM utilities WHERE service_name = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                utilities.add(new Utilities(
                    rs.getString("service_name"),
                    rs.getString("service_type"),
                    rs.getDouble("price"),
                    rs.getString("unit"),
                    rs.getString("description")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return utilities;
    }
    public List<Utilities> getAllUtilities() {
        String query = "SELECT * FROM services";
        List<Utilities> utilitiesList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                utilitiesList.add(new Utilities(
                    rs.getString("service_name"),
                    rs.getString("service_type"),
                    rs.getDouble("price"),
                    rs.getString("unit"),
                    rs.getString("description")
                ));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return utilitiesList;
    }
}
