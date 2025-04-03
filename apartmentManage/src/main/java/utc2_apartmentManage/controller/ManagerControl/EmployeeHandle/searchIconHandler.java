
package main.java.utc2_apartmentManage.controller.ManagerControl.EmployeeHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

        boolean check = employeeService.validateSearchInput( employeeID,  fullName, gender, phoneNumber, email,
                                                        position, salary, hiringDate, status, toSalary, toHiringDate);
        if( !check ) {
            return;
        }

        // nếu không null thì xét với table
        if (!employeeID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + employeeID.getText().trim(), 0));
        }
        if (fullName.getText().trim() != null ) {
            filters.add(RowFilter.regexFilter("(?i)" + fullName.getText().trim(), 1));
        }
        if (gender.getSelectedItem() != null && !gender.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + gender.getSelectedItem().toString().trim(), 2));
        }
        if (phoneNumber.getText() != null) {
            filters.add(RowFilter.regexFilter("(?i)" + phoneNumber.getText().trim(), 3));
        }
        if (!email.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + email.getText().trim(), 4));
        }
        if (position.getSelectedItem() != null && !position.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + position.getSelectedItem().toString().trim(), 5));
        }
        if (status.getSelectedItem() != null && !status.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + status.getSelectedItem().toString().trim(), 8));
        }
                
        // xét các khoảng số nguyên số thực
        RowFilter<DefaultTableModel, Integer> numberFilter = new RowFilter<>() {
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                try {
                     double salaryFrom = Double.parseDouble(entry.getStringValue(6).trim());

                    // Chuyển đổi ngày tuyển dụng từ bảng
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date hiringDateFrom = sdf.parse(entry.getStringValue(7).trim()); 
                    
                    String salaryStr = salary.getText().trim();
                    String toSalaryStr = toSalary.getText().trim();

                    Date hiringDateInput = hiringDate.getDate();
                    Date toHiringDateInput = toHiringDate.getDate();

                    boolean salaryMatch = salaryStr.isEmpty() || salaryFrom >= Double.parseDouble(salaryStr);
                    boolean salaryMaxMatch = toSalaryStr.isEmpty() || salaryFrom <= Double.parseDouble(toSalaryStr);

                    boolean hiringDateMatch = (hiringDateInput == null) || hiringDateFrom.compareTo(hiringDateInput) >= 0;
                    boolean hiringDateMaxMatch = (toHiringDateInput == null) || hiringDateFrom.compareTo(toHiringDateInput) <= 0;

                    return salaryMatch && salaryMaxMatch && hiringDateMatch && hiringDateMaxMatch;
                } catch (ParseException e) {
                return false;
                }
            }
        };

        filters.add(numberFilter);
        sorter.setRowFilter(RowFilter.andFilter(filters));
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}