package main.java.utc2.apartmentManage.util;

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

