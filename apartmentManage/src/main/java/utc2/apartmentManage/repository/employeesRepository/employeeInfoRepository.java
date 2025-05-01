package main.java.utc2.apartmentManage.repository.employeesRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.utc2.apartmentManage.db.ConnectDB;
import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.model.Resident;
import main.java.utc2.apartmentManage.util.ScannerUtil;

public class employeeInfoRepository {
    public int isDuplicateEmployee(Employee emp) {
        String sql = """
            SELECT pi.email, pi.phoneNum, pi.id_card
            FROM employees e
            JOIN personal_info pi ON e.person_id = pi.person_id
            WHERE e.employee_id <> ?
              AND (pi.email = ? OR pi.phoneNum = ? OR pi.id_card = ?)
            """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emp.getId());
            stmt.setString(2, emp.getEmail());
            stmt.setString(3, emp.getPhoneNumber());
            stmt.setString(4, emp.getIdcard());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (emp.getEmail().equals(rs.getString("email"))) return 1;
                if (emp.getPhoneNumber().equals(rs.getString("phoneNum"))) return 2;
                if (emp.getIdcard().equals(rs.getString("id_card"))) return 3;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Không trùng
    }
    
    public boolean updateInfo(Employee emp) {
        String sql = "UPDATE personal_info SET phoneNum = ?, email = ?, "
                    + "id_card = ? WHERE person_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, emp.getPhoneNumber());
            pstmt.setString(2, emp.getEmail());
            pstmt.setString(3, emp.getIdcard());
            pstmt.setInt(4, emp.getInfoID());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật cư dân: " + e.getMessage());
        }
        return false;
    }

}
