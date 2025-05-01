package main.java.utc2.apartmentManage.db;

import java.sql.*;

public class ConnectDB {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                databaseConfig.URL,
                databaseConfig.USER,
                databaseConfig.PASSWORD
        );
    }
}

