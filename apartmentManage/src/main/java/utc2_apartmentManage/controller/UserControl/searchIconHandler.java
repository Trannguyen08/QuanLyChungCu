
package main.java.utc2_apartmentManage.controller.UserControl;

import main.java.utc2_apartmentManage.model.Bill;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;
import main.java.utc2_apartmentManage.service.userService.billService;


public class searchIconHandler {
    private JTextField billId, apartmentId, totalAmount, to_totalAmount;
    private JButton searchBtn;
    private JDateChooser billDate, dueDate;
    private JComboBox<String> status;
    private JTable table;
    private JFrame frame;
    private billService billService = new billService();

    public searchIconHandler(JTextField billId, JTextField apartmentId, JTextField totalAmount,
                             JButton searchBtn, JDateChooser billDate, JDateChooser dueDate, 
                             JComboBox<String> status, JTable table, JFrame frame ,JTextField to_talAmount) {
        
        this.billId = billId;
        this.apartmentId = apartmentId;
        this.totalAmount = totalAmount;
        this.to_totalAmount = to_totalAmount;
        this.searchBtn = searchBtn;
        this.billDate = billDate;
        this.dueDate = dueDate;
        this.status = status;
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
        boolean check = billService.validateSeachInput(billId, apartmentId, totalAmount,billDate,
                                                         dueDate, status, to_totalAmount);
        
        if( !check ) {
            return;
        }
        
        if( billService.checkAllNull(billId, apartmentId, totalAmount, billDate, 
                                    dueDate, status, to_totalAmount) ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", 
                                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(false);
            return;
        }
        // Lấy ID của hóa đơn
        int id = 0;
        String idText = billId.getText().trim();
        if( !idText.isEmpty() ) { 
            id = Integer.parseInt(idText);
            System.out.println(id);
        }
        
        // Lấy ID căn hộ
        int apartmentIdInt = 0;
        String apartmentIdText = apartmentId.getText().trim();
        if( !apartmentIdText.isEmpty() ) {
            apartmentIdInt = Integer.parseInt(apartmentIdText);
            System.out.println(apartmentIdInt);
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (billDate.getDate() == null && dueDate.getDate() != null) {
                billDate.setDate(dateFormat.parse("2000-01-01"));
            } else if (billDate.getDate() != null && dueDate.getDate() == null) {
                dueDate.setDate(dateFormat.parse("2100-01-01"));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

       // Xử lý giá trị tiền
        double minTotalAmount = 0, maxTotalAmount = Double.MAX_VALUE;
        try {
            if (!totalAmount.getText().trim().isEmpty()) {
                minTotalAmount = Double.parseDouble(totalAmount.getText().trim());
            }
            if (!to_totalAmount.getText().trim().isEmpty()) {
                maxTotalAmount = Double.parseDouble(to_totalAmount.getText().trim());
            }

            // Đảm bảo minTotalAmount không lớn hơn maxTotalAmount
            if (minTotalAmount > maxTotalAmount) {
                JOptionPane.showMessageDialog(null, "Giá trị tối thiểu không thể lớn hơn giá trị tối đa!", 
                                              "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ cho tổng số tiền!", 
                                          "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Tạo đối tượng Bill và gọi phương thức lọc
        Bill bill = new Bill(id, apartmentIdInt, minTotalAmount, 
                             "", "", status.getSelectedItem().toString().trim());

        boolean checkRun = billService.filterBills(bill, minTotalAmount, maxTotalAmount, 
                                         billDate, dueDate, table);
        
        frame.setVisible(false);
        JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", 
                                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}