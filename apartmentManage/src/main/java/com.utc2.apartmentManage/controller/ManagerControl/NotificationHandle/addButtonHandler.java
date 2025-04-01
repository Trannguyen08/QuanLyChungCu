package main.java.com.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.com.utc2.apartmentManage.model.Notification;
import main.java.com.utc2.apartmentManage.service.managerService.notificationService;

public class addButtonHandler {
    private JButton addBtn;
    private JComboBox<String> notiType;
    private JTextField notiID, ownerID, notiTitle, notification;
    private JTable table;
    private JFrame add;
    private final notificationService notificationService = new notificationService();

    public addButtonHandler(JButton addBtn, JTextField notiTitle, JTextField notification, JComboBox<String> notiType,
                            JTextField notiID, JTextField ownerID, JTable table, JFrame add) {
        this.addBtn = addBtn;
        this.notiTitle = notiTitle;
        this.notification = notification;
        this.notiType = notiType;
        this.notiID = notiID;
        this.ownerID = ownerID;
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
        String idText = notiID.getText().trim();
        String ownerText = ownerID.getText().trim();
        String titleText = notiTitle.getText().trim();
        String messText = notification.getText().trim();
        String typeText = notiType.getSelectedItem().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (idText.isEmpty() || ownerText.isEmpty() || titleText.isEmpty() || messText.isEmpty() || typeText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(idText);
        int owner = Integer.parseInt(ownerText);

        Notification noti = new Notification(id, owner, titleText, messText, typeText);


        // Thêm vào cơ sở dữ liệu
        boolean isAddedComplete = notificationService.addNotification(noti);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.addRow(new Object[]{noti.getID(), noti.getOwnerID(), noti.getTitle(), noti.getMess(), noti.getType()});

        if (isAddedComplete) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        add.setVisible(false);
    }
}
