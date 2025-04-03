package main.java.com.utc2.apartmentManage.controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.com.utc2.apartmentManage.model.Employee;
import main.java.com.utc2.apartmentManage.service.managerService.employeeService;
import main.java.com.utc2.apartmentManage.util.ScannerUtil;



public class addButtonHandler {
    private JButton addBtn;
    private JTextField fullName, phoneNumber, email, salary;
    private JComboBox<String> gender, position,  status;
    private JDateChooser hiringDate;
    private JTable table;
    private JFrame add;
    private final employeeService employeeService = new employeeService();
    private DecimalFormat df = new DecimalFormat("#,###");

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
        // Kiểm tra dữ liệu hợp lệ trước khi tiếp tục
        boolean check = employeeService.validateData(table, fullName, gender, phoneNumber, email, position, salary, hiringDate, status);
        if (!check) {
            return;
        }

        // Tạo đối tượng Employee
        int id = employeeService.getNewID();
        String date = ScannerUtil.convertJDateChooserToString(hiringDate);
        Employee employee = new Employee(id, fullName.getText().trim(), gender.getSelectedItem().toString(), phoneNumber.getText().trim(), 
                            email.getText().trim(), date, position.getSelectedItem().toString(), 
                                Long.parseLong(salary.getText().trim()),status.getSelectedItem().toString());

        // Kiểm tra trùng lặp nhân viên
        if (employeeService.isDuplicate(employee, table)) {
            JOptionPane.showMessageDialog(null, "Nhân viên này đã tồn tại!", "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // thêm vào database và table
        boolean isAddedComplete = employeeService.addEmployee(employee);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[] {employee.getId(), employee.getName(), employee.getGender(),
                employee.getPhoneNumber(), employee.getEmail(), employee.getHiringDate(), 
                employee.getPosition(), df.format(employee.getSalary()), employee.getStatus()});

        add.setVisible(false);
        if( isAddedComplete ) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

