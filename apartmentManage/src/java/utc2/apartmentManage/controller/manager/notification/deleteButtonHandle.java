package java.utc2.apartmentManage.controller.manager.notification;

import utc2.apartmentManage.service.implement.manager.notificationIMP;
import utc2.apartmentManage.view.manager.pages.NotificationUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class deleteButtonHandle {
    private JTable table;
    private JPanel panel;
    private final notificationIMP notificationService = new notificationIMP();
    
    
    public deleteButtonHandle(JTable table, JPanel panel) {
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
