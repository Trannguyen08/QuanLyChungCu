package java.utc2.apartmentManage.controller.manager.notification;

import utc2.apartmentManage.model.Notification;
import utc2.apartmentManage.service.implement.manager.notificationIMP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class addButtonHandle {
    private JButton addBtn;
    private JComboBox<String> type, recipant;
    private JTextField title;
    private JTextArea content;
    private JTable table;
    private JFrame add;
    private final notificationIMP notificationService = new notificationIMP();

    public addButtonHandle(JComboBox<String> recipant, JComboBox<String> type, JTextField title,
                           JTextArea content, JButton addBtn, JTable table, JFrame add) {
        this.addBtn = addBtn;
        this.type = type;
        this.title = title;
        this.content = content;
        this.table = table;
        this.add = add;
        this.recipant = recipant;

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String todayDate = sdf.format(new Date());

        Notification noti = new Notification(id, recipant.getSelectedItem().toString().trim(),
                                            type.getSelectedItem().toString().trim(),
                                             title.getText().trim(), content.getText().trim(),
                                              LocalDateTime.now(), 0);


        // Thêm vào cơ sở dữ liệu
        boolean isAddedComplete = notificationService.add(noti);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.addRow(new Object[]{noti.getID(), noti.getRecipant(), noti.getType(), noti.getTitle(), 
                                noti.getMess(), noti.getSentDate()});

        add.setVisible(false);
        if (isAddedComplete) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
