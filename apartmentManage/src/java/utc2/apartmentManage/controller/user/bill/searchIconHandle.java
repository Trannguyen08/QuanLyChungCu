package java.utc2.apartmentManage.controller.user.bill;

import utc2.apartmentManage.service.implement.user.billIMP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchIconHandle {
    private JButton searchBtn;
    private JComboBox<String> month, year, status;
    private int resID;
    private JTable table;
    private JFrame frame;
    private billIMP billService = new billIMP();

    public searchIconHandle(JComboBox<String> month, JComboBox<String> year, JComboBox<String> status,
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
        String monthStr = month.getSelectedItem().toString().trim();
        String yearStr = year.getSelectedItem().toString().trim();
        String statusStr = status.getSelectedItem().toString().trim();

        if (monthStr.isEmpty() && yearStr.isEmpty() && statusStr.isEmpty()) {
            frame.setVisible(false);
            return;
        }

        int monthNum = 0, yearNum = 0;
        if( !monthStr.isEmpty() ){
            monthNum = Integer.parseInt(monthStr);
        }
        if( !yearStr.isEmpty() ){
            yearNum = Integer.parseInt(yearStr);
        }
        
        frame.setVisible(false);
        boolean found = billService.filterBill(table, resID, monthNum, yearNum, statusStr);
        if (!found) {
            JOptionPane.showMessageDialog(frame, "Không tìm thấy kết quả phù hợp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            frame.setVisible(false);
        }
    }

}