package main.java.utc2.apartmentManage.repository.employeesRepository;

import main.java.utc2.apartmentManage.db.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2.apartmentManage.model.Attendance;
import main.java.utc2.apartmentManage.util.ScannerUtil;

public class chamcongRepository {
    private Connection getConnection() throws SQLException {
        return ConnectDB.getConnection(); // Sử dụng ConnectDB
    }
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.attendance_id) + 1 AS next_id
                       FROM attendances a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM attendances a2 WHERE a2.attendance_id = a1.attendance_id + 1
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

    
    public boolean insertAttendance(int attendanceId, int employeeId, Date attendanceDate) {
        String sql = "INSERT INTO attendances (attendance_id, employee_id, attendance_date) VALUES (?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
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

        try (Connection conn = ConnectDB.getConnection();
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

        try (Connection conn = ConnectDB.getConnection();
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
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
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


}