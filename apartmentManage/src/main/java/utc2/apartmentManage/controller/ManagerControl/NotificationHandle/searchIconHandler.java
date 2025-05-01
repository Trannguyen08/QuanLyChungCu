package main.java.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.utc2.apartmentManage.model.Notification;
import main.java.utc2.apartmentManage.service.managerService.notificationIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;


public class searchIconHandler {
    private JTextField title;
    private JComboBox<String> type, recipant;
    private JDateChooser date;
    private JTable table;
    private JFrame frame;
    private JButton searchBtn;
    private final notificationIMP notificationService = new notificationIMP();

    public searchIconHandler(JComboBox<String> recipant, JTextField title, JComboBox<String> type, JDateChooser date, 
                            JButton searchBtn, JTable table, JFrame frame) {
      
        this.title = title;
        this.type = type;
        this.date = date;
        this.frame = frame;
        this.table = table;
        this.searchBtn = searchBtn;
        this.recipant = recipant;

        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    } 

   
    public void filterTableData() {
        if( !notificationService.searchValidate(date) ){
            return;
        }
        
        Notification noti = new Notification(0, recipant.getSelectedItem().toString().trim(),
                                             type.getSelectedItem().toString().trim(),
                                             title.getText().trim(), "", 
                                             ScannerUtil.convertJDateChooserToString(date), 0);
        frame.setVisible(false);
        boolean checkFilter = notificationService.filterNotification(table, noti);
        if( !checkFilter ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
        
}
