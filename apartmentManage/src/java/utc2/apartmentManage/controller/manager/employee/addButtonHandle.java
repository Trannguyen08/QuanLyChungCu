package java.utc2.apartmentManage.controller.manager.employee;

import com.toedter.calendar.JDateChooser;
import org.mindrot.jbcrypt.BCrypt;
import utc2.apartmentManage.model.*;
import utc2.apartmentManage.repository.manager.infoRepository;
import utc2.apartmentManage.service.implement.login.registerIMP;
import utc2.apartmentManage.service.implement.manager.employeeIMP;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

public class addButtonHandle {
    private JButton addBtn;
    private JTextField fullName, phoneNumber, email, salary, username, password, idcard;
    private JComboBox<String> gender, position, shift;
    private JDateChooser date;
    private JTable table;
    private JFrame add;
    private final employeeIMP employeeService = new employeeIMP();
    private final registerIMP registerService = new registerIMP();
    private infoRepository ir = new infoRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    public addButtonHandle(JComboBox<String> shift, JButton addBtn, JTextField fullName, JComboBox<String> gender, JDateChooser date,
                           JTextField phoneNumber, JTextField email, JTextField idcard, JComboBox<String> position,
                           JTextField salary, JTextField username, JTextField password, JTable table, JFrame add) {
        
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
        this.idcard = idcard;
        this.shift = shift;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }

    private void addNewRow() {
        boolean check = employeeService.addValidate(username, password, fullName, phoneNumber, email, idcard, salary, date);
        if (!check) {
            return;
        }
        
        int newAccountID = registerService.getNewID();
        Account acc = new Account(newAccountID, username.getText().trim(), 
                                  BCrypt.hashpw(password.getText().trim(), BCrypt.gensalt(12)), 
                                  email.getText().trim(),
                                  phoneNumber.getText().trim(), "employee");

        // Tạo đối tượng Employee
        int id = employeeService.getNewID();
        Employee employee = new Employee(id, fullName.getText().trim(), 
                            gender.getSelectedItem().toString(),
                            ScannerUtil.convertJDateChooserToString(date),
                            phoneNumber.getText().trim(), email.getText().trim(),
                            idcard.getText().trim(),
                            position.getSelectedItem().toString(), 
                           ScannerUtil.parseToDouble(salary.getText().trim()),
                            "Làm việc", ir.getNewID(), newAccountID, 
                            shift.getSelectedItem().toString());

        // Kiểm tra trùng lặp nhân viên
        if (employeeService.isDuplicate(employee)) {
            return;
        }

        // thêm vào database và table
        boolean isAddedComplete = registerService.addAccount(acc) && employeeService.add(employee) ; 
        DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[] {employee.getId(), employee.getName(), employee.getGender(),
                employee.getDate(), employee.getPhoneNumber(), employee.getEmail(), 
                employee.getPosition(), df.format(employee.getSalary()), employee.getStatus(), employee.getShift()});

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

