package java.utc2.apartmentManage.controller.manager.employee;

import utc2.apartmentManage.service.implement.manager.employeeIMP;
import utc2.apartmentManage.view.manager.pages.EmployeeUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class deleteButtonHandle {
    private JTable table;
    private JPanel panel;
    private final employeeIMP employeeService = new employeeIMP();
    
    public deleteButtonHandle(JTable table, JPanel panel) {
        this.table = table;
        this.panel = panel;
        
    }
    public void deleteSelectedRow() {
        if( !employeeService.isSelectedRow(table) ) {
            return;
        }
        Integer id = employeeService.getEmployeeId(table);
        if( id == null ) {
            return;
        }
        if (employeeService.confirmDelete("nhân viên")) {
            boolean isDeleted = (panel instanceof EmployeeUI) && employeeService.delete(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}

