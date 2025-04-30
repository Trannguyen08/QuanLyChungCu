
package main.java.utc2_apartmentManage.controller.UserControl;


import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.*;
import main.java.utc2.apartmentManage.model.Bill;
import main.java.utc2.apartmentManage.service.userService.billService;
import main.java.utc2.apartmentManage.util.ScannerUtil;



public class searchIconBillHandler {
    private JTextField billId, totalAmount, to_totalAmount;
    private JButton searchBtn;
    private JDateChooser billDate, dueDate;
    private JComboBox<String> status;
    private JTable table;
    private JFrame frame;
    private billService billService = new billService();

    public searchIconBillHandler(JTextField billId, JTextField totalAmount,
                             JButton searchBtn, JDateChooser billDate, JDateChooser dueDate, 
                             JComboBox<String> status, JTable table, JFrame frame ,JTextField to_talAmount) {
        
        this.billId = billId;
        this.totalAmount = totalAmount;
        this.to_totalAmount = to_talAmount;
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
        boolean check = billService.validateSeachInput(billId, totalAmount,billDate,
                                                         dueDate, status, to_totalAmount);
        
        if( !check ) {
            return;
        }
        
        if( billService.checkAllNull(billId, totalAmount, billDate, 
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
        
        String billDateValue = ScannerUtil.convertJDateChooserToString(billDate);
        String dueDateValue = ScannerUtil.convertJDateChooserToString(dueDate);
        
        Double minTotalAmount = (totalAmount.getText() == null || totalAmount.getText().trim().isEmpty())
                ? null : ScannerUtil.replaceDouble(totalAmount);

        Double maxTotalAmount = (to_totalAmount.getText() == null || to_totalAmount.getText().trim().isEmpty())
                ? null : ScannerUtil.replaceDouble(to_totalAmount);
        
        // Tạo đối tượng Bill và gọi phương thức lọc
        Bill bill = new Bill(id, minTotalAmount, 
                             billDateValue, dueDateValue, status.getSelectedItem().toString().trim());

        boolean checkRun = billService.filterBillByIcon(bill, maxTotalAmount, table);
        
        frame.setVisible(false);
        if( !checkRun ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", 
                                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}