package java.utc2.apartmentManage.repository.employee;

import utc2.apartmentManage.db.databaseConnect;
import utc2.apartmentManage.model.Attendance;
import utc2.apartmentManage.model.EmployeeReport;
import utc2.apartmentManage.util.ScannerUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class timekeepingRepository {
    private Connection getConnection() throws SQLException {
        return databaseConnect.getConnection(); // Sử dụng ConnectDB
    }
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.attendance_id) + 1 AS next_id
                       FROM attendances a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM attendances a2 WHERE a2.attendance_id = a1.attendance_id + 1
                       );""";
        int ans = 0;
        try (Connection con = databaseConnect.getConnection();
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

    public boolean insertAttendance(int attendanceId, int employeeId, Date attendanceDate) {
        String sql = "INSERT INTO attendances (attendance_id, employee_id, attendance_date) VALUES (?, ?, ?)";

        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, attendanceId);
            stmt.setInt(2, employeeId);
            stmt.setDate(3, attendanceDate);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateCheckInTime(Date attendanceId, Time checkInTime) {
        String sql = "UPDATE attendances SET check_in_time = ? WHERE attendance_date = ?";

        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTime(1, checkInTime);
            stmt.setDate(2, attendanceId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateCheckOutTime(Date attendanceId, Time checkoutTime) {
        String sql = "UPDATE attendances SET check_out_time = ? WHERE attendance_date = ?";

        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTime(1, checkoutTime);
            stmt.setDate(2, attendanceId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<Attendance> getAttendance(int employeeId, int month, int year) {
        List<Attendance> records = new ArrayList<>();
        String sql = "SELECT * FROM attendances WHERE employee_id = ? " +
                     "AND MONTH(attendance_date) = ? AND YEAR(attendance_date) = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                records.add(new Attendance(
                        rs.getInt("attendance_id"),
                        ScannerUtil.convertDateFormat2(rs.getString("attendance_date")),
                        rs.getString("check_in_time"),
                        rs.getString("check_out_time"),
                        rs.getInt("employee_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
    
    public Attendance getAttendanceToday(int employeeId) {
        String sql = """
                SELECT * 
                FROM attendances 
                WHERE employee_id = ? AND attendance_date = CURRENT_DATE
                """; 

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Attendance(
                    rs.getInt("attendance_id"),
                    ScannerUtil.convertDateFormat2(rs.getString("attendance_date")),
                    rs.getString("check_in_time"),
                    rs.getString("check_out_time"),
                    rs.getInt("employee_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public EmployeeReport getEmployeeReportByID(int month, int year, int id) {
        String sql = """
            SELECT 
                e.employee_id,
                p.full_name,
                e.position,
                e.shift,
                COUNT(DISTINCT a.attendance_date) AS work_days,
                SUM(
                    CASE 
                        WHEN a.check_out_time >= a.check_in_time THEN 
                            TIMESTAMPDIFF(MINUTE, a.check_in_time, a.check_out_time)
                        ELSE 
                            TIMESTAMPDIFF(MINUTE, a.check_in_time, TIMESTAMPADD(DAY, 1, a.check_out_time))
                    END
                ) AS total_work_minutes,
                e.salary 
            FROM employees e 
            JOIN personal_info p ON e.person_id = p.person_id 
            LEFT JOIN attendances a 
                ON e.employee_id = a.employee_id 
                AND MONTH(a.attendance_date) = ? 
                AND YEAR(a.attendance_date) = ? 
                AND a.check_in_time IS NOT NULL 
                AND a.check_out_time IS NOT NULL 
            WHERE e.status = 'Làm việc' AND e.employee_id = ?
            GROUP BY e.employee_id, p.full_name, e.position, e.shift, e.salary 
            ORDER BY p.full_name
        """;


        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, month);
            ps.setInt(2, year);
            ps.setInt(3, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return new EmployeeReport(
                    rs.getInt("employee_id"),
                    rs.getString("full_name"),
                    rs.getString("position"),
                    rs.getString("shift"),
                    rs.getInt("work_days"),
                    rs.getInt("total_work_minutes"),
                    rs.getDouble("salary"),
                    0, 0, 0
                ); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}