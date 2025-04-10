package main.java.utc2_apartmentManage.controller.ManagerControl.EmployeeHandle;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2_apartmentManage.service.managerService.employeeService;
import main.java.utc2_apartmentManage.view.ManagerUI.Pages.Employee;


public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final employeeService employeeService = new employeeService();
    
    public deleteButtonHandler(JButton deleteBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;
        
    }
    public void deleteSelectedRow() {
        Integer id = employeeService.getEmployeeId(table);
        if( id == null ) {
            return;
        }
        if (employeeService.confirmDelete("nhân viên")) {
            boolean isDeleted = (panel instanceof Employee) && employeeService.deleteEmployee(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}

