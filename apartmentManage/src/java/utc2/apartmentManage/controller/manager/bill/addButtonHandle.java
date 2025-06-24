package java.utc2.apartmentManage.controller.manager.bill;

import utc2.apartmentManage.model.BillManagerDetail;
import utc2.apartmentManage.service.implement.user.billIMP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class addButtonHandle {
    private JTextField name, price;
    private JFrame frame;
    private JTable table;
    private JButton addBtn;
    private JComboBox<String> month, year;
    private final billIMP billService = new billIMP();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    public addButtonHandle(JButton addBtn, JTextField name, JTextField price, JTable table, 
                            JComboBox<String> month, JComboBox<String> year, JFrame frame) {
        this.name = name;
        this.price = price;
        this.frame = frame;
        this.table = table;
        this.addBtn = addBtn;
        this.month = month;
        this.year = year;
        
        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
        
        
    }
    
    public static String getTodayDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(formatter);
    } 
    
    public void add() {
        String currentDate = getTodayDate(); // dd/MM/yyyy
        String[] dateParts = currentDate.split("/");

        int currentMonth = Integer.parseInt(dateParts[1]);
        int currentYear = Integer.parseInt(dateParts[2]);

        int selectedMonth = Integer.parseInt((String) month.getSelectedItem());
        int selectedYear = Integer.parseInt((String) year.getSelectedItem());

        BillManagerDetail bm = new BillManagerDetail(0, name.getText().trim(), 
                                                    Double.parseDouble(price.getText().trim()), 
                                                    currentDate);

        frame.setVisible(false);

        if (billService.add(bm)) {
           
            if (selectedMonth == currentMonth && selectedYear == currentYear) {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[] {bm.getName(), df.format(bm.getPrice()), bm.getPaidDate()});
            }

            JOptionPane.showMessageDialog(null,
                        "Thêm hóa đơn thành công",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                        "Thêm hóa đơn thất bại",
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}
