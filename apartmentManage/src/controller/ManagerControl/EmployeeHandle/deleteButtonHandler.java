package controller.ManagerControl.EmployeeHandle;

import dao.managerDAO.EmployeeDAO;
import service.managerService.employeeService;
import view.ManagerUI.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final employeeService employeeService = new employeeService();
    
    public deleteButtonHandler(JButton deleteBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;
        
        for (ActionListener al : deleteBtn.getActionListeners()) {
            deleteBtn.removeActionListener(al);
        }

        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });
    }

    private void deleteSelectedRow() {
        Integer id = employeeService.getEmployeeId(table);
        if( id == null ) {
            return;
        }
        if (employeeService.confirmDelete()) {
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

