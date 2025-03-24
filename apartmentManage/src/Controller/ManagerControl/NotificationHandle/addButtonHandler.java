package Controller.ManagerControl.NotificationHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class addButtonHandler {
    private JButton addBtn;
    private  JComboBox<String> notiType;
    private JTextField notiID, ownerID, notiTitle, notification;
    private JTable table;
    private JFrame add;
    
    public addButtonHandler(JTextField notiTitle,JTextField notification,JComboBox<String> notiType, JTextField notiID, JTextField ownerID){
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
    
    private void addNewRow(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                null,
                ownerID.getText(),
                notiTitle.getText(),
                notification.getText(),
                notiType.getSelectedItem(),
                notiID.getText(),
                ownerID.getText(),
        });
        add.setVisible(false);
        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}
