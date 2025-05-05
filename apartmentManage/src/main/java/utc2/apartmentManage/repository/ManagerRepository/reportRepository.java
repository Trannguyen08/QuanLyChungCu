
package main.java.utc2.apartmentManage.repository.ManagerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2.apartmentManage.model.Amount;
import main.java.utc2.apartmentManage.model.EmployeeReport;
import main.java.utc2.apartmentManage.model.WorkDate;
import main.java.utc2.apartmentManage.db.ConnectDB;
import main.java.utc2.apartmentManage.util.ScannerUtil;


public class reportRepository {
    public List<WorkDate> getAllDateByIDAndMonth(int empId, int month, int year) {
        List<WorkDate> workDates = new ArrayList<>();
        String sql = "SELECT attendance_date, check_in_time, check_out_time " +
                     "FROM attendances " +
                     "WHERE employee_id = ? " +
                     "AND MONTH(attendance_date) = ? " +
                     "AND YEAR(attendance_date) = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                workDates.add( new WorkDate(
                        ScannerUtil.convertDateFormat2(rs.getString("attendance_date")),
                        ScannerUtil.formatSqlTimeToHHmm(rs.getTime("check_in_time")),
                        ScannerUtil.formatSqlTimeToHHmm(rs.getTime("check_out_time"))
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workDates;
    }
    
    public List<EmployeeReport> getAllEmployeeReport(int month, int year) {
        List<EmployeeReport> reports = new ArrayList<>();
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
            WHERE e.status = 'Làm việc' 
            GROUP BY e.employee_id, p.full_name, e.position, e.shift, e.salary 
            ORDER BY p.full_name
        """;


        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, month);
            ps.setInt(2, year);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reports.add( new EmployeeReport(
                    rs.getInt("employee_id"),
                    rs.getString("full_name"),
                    rs.getString("position"),
                    rs.getString("shift"),
                    rs.getInt("work_days"),
                    rs.getInt("total_work_minutes"),
                    rs.getDouble("salary"),
                    0, 0, 0
                )); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reports;
    }
    
    public double calculateMonthlyRevenue(int year, int month) {
        String query = """
                       SELECT  
                            YEAR(b.due_date) AS year,
                            MONTH(b.due_date) AS month, 
                            SUM(bd.quantity * s.price) AS total_revenue 
                        FROM bill_detail_users bd 
                        JOIN bills b ON bd.bill_id = b.bill_id 
                        JOIN services s ON bd.service_id = s.service_id 
                        WHERE YEAR(b.due_date) = ? AND MONTH(b.due_date) = ?  
                        GROUP BY YEAR(b.due_date), MONTH(b.due_date) 
                       """;

        try (Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, year);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    return rs.getDouble("total_revenue");
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    
    public List<Amount> calculateTotalRevenueByService(int month, int year) {
        String query = """
                           SELECT s.service_name, SUM(bd.quantity * s.price) AS total_revenue 
                           FROM bill_detail_users bd  
                           JOIN bills b ON bd.bill_id = b.bill_id  
                           JOIN services s ON bd.service_id = s.service_id  
                           WHERE YEAR(b.due_date) = ? AND MONTH(b.due_date) = ? 
                           GROUP BY s.service_name  
                           ORDER BY total_revenue DESC 
                       """;
        List<Amount> list = new ArrayList<>();
        
        try (Connection con = ConnectDB.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
                 
            ps.setInt(1, year);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Amount(
                        rs.getString("service_name"),
                        rs.getDouble("total_revenue")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Amount> calculateTotalRevenueByServiceWithoutJoin(int month, int year) {
        String query = """
                       SELECT bd.service_name, SUM(bd.price) AS total
                       FROM bill_detail_managers bd 
                       WHERE YEAR(bd.paidDate) = ? AND MONTH(bd.paidDate) = ? 
                       GROUP BY bd.service_name
                   """;

        List<Amount> list = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, year);
            ps.setInt(2, month);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Amount(
                        rs.getString("service_name"),
                        rs.getDouble("total")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }



    
    

}
