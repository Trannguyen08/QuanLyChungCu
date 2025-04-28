package main.java.utc2.apartmentManage.controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.service.managerService.employeeIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.repository.ManagerRepository.infoRepository;
import main.java.utc2.apartmentManage.service.loginService.registerIMP;



public class addButtonHandler {
    private JButton addBtn;
    private JTextField fullName, phoneNumber, email, salary, username, password;
    private JComboBox<String> gender, position;
    private JDateChooser date;
    private JTable table;
    private JFrame add;
    private final employeeIMP employeeService = new employeeIMP();
    private final registerIMP registerService = new registerIMP();
    private infoRepository ir = new infoRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    public addButtonHandler(JButton addBtn, JTextField fullName, JComboBox<String> gender, JDateChooser date, JTextField phoneNumber, 
                            JTextField email, JComboBox<String> position, JTextField salary, JTextField username,
                            JTextField password, JTable table, JFrame add) {
        this.fullName = fullName;
        this.addBtn = addBtn;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.table = table;
        this.add = add;
        this.date = date;
        this.username = username;
        this.password = password;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }

    private void addNewRow() {
        boolean check = employeeService.addValidate(username, password, fullName, phoneNumber, email, salary);
        if (!check) {
            return;
        }
        
        int newAccountID = registerService.getNewID();
        Account acc = new Account(newAccountID, username.getText().trim(), 
                                  password.getText().trim(), email.getText().trim(),
                                  phoneNumber.getText().trim(), "employee");

        // Tạo đối tượng Employee
        int id = employeeService.getNewID();
        Employee employee = new Employee(id, fullName.getText().trim(), 
                            gender.getSelectedItem().toString(),
                            ScannerUtil.convertJDateChooserToString(date),
                            phoneNumber.getText().trim(), email.getText().trim(), 
                            position.getSelectedItem().toString(), 
                           ScannerUtil.parseToDouble(salary.getText().trim()),
                            "Làm việc", ir.getNewID());

        // Kiểm tra trùng lặp nhân viên
        if (employeeService.isDuplicate(employee)) {
            JOptionPane.showMessageDialog(null,
                    "Email hoặc số điện thoại đã tồn tại!",
                    "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // thêm vào database và table
        boolean isAddedComplete = employeeService.add(employee) && registerService.addAccount(acc); ;
        DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[] {employee.getId(), employee.getName(), employee.getGender(),
                employee.getDate(), employee.getPhoneNumber(), employee.getEmail(), 
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

