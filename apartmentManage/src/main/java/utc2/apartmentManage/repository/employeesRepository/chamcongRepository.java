package main.java.utc2.apartmentManage.repository.employeesRepository;

import main.java.utc2.apartmentManage.util.ConnectDB;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class chamcongRepository {
    private Connection getConnection() throws SQLException {
        return ConnectDB.getConnection(); // Sử dụng ConnectDB
    }

    public void saveAttendance(int employeeId, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime) {
        String checkSql = "SELECT attendance_id FROM attendances WHERE employee_id = ? AND attendance_date = ?";
        Integer attendanceId = null;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(checkSql)) {
            stmt.setInt(1, employeeId);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                attendanceId = rs.getInt("attendance_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (attendanceId != null) {
            String updateSql = "UPDATE attendances SET check_in_time = ?, check_out_time = ? WHERE attendance_id = ?";
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(updateSql)) {
                stmt.setTime(1, checkInTime != null ? Time.valueOf(checkInTime) : null);
                stmt.setTime(2, checkOutTime != null ? Time.valueOf(checkOutTime) : null);
                stmt.setInt(3, attendanceId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String insertSql = "INSERT INTO attendances (employee_id, attendance_date, check_in_time, check_out_time) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setInt(1, employeeId);
                stmt.setDate(2, Date.valueOf(date));
                stmt.setTime(3, checkInTime != null ? Time.valueOf(checkInTime) : null);
                stmt.setTime(4, checkOutTime != null ? Time.valueOf(checkOutTime) : null);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String[]> getAttendanceRecords(int employeeId, int month, int year) {
        List<String[]> records = new ArrayList<>();
        String sql = "SELECT attendance_date, check_in_time, check_out_time FROM attendances WHERE employee_id = ? " +
                     "AND MONTH(attendance_date) = ? AND YEAR(attendance_date) = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String date = rs.getDate("attendance_date").toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String checkInTime = rs.getTime("check_in_time") != null ? rs.getTime("check_in_time").toLocalTime().toString() : "";
                String checkOutTime = rs.getTime("check_out_time") != null ? rs.getTime("check_out_time").toLocalTime().toString() : "";
                records.add(new String[]{date, checkInTime, checkOutTime});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}