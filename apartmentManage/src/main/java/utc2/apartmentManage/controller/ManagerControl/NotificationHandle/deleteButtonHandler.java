package main.java.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.utc2.apartmentManage.service.managerService.notificationService;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.NotificationUI;


public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final notificationService notificationService = new notificationService();
    
    
    public deleteButtonHandler(JButton deleteBtn, JTable table,JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;

        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });
    }
    
    public void deleteSelectedRow() {
        Integer id = notificationService.getNotificationID(table);
        if(id == null){
            return;
        }
        if(notificationService.confirmDelete("thông báo")){
            boolean isDeleted = (panel instanceof NotificationUI) && notificationService.deleteNotification(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
