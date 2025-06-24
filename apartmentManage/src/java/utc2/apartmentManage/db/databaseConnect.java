package java.utc2.apartmentManage.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnect {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                databaseConfig.URL,
                databaseConfig.USER,
                databaseConfig.PASSWORD
        );
    }
}

