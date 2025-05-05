package main.java.utc2.apartmentManage.controller.ManagerControl.EmployeeHandle;

import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.service.managerService.employeeIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class searchIconHandler {
    public JTextField fullName, salary, toSalary;
    public JComboBox<String> shift, position, status ;
    public JButton searchBtn;
    public JTable table;
    public JFrame frame;
    private final employeeIMP employeeService = new employeeIMP();

    public searchIconHandler(JTextField fullName, JComboBox<String> gender,
                                JComboBox<String> position,
                                JTextField salary, JTextField toSalary, JComboBox<String> status,
                                JButton searchBtn, JTable table, JFrame frame ) {
        
        this.fullName = fullName;
        this.shift = gender;
        this.frame = frame;
        this.position = position;
        this.salary = salary;
        this.status = status;
        this.searchBtn = searchBtn;
        this.table = table;
        this.toSalary = toSalary;


        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }

    public void filterTableData() {
        boolean check = employeeService.searchValidate(salary, toSalary);
        if( !check ) {
            return;
        }
        
        double fsalary = (salary.getText() == null || salary.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(salary);

        double tsalary = (toSalary.getText() == null || toSalary.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(toSalary);
        
        Employee emp = new Employee(0, fullName.getText().trim(), "",
                                    "", "", "", "",position.getSelectedItem().toString().trim(),
                                    fsalary, status.getSelectedItem().toString().trim(), 0, 0,
                                    shift.getSelectedItem().toString().trim()
        );
        
        boolean checkRun = employeeService.filterEmployeeIcon(table, emp, tsalary);
        frame.setVisible(false);
        if ( !checkRun ) {
            JOptionPane.showMessageDialog(null, 
                    "Không tìm thấy kết quả phù hợp!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}