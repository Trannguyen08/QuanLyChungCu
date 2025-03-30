
package dao.managerDAO;

import DatabaseConnect.ConnectDB;
import model.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ServiceDAO {
    public boolean addService(Service service) {
        String sql = "INSERT INTO services (service_name, service_type, price, unit, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, service.getServiceName());
            pstmt.setString(2, service.getServiceType());
            pstmt.setDouble(3, service.getPrice());
            pstmt.setString(4, service.getUnit());
            pstmt.setString(5, service.getDescription());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm dịch vụ: " + e.getMessage());
        }
        return false;
    }

    public boolean updateService(Service service) {
        String sql = "UPDATE services SET service_name = ?, service_type = ?, price = ?, unit = ?, description = ? WHERE service_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, service.getServiceName());
            pstmt.setString(2, service.getServiceType());
            pstmt.setDouble(3, service.getPrice());
            pstmt.setString(4, service.getUnit());
            pstmt.setString(5, service.getDescription());
            pstmt.setInt(6, service.getServiceId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật dịch vụ: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteService(int serviceId) {
        String sql = "DELETE FROM services WHERE service_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, serviceId);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa dịch vụ: " + e.getMessage());
        }
        return false;
    }

    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM services";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
                Service service = new Service(
                        res.getInt("service_id"),
                        res.getString("service_name"),
                        res.getString("service_type"),
                        res.getDouble("price"),
                        res.getString("unit"),
                        res.getString("description")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách dịch vụ: " + e.getMessage());
        }
        return services;
    }
}

