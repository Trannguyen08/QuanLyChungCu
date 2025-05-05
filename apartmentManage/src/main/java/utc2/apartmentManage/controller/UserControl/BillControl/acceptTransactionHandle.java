package main.java.utc2.apartmentManage.controller.UserControl.BillControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.*;
import main.java.utc2.apartmentManage.repository.UserRepository.billRepository;

public class acceptTransactionHandle {
    private int id;
    private String total, dueDate, humanName;
    private JButton acceptBtn;
    private JLabel billid, date, money, human;
    private JTable table;
    private JFrame frame;
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    private billRepository billRepo = new billRepository();

    public acceptTransactionHandle(JTable table, int id, String humanName, String total, String dueDate, JButton acceptBtn, 
                                    JLabel billid, JLabel human, JLabel date, JLabel money, JFrame frame) {
        this.id = id;
        this.total = total;
        this.acceptBtn = acceptBtn;
        this.billid = billid;
        this.date = date;
        this.money = money;
        this.dueDate = dueDate;
        this.frame = frame;
        this.table = table;
        this.human = human;
        this.humanName = humanName;
        
        loadData();
        
        this.acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptBtnClick();
            }
        });
        
        
    }
    
    public void acceptBtnClick() {
        String today = getTodayString();
        String note;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate todayDate = LocalDate.parse(today, formatter);
        LocalDate due = LocalDate.parse(dueDate, formatter);

        if (!todayDate.isAfter(due)) {
            note = "Đúng hạn";
        } else {
            note = "Quá hạn";
        }

        boolean added = billRepo.addInvoiceHistory(id, today, note) && billRepo.updateBillById(id, "Đã thanh toán");
        frame.setVisible(false);
        if (added) {
            table.setValueAt("Đã thanh toán", table.getSelectedRow(), 4);
            JOptionPane.showMessageDialog(null, "Xác nhận thanh toán thành công!");
        } else {
            JOptionPane.showMessageDialog(null, "Xác nhận thanh toán thất bại!");
        }
    }

    
    public void loadData() {
        billid.setText("Số hóa đơn: " + id);
        human.setText("Người thanh toán: " + humanName);
        date.setText("Ngày thanh toán: " + getTodayString());
        money.setText(total);
    }

    public String getTodayString() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(formatter);
    }
    
    
}
