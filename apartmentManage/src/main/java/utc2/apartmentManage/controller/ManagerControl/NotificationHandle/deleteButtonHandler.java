package main.java.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2.apartmentManage.service.managerService.notificationIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.NotificationUI;


public class deleteButtonHandler {
    private JTable table;
    private JPanel panel;
    private final notificationIMP notificationService = new notificationIMP();
    
    
    public deleteButtonHandler(JTable table,JPanel panel) {
        this.table = table;
        this.panel = panel;
    }
    
    public void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        int id = (Integer) table.getValueAt(selectedRow, 0);
        
        if(notificationService.confirmDelete("thông báo")){
            boolean isDeleted = (panel instanceof NotificationUI) && notificationService.delete(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
