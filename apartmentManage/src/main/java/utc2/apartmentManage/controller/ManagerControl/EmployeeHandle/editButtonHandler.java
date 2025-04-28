package main.java.utc2.apartmentManage.controller.ManagerControl.EmployeeHandle;

import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.service.managerService.employeeIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;



public class editButtonHandler {
    private JButton editBtn;
    private JTextField salary;
    private JComboBox<String> position;
    private JTable table;
    private JFrame edit;
    private final employeeIMP employeeService = new employeeIMP();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    public editButtonHandler(JComboBox<String> position, JTextField salary, 
                             JButton editBtn, JTable table, JFrame edit) {
        this.editBtn = editBtn;
        this.position = position;
        this.salary = salary;
        this.table = table;
        this.edit = edit;

        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
    }

    public void loadSelectedRowData() {
        boolean check = employeeService.loadSelectedRowData(table, position, salary);
    }

    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (!employeeService.isSelectedRow(table)) {
            return;
        }
        String sal = salary.getText().trim();
        if( sal.isEmpty() ){
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ dữ liệu.",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if( !ScannerUtil.validateDouble(sal, "lương") ){
            return;
        }
        

        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        try {
            sal = sal.replace(",", ".");
            double parsedSalary = ScannerUtil.parseToDouble(sal);

            model.setValueAt(position.getSelectedItem(), selectedRow, 6);
            model.setValueAt(df.format(parsedSalary), selectedRow, 7); // Format the parsed salary
            edit.setVisible(false);

            Employee e = employeeService.getObject(id);
            e.setPosition(position.getSelectedItem().toString().trim());
            e.setSalary(parsedSalary);
            

            boolean isUpdatedComplete = employeeService.update(e);
            edit.setVisible(false);
            if (isUpdatedComplete) {
                JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }


        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Lỗi: Vui lòng nhập số hợp lệ cho lương.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); 
        } 
    }
}