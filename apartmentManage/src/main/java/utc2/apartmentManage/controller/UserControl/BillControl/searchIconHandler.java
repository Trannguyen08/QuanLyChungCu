package main.java.utc2.apartmentManage.controller.UserControl.BillControl;



import main.java.utc2.apartmentManage.service.userService.billIMP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;




public class searchIconHandler {
    private JButton searchBtn;
    private JComboBox<String> month, year, status;
    private int resID;
    private JTable table;
    private JFrame frame;
    private billIMP billService = new billIMP();

    public searchIconHandler(JComboBox<String> month, JComboBox<String> year, JComboBox<String> status, 
                              JButton searchBtn, int resID, JTable table, JFrame frame ) {
        
        this.searchBtn = searchBtn;
        this.month = month;
        this.year = year;
        this.status = status;
        this.resID = resID;
        this.table = table;
        this.frame = frame;

        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
        
    }
    
    
    
    public void filterTableData() {
        String monthStr = month.getSelectedItem() != null ? month.getSelectedItem().toString().trim() : "";
        String yearStr = year.getSelectedItem() != null ? year.getSelectedItem().toString().trim() : "";
        String statusStr = status.getSelectedItem() != null ? status.getSelectedItem().toString().trim() : "";

        if (monthStr.isEmpty() || yearStr.isEmpty() || statusStr.isEmpty()) {
            frame.setVisible(false);
            return;
        }

        int monthNum = Integer.parseInt(monthStr);
        int yearNum = Integer.parseInt(yearStr);

        frame.setVisible(false);
        if (!billService.filterBill(table, resID, monthNum, yearNum, statusStr)) {
            JOptionPane.showMessageDialog(frame, "Không tìm thấy kết quả phù hợp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}