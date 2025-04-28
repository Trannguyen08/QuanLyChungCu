
package main.java.utc2_apartmentManage.service.userService;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import main.java.utc2_apartmentManage.model.Bill;
import main.java.utc2_apartmentManage.model.Service;
import main.java.utc2_apartmentManage.util.ScannerUtil;
import main.java.utc2_apartmentManage.repository.UserRepository.billRepository;

public class billService {
    private final billRepository billDAO = new billRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

  
    // load dữ liệu vào bảng
    public void setupBilltTable(JTable table) {
        List<Bill> billList = billDAO.getAllBills(101);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        addToTable(billList, table);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    // check select từ table
    public boolean notification(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }


    // lấy id
    public Integer getBillId(JTable table) {
        notification(table);
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object value = model.getValueAt(selectedRow, 0);
        try {
            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public boolean checkSelectRow(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean validateSeachInput(JTextField billId, JTextField totalAmount,
                                    JDateChooser billDate, JDateChooser dueDate, JComboBox<String> status,
                                    JTextField to_totalAmount) {

        if (billId.getText() != null && !billId.getText().trim().isEmpty() &&
            !ScannerUtil.validateInteger(billId.getText().trim(), "ID hóa đơn")) {
            return false;
        }
        if (totalAmount.getText() != null && !totalAmount.getText().trim().isEmpty() &&
            !ScannerUtil.validateDouble(totalAmount.getText().trim(), "Tổng số tiền hóa đơn")) {
            return false;
        }
        if (to_totalAmount != null && !to_totalAmount.getText().trim().isEmpty() &&
            !ScannerUtil.validateDouble(to_totalAmount.getText().trim(), "Tổng số tiền hoa đơn")) {
            return false;
        }
        if (!totalAmount.getText().trim().isEmpty() && !totalAmount.getText().trim().isEmpty() &&
            !ScannerUtil.validateRange(totalAmount.getText().trim(), totalAmount.getText().trim(), "Tổng số tiền hóa đơn")) {
            return false;
        }
        if (billDate.getDate() != null && dueDate.getDate() != null) {
            Date billDateValue = billDate.getDate();
            Date dueDateValue = dueDate.getDate();

            // Kiểm tra nếu ngày lập hóa đơn sau ngày đến hạn
            if (billDateValue.after(dueDateValue)) {
                JOptionPane.showMessageDialog(null, "Ngày lập hóa đơn không thể lớn hơn ngày đến hạn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Chuyển Date thành LocalDate để dễ kiểm tra khoảng cách
            LocalDate startLocal = billDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endLocal = dueDateValue.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        }
        return true;
    }
    
    public void addToTable( List<Bill> billList, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);  
        model.setRowCount(0);
        
        for (Bill b : billList) {
            model.addRow(new Object[]{
                b.getBillId(),
                b.getBillDate(),
                b.getDueDate(),
                df.format(b.getTotalAmount()),
                b.getStatus()
            });
        }
        
    }
    
    public boolean filterBillByIcon(Bill bill,  Double to_totalAmount, JTable table) {
        List<Bill> billList = billDAO.getFilteredBills(bill, to_totalAmount);
        addToTable(billList, table);
        return !billList.isEmpty();
    }
    
    public boolean filterBillByKeyword(int id, JTable table) {
        List<Bill> bills = billDAO.getFilteredBillsByID(id);
        addToTable(bills, table);
        return !bills.isEmpty(); 
    }
    
        
        
    public boolean checkAllNull(JTextField billId, JTextField totalAmount,
                             JDateChooser billDate, JDateChooser dueDate,JComboBox<String> status,
                             JTextField to_totalAmount ) {
    
    if (billId.getText().trim().isEmpty()  
        || totalAmount.getText().trim().isEmpty() || to_totalAmount.getText().trim().isEmpty()
        || status.getSelectedItem().toString().trim().isEmpty()) {
        return true;
    }

        Date billDateValue = billDate.getDate();
        Date dueDateValue = dueDate.getDate();
        if (billDateValue == null || dueDateValue == null) {
            return true;
        }

    double minTotalAmount = 0, maxTotalAmount = Double.MAX_VALUE;

    try {
        if (!totalAmount.getText().trim().isEmpty()) {
            minTotalAmount = Double.parseDouble(totalAmount.getText().trim());
        }
        if (!to_totalAmount.getText().trim().isEmpty()) {
            maxTotalAmount = Double.parseDouble(to_totalAmount.getText().trim());
        }

        if (minTotalAmount > maxTotalAmount) {
            JOptionPane.showMessageDialog(null, "Tổng tiền tối thiểu không thể lớn hơn tổng tiền tối đa!", 
                                          "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return true;
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ cho tổng số tiền!", 
                                      "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        return true;
    }

    return false;
    }

}