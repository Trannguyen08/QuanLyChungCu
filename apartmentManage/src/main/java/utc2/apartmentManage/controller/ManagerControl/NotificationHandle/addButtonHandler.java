package main.java.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import main.java.utc2.apartmentManage.model.Notification;
import main.java.utc2.apartmentManage.service.managerService.notificationService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class addButtonHandler {
    private JButton addBtn;
    private JComboBox<String> type;
    private JTextField title;
    private JTextArea content;
    private JTable table;
    private JFrame add;
    private final notificationService notificationService = new notificationService();

    public addButtonHandler(JComboBox<String> type, JTextField title, 
                             JTextArea content, JButton addBtn, JTable table, JFrame add) {
        this.addBtn = addBtn;
        this.type = type;
        this.title = title;
        this.content = content;
        this.table = table;
        this.add = add;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }

    private void addNewRow() {
        if( title.getText().trim().isEmpty() || content.getText().trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        
        int id = notificationService.getNewID();

        Notification noti = new Notification(id, type.getSelectedItem().toString().trim(),
                                             title.getText().trim(), content.getText().trim(),
                                              notificationService.getTodayDate());


        // Thêm vào cơ sở dữ liệu
        boolean isAddedComplete = notificationService.addNotification(noti);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.addRow(new Object[]{noti.getID(), noti.getTitle(), noti.getMess(), noti.getType(), noti.getSentDate()});

        if (isAddedComplete) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        add.setVisible(false);
    }
}
