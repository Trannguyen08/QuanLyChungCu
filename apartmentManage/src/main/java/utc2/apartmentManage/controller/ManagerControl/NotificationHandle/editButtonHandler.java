package main.java.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.utc2.apartmentManage.model.Notification;
import main.java.utc2.apartmentManage.service.managerService.notificationIMP;

public class editButtonHandler {
    private JButton editBtn;
    private JTextField  notiTitle;
    private JTextArea content;
    private JComboBox<String> type;
    private JTable table;
    private JFrame edit;
    private final notificationIMP notificationService = new notificationIMP();
    
    public editButtonHandler(JTextField notiTitle, JComboBox<String> type, JTextArea content, 
                            JButton editBtn, JTable table, JFrame edit){
        this.editBtn =editBtn;
        this.notiTitle = notiTitle;
        this.content = content;
        this.table = table;
        this.edit = edit;
        this.type = type;
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
    }
    
    public void loadSelectRow() {
        notificationService.loadSelectedRowData(table, notiTitle, content, type);
    }
    
    public void updateSelectedRow() {
        if( !notificationService.addValidate(notiTitle, content) ) {
            return;
        }
        int selectedRow = table.getSelectedRow();
        int id = (Integer) table.getValueAt(selectedRow, 0);
        Notification noti = notificationService.getObject(id);
        noti.setType(type.getSelectedItem().toString().trim());
        noti.setTitle(notiTitle.getText().trim());
        noti.setMess(content.getText().trim());
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        edit.setVisible(false);
        if( notificationService.update(noti) ) {
            model.setValueAt(type.getSelectedItem().toString().trim(), selectedRow, 2);
            model.setValueAt(notiTitle.getText().trim(), selectedRow, 3);
            model.setValueAt(content.getText().trim(), selectedRow, 4);
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}
