package main.java.utc2_apartmentManage.controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;

import main.java.utc2_apartmentManage.model.Employee;
import main.java.utc2_apartmentManage.service.managerService.employeeService;
import main.java.utc2_apartmentManage.util.ScannerUtil;


public class editButtonHandler {
    private JButton editBtn;
    private JTextField fullName, phoneNumber, email, salary;
    private JComboBox<String> gender, position, status;
    private JDateChooser hiringDate;
    private JTable table;
    private JFrame edit;
    private final employeeService employeeService = new employeeService();
    private DecimalFormat df = new DecimalFormat("#,###");

    public editButtonHandler(JButton addBtn, JTextField fullName, JComboBox<String> gender, JTextField phoneNumber, 
                            JTextField email, JComboBox<String> position, JTextField salary, JDateChooser hiringDate, 
                            JComboBox<String> status, JTable table, JFrame edit) {
        this.editBtn = addBtn;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.status = status;
        this.table = table;
        this.edit = edit;

        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
    }
    public void loadSelectedRowData() throws ParseException {
        boolean check = employeeService.loadSelectedRowData(table, fullName, gender, phoneNumber, email, position, salary, hiringDate, status);
    }
 
    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (!employeeService.errorNotifiaction(table)) {
            return;
        }
        
        boolean check = employeeService.validateData(table, fullName, gender, phoneNumber, email, position, salary, hiringDate, status);
        if (!check) {
            return;
        }

            
        String date = ScannerUtil.convertJDateChooserToString(hiringDate);
        
        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        model.setValueAt(fullName.getText(), selectedRow, 1);
        model.setValueAt(gender.getSelectedItem(), selectedRow, 2);
        model.setValueAt(phoneNumber.getText(), selectedRow, 3);
        model.setValueAt(email.getText(), selectedRow, 4);
        model.setValueAt(date, selectedRow, 5);
        model.setValueAt(position.getSelectedItem(), selectedRow, 6);
        model.setValueAt(df.format(salary.getText()), selectedRow, 7);
        model.setValueAt(status.getSelectedItem(), selectedRow, 8);
        edit.setVisible(false);


        Employee a = new Employee(
            id,  
            fullName.getText(),  
            gender.getSelectedItem().toString(),
            phoneNumber.getText(),  
            email.getText(),  
            date,
            position.getSelectedItem().toString(),
            Long.parseLong(salary.getText()),  
            status.getSelectedItem().toString()
        );
        
        boolean isUpdatedComplete = employeeService.updateEmployee(a);
        edit.setVisible(false);
        if( isUpdatedComplete ) {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}