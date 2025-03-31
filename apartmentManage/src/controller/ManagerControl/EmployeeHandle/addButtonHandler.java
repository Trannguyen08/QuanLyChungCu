package controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import model.Employee;
import dao.managerDAO.EmployeeDAO;
import util.ScannerUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.managerService.employeeService;

public class addButtonHandler {
    private JButton addBtn;
    private JTextField fullName, phoneNumber, email, salary;
    private JComboBox<String> gender, position,  status;
    private JDateChooser hiringDate;
    private JTable table;
    private JFrame add;
    private final employeeService employeeService = new employeeService();

    public addButtonHandler(JButton addBtn, JTextField fullName, JComboBox<String> gender, JTextField phoneNumber, 
                            JTextField email, JComboBox<String> position, JTextField salary, JDateChooser hiringDate, 
                            JComboBox<String> status, JTable table, JFrame add) {
        this.fullName = fullName;
        this.addBtn = addBtn;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.status = status;
        this.table = table;
        this.add = add;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }

    private void addNewRow() {
        String fName = fullName.getText().trim();
        Object eGender = gender.getSelectedItem().toString();
        String pNumber = phoneNumber.getText().trim();
        String eEmail = email.getText().trim();
        Object ePosition = position.getSelectedItem().toString();
        Object eStatus = status.getSelectedItem().toString();

        // Lấy ngày và định dạng
        Date selectedDate = hiringDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";


        double salaryValue = Double.parseDouble(salary.getText().trim());

        // Kiểm tra dữ liệu hợp lệ trước khi tiếp tục
        boolean check = employeeService.validateData(table, fullName, gender, phoneNumber, email, position, salary, hiringDate, status);
        if (!check) {
            return;
        }

        // Tạo đối tượng Employee
       Employee employee = new Employee(0, fullName.getText().trim(), gender.getSelectedItem().toString(), phoneNumber.getText().trim(), 
                            email.getText().trim(), position.getSelectedItem().toString(), Double.parseDouble(salary.getText().trim()), 
                           (hiringDate.getDate() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(hiringDate.getDate()) : "N/A", 
                                status.getSelectedItem().toString());

        // Kiểm tra trùng lặp nhân viên
        if (employeeService.isDuplicate(employee, table)) {
            JOptionPane.showMessageDialog(null, "Nhân viên này đã tồn tại!", "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // thêm vào database và table
        boolean isAddedComplete = employeeService.addEmployee(employee);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[] {employee.getId(), employee.getName(), employee.getGender(),
                employee.getPhoneNumber(), employee.getEmail(), employee.getPosition(),
                employee.getSalary(), employee.getHiringDate(), employee.getStatus()});

        if( isAddedComplete ) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

