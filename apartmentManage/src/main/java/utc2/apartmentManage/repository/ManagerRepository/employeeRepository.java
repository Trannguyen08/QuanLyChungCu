package main.java.utc2.apartmentManage.repository.ManagerRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.db.ConnectDB;
import main.java.utc2.apartmentManage.util.ScannerUtil;

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
    
    public int isDuplicate(Employee employee) {
        String sql = "SELECT " +
                     "CASE " +
                     "  WHEN email = ? THEN 1 " +
                     "  WHEN phoneNum = ? THEN 2 " +
                     "  WHEN id_card = ? THEN 3 " +
                     "  ELSE 0 " +
                     "END AS duplicate_type " +
                     "FROM personal_info " +
                     "WHERE person_id != ? AND (email = ? OR phoneNum = ? OR id_card = ?) " +
                     "LIMIT 1";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, employee.getEmail());
            pstmt.setString(2, employee.getPhoneNumber());
            pstmt.setString(3, employee.getIdcard());
            pstmt.setInt(4, employee.getInfoID());
            pstmt.setString(5, employee.getEmail());
            pstmt.setString(6, employee.getPhoneNumber());
            pstmt.setString(7, employee.getIdcard());

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("duplicate_type");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    
    public boolean updateEmployee(Employee employee) {
        String updatePersonalInfoSql = "UPDATE personal_info SET full_name = ?, gender = ?, dob = ?, phoneNum = ?, email = ? WHERE person_id = ?";
        String updateEmployeeSql = "UPDATE employees SET position = ?, salary = ?, status = ? WHERE employee_id = ?";

        try (Connection con = ConnectDB.getConnection()) {
            con.setAutoCommit(false); // Transaction

            // Update personal_info
            try (PreparedStatement pstmt1 = con.prepareStatement(updatePersonalInfoSql)) {
                pstmt1.setString(1, employee.getName());
                pstmt1.setString(2, employee.getGender());
                pstmt1.setString(3, ScannerUtil.convertDateFormat1(employee.getDate()));
                pstmt1.setString(4, employee.getPhoneNumber());
                pstmt1.setString(5, employee.getEmail());
                pstmt1.setInt(6, employee.getInfoID());
                pstmt1.executeUpdate();
            }

            // Update employees
            try (PreparedStatement pstmt2 = con.prepareStatement(updateEmployeeSql)) {
                pstmt2.setString(1, employee.getPosition());
                pstmt2.setDouble(2, employee.getSalary());
                pstmt2.setString(3, employee.getStatus());
                pstmt2.setInt(4, employee.getId());
                pstmt2.executeUpdate();
            }

            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean addEmployee(Employee employee) {
        String insertPersonalInfoSql = "INSERT INTO personal_info (person_id, full_name, gender, dob, phoneNum, email) VALUES (?, ?, ?, ?, ?, ?)";
        String insertEmployeeSql = "INSERT INTO employees (employee_id, person_id, position, salary, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection()) {
            con.setAutoCommit(false); // Bắt đầu transaction

            // Thêm personal_info
            try (PreparedStatement pstmt1 = con.prepareStatement(insertPersonalInfoSql)) {
                pstmt1.setInt(1, employee.getInfoID());
                pstmt1.setString(2, employee.getName());
                pstmt1.setString(3, employee.getGender());
                pstmt1.setString(4, ScannerUtil.convertDateFormat1(employee.getDate()));
                pstmt1.setString(5, employee.getPhoneNumber());
                pstmt1.setString(6, employee.getEmail());
                pstmt1.executeUpdate();
            }

            // Thêm employees
            try (PreparedStatement pstmt2 = con.prepareStatement(insertEmployeeSql)) {
                pstmt2.setInt(1, employee.getId());
                pstmt2.setInt(2, employee.getInfoID());
                pstmt2.setString(3, employee.getPosition());
                pstmt2.setDouble(4, employee.getSalary());
                pstmt2.setString(5, employee.getStatus());
                pstmt2.executeUpdate();
            }

            con.commit(); // Thành công
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean deleteEmployee(int id) {
        String selectSql = "SELECT person_id FROM employees WHERE employee_id = ?";
        String deleteEmployeeSql = "DELETE FROM employees WHERE employee_id = ?";
        String deletePersonSql = "DELETE FROM person_info WHERE person_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement selectStmt = con.prepareStatement(selectSql);
             PreparedStatement deleteEmployeeStmt = con.prepareStatement(deleteEmployeeSql);
             PreparedStatement deletePersonStmt = con.prepareStatement(deletePersonSql)) {


            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();

            if (!rs.next()) {
                System.err.println("Không tìm thấy nhân viên với ID: " + id);
                return false;
            }

            int personId = rs.getInt("person_id");
            
            deleteEmployeeStmt.setInt(1, id);
            int employeeDeleted = deleteEmployeeStmt.executeUpdate();

            deletePersonStmt.setInt(1, personId);
            int personDeleted = deletePersonStmt.executeUpdate();

            if (employeeDeleted > 0 && personDeleted > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Lỗi xóa nhân viên: " + e.getMessage());
        }

        return false;
    }

    public Employee getEmployeeById(int id) {
        String query = "SELECT e.employee_id, p.full_name, p.gender, p.dob, p.phoneNum, p.email, p.id_card, " +
                       "e.position, e.salary, e.status, e.person_id, e.account_id " +
                       "FROM employees e " +
                       "JOIN personal_info p ON e.person_id = p.person_id " +
                       "WHERE e.employee_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Employee e = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("full_name"),
                    rs.getString("gender"),
                    ScannerUtil.convertDateFormat2(rs.getString("dob")),
                    rs.getString("phoneNum"),
                    rs.getString("email"),
                    rs.getString("id_card"),
                    rs.getString("position"),
                    rs.getDouble("salary"),
                    rs.getString("status"),
                    rs.getInt("person_id"),
                    rs.getInt("account_id")
                );
                return e;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    
    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new ArrayList<>();
        String sql = """
                SELECT e.employee_id, p.full_name, p.gender, p.phoneNum, p.id_card, 
                       p.email, p.dob, e.position, e.salary, e.status, e.person_id, e.account_id
                FROM employees e
                JOIN personal_info p ON e.person_id = p.person_id
                """;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("full_name"),
                    rs.getString("gender"),
                    ScannerUtil.convertDateFormat2(rs.getString("dob")),
                    rs.getString("phoneNum"),
                    rs.getString("email"),
                    rs.getString("id_card"),
                    rs.getString("position"),
                    rs.getDouble("salary"),
                    rs.getString("status"),
                    rs.getInt("person_id"),
                    rs.getInt("account_id")
                );
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }

    
    
    
    public List<Employee> getAllEmployeeBySearchIcon(Employee emp, double toSalary) {
        String query = "SELECT e.employee_id, p.full_name, p.gender, p.dob, p.phoneNum, p.email, p.id_card, " +
                       "e.position, e.salary, e.status, e.person_id, e.account_id " +
                       "FROM employees e " +
                       "JOIN personal_info p ON e.person_id = p.person_id " +
                       "WHERE 1=1";
        List<Employee> emps = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (emp.getName() != null && !emp.getName().isEmpty()) {
            query += " AND p.full_name LIKE ?";
            parameters.add("%" + emp.getName() + "%");
        }
        if (emp.getGender() != null && !emp.getGender().isEmpty()) {
            query += " AND p.gender = ?";
            parameters.add(emp.getGender());
        }
        if (emp.getPosition() != null && !emp.getPosition().isEmpty()) {
            query += " AND e.position = ?";
            parameters.add(emp.getPosition());
        }
        if (emp.getStatus() != null && !emp.getStatus().isEmpty()) {
            query += " AND e.status = ?";
            parameters.add(emp.getStatus());
        }
        if (emp.getSalary() != 0) {
            query += " AND e.salary >= ?";
            parameters.add(emp.getSalary());
        }
        if (toSalary != 0) {
            query += " AND e.salary <= ?";
            parameters.add(toSalary);
        }

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            for (int i = 0; i < parameters.size(); i++) {
                stmt.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee e = new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("full_name"),
                    rs.getString("gender"),
                    ScannerUtil.convertDateFormat2(rs.getString("dob")),
                    rs.getString("phoneNum"),
                    rs.getString("email"),
                    rs.getString("id_card"),
                    rs.getString("position"),
                    rs.getDouble("salary"),
                    rs.getString("status"),
                    rs.getInt("person_id"),
                    rs.getInt("account_id")
                );
                emps.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emps;
    }
    
    public Employee getEmployeeByAccID(int accID) {
        String sql = """
                SELECT e.employee_id, p.full_name, p.gender, p.phoneNum, p.id_card, 
                       p.email, p.dob, e.position, e.salary, e.status, e.person_id, e.account_id
                FROM employees e
                JOIN personal_info p ON e.person_id = p.person_id
                WHERE e.account_id = ?
                """;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, accID);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                return new Employee(
                    rs.getInt("employee_id"),
                    rs.getString("full_name"),
                    rs.getString("gender"),
                    ScannerUtil.convertDateFormat2(rs.getString("dob")),
                    rs.getString("phoneNum"),
                    rs.getString("email"),
                    rs.getString("id_card"),
                    rs.getString("position"),
                    rs.getDouble("salary"),
                    rs.getString("status"),
                    rs.getInt("person_id"),
                    rs.getInt("account_id")
                );
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }


    
}
