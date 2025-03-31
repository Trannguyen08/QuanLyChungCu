package controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import model.Employee;
import dao.managerDAO.EmployeeDAO;
import service.managerService.employeeService;
import util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class editButtonHandler {
    private JButton editBtn;
    private JTextField fullName, phoneNumber, email, salary;
    private JComboBox<String> gender, position, status;
    private JDateChooser hiringDate;
    private JTable table;
    private JFrame edit;
    private final employeeService employeeService = new employeeService();

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
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        Date selectedDate = hiringDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";
        
        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        model.setValueAt(fullName.getText(), selectedRow, 1);
        model.setValueAt(gender.getSelectedItem(), selectedRow, 2);
        model.setValueAt(phoneNumber.getText(), selectedRow, 3);
        model.setValueAt(email.getText(), selectedRow, 4);
        model.setValueAt(position.getSelectedItem(), selectedRow, 5);
        model.setValueAt(salary.getText(), selectedRow, 6);
        model.setValueAt(formattedDate, selectedRow, 7);
        model.setValueAt(status.getSelectedItem(), selectedRow, 8);
        edit.setVisible(false);


        Employee a = new Employee(
            id,  
            fullName.getText(),  
            gender.getSelectedItem().toString(),
            phoneNumber.getText(),  
            email.getText(),  
            position.getSelectedItem().toString(),
            Double.parseDouble(salary.getText()),
            formattedDate,  
            status.getSelectedItem().toString()
        );
        boolean isUpdatedComplete = employeeService.updateEmployee(a);
        if( isUpdatedComplete ) {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}