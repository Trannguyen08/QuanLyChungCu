
package dao.managerDAO;

import DatabaseConnect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Employee;

/**
 *
 * @author nghia
 */
public class EmployeeDAO {
    private int getRowCount() {
        String query = "SELECT COUNT(*) FROM employees";
        int count = 0;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if( res.next() ) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
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
        String sql = "INSERT INTO employees (full_name, phone_number, email, " +
                "gender, position, salary, hire_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
                        res.getString("phone_number"),
                        res.getString("email"),
                        res.getString("gender"),
                        res.getString("position"),
                        res.getDouble("salary"),
                        res.getString("hire_date"),
                        res.getString("status")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDataToTable(JTable table) {
        String query = "SELECT * FROM employees";
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while( res.next() ) {
                model.addRow( new Object[]{
                        res.getInt("employee_id"),
                        res.getString("full_name"),
                        res.getString("phone_number"),
                        res.getString("email"),
                        res.getString("gender"),
                        res.getString("position"),
                        res.getDouble("salary"),
                        res.getString("hire_date"),
                        res.getString("status")}
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
