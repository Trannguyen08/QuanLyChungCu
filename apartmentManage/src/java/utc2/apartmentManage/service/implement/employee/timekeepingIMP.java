package java.utc2.apartmentManage.service.implement.employee;

import utc2.apartmentManage.model.Attendance;
import utc2.apartmentManage.model.EmployeeReport;
import utc2.apartmentManage.repository.employee.timekeepingRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class timekeepingIMP {
    private timekeepingRepository repository = new timekeepingRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    public int getNewID() {
        return repository.getIDMinNotExist();
    }
    
    public boolean add(int attendanceId, int employeeId, Date attendanceDate) {
        return repository.insertAttendance(attendanceId, employeeId, attendanceDate);
    }
    
    public boolean addCheckInTime(Date attendanceId, Time checkInTime) {
        return repository.updateCheckInTime(attendanceId, checkInTime);
    }
    
    public boolean addCheckOutTime(Date attendanceId, Time checkOutTime) {
        return repository.updateCheckOutTime(attendanceId, checkOutTime);
    }
    
    public Attendance isExistTodayDate(int employee_id) {
        return repository.getAttendanceToday(employee_id);
    }
    
    public void sortlist(List<Attendance> list) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        list.sort((n1, n2) -> {
            LocalDate d1 = LocalDate.parse(n1.getDate(), formatter);
            LocalDate d2 = LocalDate.parse(n2.getDate(), formatter);
            return d2.compareTo(d1); 
        });
    }

    public int getDaysInMonth(int month, int year) {
        YearMonth yearMonth = YearMonth.of(year, month);
        return yearMonth.lengthOfMonth();
    }

    public void setSalary(EmployeeReport er, int month, int year, int offDate) {
        int actualMinutes = er.getTotalHour();
        int expectedMinutes = er.getWorkDateNum() * 9 * 60;
        int diffMinutes = actualMinutes - expectedMinutes;
        double diffHours = Math.abs(diffMinutes) / 60.0;

        double amount = Math.round(diffHours * 50000);

        if (diffMinutes > 0) {
            er.setBonus(amount);
            er.setFoul(offDate*100000);
        } else if (diffMinutes < 0) {
            er.setFoul(amount + offDate*100000);
        }
    }

    public void setUpTable(JTable table, JTable tiendo, JTable salaryTable, int month, int year, int employee_id) {
        List<Attendance> list = repository.getAttendance(employee_id, month, year);
        sortlist(list);
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Attendance a : list) {
            model.addRow(new Object[]{
                    a.getDate(),
                    a.getCheckin(),
                    a.getCheckout()
            });
        }

        EmployeeReport employee = repository.getEmployeeReportByID(month, year, employee_id);
        LocalDate now = LocalDate.now();

        DefaultTableModel model1 = (DefaultTableModel) tiendo.getModel();
        model1.setRowCount(0);
        int offDate = 0;
        if (month == now.getMonthValue() && year == now.getYear()) {
            offDate = now.getDayOfMonth() - employee.getWorkDateNum();
        } else if (month < now.getMonthValue()) {
            offDate = getDaysInMonth(month, year) - employee.getWorkDateNum();
        }
        model1.addRow(new Object[]{
                    employee.getWorkDateNum(),
                    df.format(employee.getTotalHour()/60),
                    offDate
        });


        if (month < now.getMonthValue()) {
            setSalary(employee, month, year, offDate);
            DefaultTableModel model2 = (DefaultTableModel) salaryTable.getModel();
            model2.setRowCount(0);

            model2.addRow(new Object[]{
                    df.format(employee.getSalary()),
                    df.format(employee.getBonus()),
                    df.format(employee.getFoul()),
                    df.format(employee.getSalary() + employee.getBonus() - employee.getFoul())
            });

        }

        setFont(salaryTable);
        setFont(tiendo);
        setFont(table);
        
    }
    
    public void setFont(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader()
                .getDefaultRenderer()).
                setHorizontalAlignment(SwingConstants.CENTER);
    }
}