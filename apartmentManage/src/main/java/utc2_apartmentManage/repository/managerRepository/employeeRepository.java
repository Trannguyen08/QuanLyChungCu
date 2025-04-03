package main.java.utc2_apartmentManage.repository.managerRepository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2_apartmentManage.databaseConnect.ConnectDB;
import main.java.utc2_apartmentManage.model.Employee;


public class employeeRepository {
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.employee_id) + 1 AS next_id
                       FROM employees a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM employees a2 WHERE a2.employee_id = a1.employee_id + 1
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
    
    public boolean updateEmployee(Employee employee) {
        String sql = "UPDATE employees SET full_name = ?, phone_number = ?, email = ?, " +
                     "gender = ?, position = ?, salary = ?, hire_date = ?, status = ? WHERE employee_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getGender());
            pstmt.setString(3, employee.getPhoneNumber());
            pstmt.setString(4, employee.getEmail());
            pstmt.setString(5, employee.getPosition());
            pstmt.setDouble(6, employee.getSalary());
            pstmt.setString(7, employee.getHiringDate());
            pstmt.setString(8, employee.getStatus());
            pstmt.setInt(9, employee.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật nhân viên: " + e.getMessage());
        }
        return false; 
    }

    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (employee_id, full_name, gender, phone_number, email, " +
                "position, salary, hire_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getGender());
            pstmt.setString(4, employee.getPhoneNumber());
            pstmt.setString(5, employee.getEmail());
            pstmt.setString(6, employee.getPosition());
            pstmt.setDouble(7, employee.getSalary());
            pstmt.setString(8, employee.getHiringDate());
            pstmt.setString(9, employee.getStatus());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm nhân viên: " + e.getMessage());
        }
        return false;
    }
    
    public boolean deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa nhân viên: " + e.getMessage());
        }
        return false;
    }

    public Object[] getLastRow() {
        String query = "SELECT * FROM employees ORDER BY employee_id DESC LIMIT 1";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if (res.next()) {
                return new Object[]{
                    res.getInt("employee_id"),
                    res.getString("full_name"),
                    res.getString("gender"),
                    res.getString("phone_number"),
                    res.getString("email"),
                    res.getString("hire_date"),
                    res.getString("position"),
                    res.getLong("salary"),
                    res.getString("status")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAllEmployee() {
        String query = "SELECT * FROM employees";
        List<Employee> employeeList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
                Employee employee = new Employee(
                    res.getInt("employee_id"),
                    res.getString("full_name"),
                    res.getString("gender"),
                    res.getString("phone_number"),
                    res.getString("email"),
                    res.getString("hire_date"),
                    res.getString("position"),
                    res.getLong("salary"),
                    res.getString("status")
                );
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }



}
