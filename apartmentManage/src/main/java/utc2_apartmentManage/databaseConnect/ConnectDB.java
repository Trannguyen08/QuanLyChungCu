package main.java.utc2_apartmentManage.databaseConnect;

import config.databaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    public static void main(String[] args) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseConfig.URL, databaseConfig.USER, databaseConfig.PASSWORD);
            System.out.println("Kết nối thành công: " + connection);
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseConfig.URL, databaseConfig.USER, databaseConfig.PASSWORD);
    }
}
