package main.java.utc2_apartmentManage.controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.utc2_apartmentManage.model.Employee;
import main.java.utc2_apartmentManage.service.managerService.employeeService;
import main.java.utc2_apartmentManage.util.ScannerUtil;



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
        
        int id = 0;
        String idStr = employeeID.getText().trim();
        if( !idStr.isEmpty() ) {
            id = Integer.parseInt(idStr);
        }
        
        String hiring = ScannerUtil.convertJDateChooserToString(hiringDate);
        String tohiring = ScannerUtil.convertJDateChooserToString(toHiringDate);

        double fsalary = (salary.getText() == null || salary.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(salary);

        double tsalary = (toSalary.getText() == null || toSalary.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(toSalary);
        
        Employee emp = new Employee(id, fullName.getText().trim(), gender.getSelectedItem().toString().trim(), 
                                    phoneNumber.getText().trim(), email.getText().trim(),
                                    hiring, position.getSelectedItem().toString().trim(), fsalary, 
                                    status.getSelectedItem().toString().trim());
        
        boolean checkRun = employeeService.filterEmployeeIcon(table, emp, tohiring, tsalary);
        frame.setVisible(false);
        if ( !checkRun ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}