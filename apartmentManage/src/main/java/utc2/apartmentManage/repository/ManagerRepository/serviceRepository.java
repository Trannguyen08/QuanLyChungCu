
package main.java.utc2.apartmentManage.repository.ManagerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2.apartmentManage.model.Service;
import main.java.utc2.apartmentManage.db.ConnectDB;


public class serviceRepository {
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.service_id) + 1 AS next_id
                       FROM services a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM services a2 WHERE a2.service_id = a1.service_id + 1
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
    
    public boolean addService(Service service) {
        String sql = "INSERT INTO services (service_id, service_name, relevant, service_type, price, unit, description) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, service.getServiceId());
            pstmt.setString(2, service.getServiceName());
            pstmt.setString(3, service.getRelevant());
            pstmt.setString(4, service.getServiceType());
            pstmt.setDouble(5, service.getPrice());
            pstmt.setString(6, service.getUnit());
            pstmt.setString(7, service.getDescription());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm dịch vụ: " + e.getMessage());
        }
        return false;
    }

    public boolean updateService(Service service) {
        String sql = "UPDATE services SET service_name = ?, service_type = ?, relevant = ?, "
                    + "price = ?, unit = ?, description = ? WHERE service_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, service.getServiceName());
            pstmt.setString(2, service.getServiceType());
            pstmt.setString(3, service.getRelevant());
            pstmt.setDouble(4, service.getPrice());
            pstmt.setString(5, service.getUnit());
            pstmt.setString(6, service.getDescription());
            pstmt.setInt(7, service.getServiceId());

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
                        res.getString("relevant"),
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
    
    public Service getServiceByID(int id) {
        List<Service> services = new ArrayList<>();
        String query = "SELECT * FROM services WHERE service_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query) ) {

            pstmt.setInt(1, id);
            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                return new Service(
                        res.getInt("service_id"),
                        res.getString("service_name"),
                        res.getString("service_type"),
                        res.getString("relevant"),
                        res.getDouble("price"),
                        res.getString("unit"),
                        res.getString("description")
                );
            }
        } catch (SQLException e) {
            System.err.println("Lỗi lấy danh sách dịch vụ: " + e.getMessage());
        }
        return null;
    }
    
    public boolean isDuplicate(Service service) {
        String query = "SELECT COUNT(*) FROM services WHERE service_name = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query) ) {
            
            pstmt.setString(1, service.getServiceName());
            ResultSet res = pstmt.executeQuery();
            
            if (res.next()) {
                int count = res.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi trùng tên dịch vụ: " + e.getMessage());
        }
        return false;
    }
    
    public List<Service> getFilteredServiceByIcon(Service service, double toPrice) {
        List<Service> services = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        String sql = "SELECT * FROM services WHERE 1=1";
        
        if( service.getServiceId() != 0 ) {
            sql += " AND service_id = ?";
            params.add(service.getServiceId());
        }
        
        if( !service.getServiceName().isEmpty() ) {
            sql += " AND service_name LIKE ?";
            params.add("%" + service.getServiceName() + "%");
        }
        
        if( !service.getServiceType().isEmpty() ) {
            sql += " AND service_type = ?";
            params.add(service.getServiceType());
        }
        
        if( service.getPrice() != 0 ) {
            sql += " AND price >= ?";
            params.add(service.getPrice());
        }
        
        if( toPrice != 0 ) {
            sql += " AND price <= ?";
            params.add(toPrice);
        }
        
        if( !service.getUnit().isEmpty() ) {
            sql += " AND unit LIKE ?";
            params.add("%" + service.getUnit() + "%");
        }
        
        if( !service.getDescription().isEmpty() ) {
            sql += " AND description LIKE ?";
            params.add("%" + service.getDescription() + "%");
        }

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet res = pstmt.executeQuery();
            while (res.next()) {
                Service ser = new Service(
                        res.getInt("service_id"),
                        res.getString("service_name"),
                        res.getString("service_type"),
                        res.getString("relevant"),
                        res.getDouble("price"),
                        res.getString("unit"),
                        res.getString("description")
                );
                services.add(ser);
            }
            res.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return services;
    }
}
