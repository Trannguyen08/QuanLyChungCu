package main.java.utc2_apartmentManage.controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.java.utc2_apartmentManage.service.managerService.employeeService;



public class searchIconHandler {
    public JTextField employeeID, fullName, phoneNumber, email, salary, toSalary;
    public JComboBox<String> gender, position, status ;
    public JButton searchBtn;
    public JTable table;
    public JFrame frame;
    private JDateChooser hiringDate;
    private JDateChooser toHiringDate;
    private final employeeService employeeService = new employeeService();

    public searchIconHandler(JTextField employeeID, JTextField fullName, JComboBox<String> gender,
                                JTextField phoneNumber, JTextField email, JComboBox<String> position,
                                JTextField salary, JDateChooser hiringDate, JComboBox<String> status,
                                JButton searchBtn, JTable table, JFrame frame, JTextField toSalary, 
                                JDateChooser toHiringDate) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.gender = gender;
        this.frame = frame;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.status = status;
        this.searchBtn = searchBtn;
        this.table = table;
        this.toHiringDate = toHiringDate;
        this.toSalary = toSalary;


        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }

    public void filterTableData() {
        boolean check = employeeService.validateSearchInput( employeeID,  fullName, gender, phoneNumber, email,
                                                        position, salary, hiringDate, status, toSalary, toHiringDate);
        if( !check ) {
            return;
        }
        
        String hiring = "", tohiring = "";

        if (hiringDate == null ) {
            if (toHiringDate != null) {
                hiring = "01/01/2015"; // Gán giá trị mặc định
            }
        } else if (toHiringDate == null ) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            tohiring = dateFormat.format(new Date()); 
        }

        double minValue = salary.getText().trim().isEmpty() ? 0 : Double.parseDouble(salary.getText().trim());
        double maxValue = toSalary.getText().trim().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(toSalary.getText().trim());
                
        
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}