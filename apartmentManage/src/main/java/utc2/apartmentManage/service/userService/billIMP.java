package main.java.utc2.apartmentManage.service.userService;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;
import main.java.utc2.apartmentManage.model.Bill;
import main.java.utc2.apartmentManage.model.PaidHistory;
import main.java.utc2.apartmentManage.repository.UserRepository.billRepository;
import main.java.utc2.apartmentManage.service.Interface.ITable;

public class billIMP implements ITable<Bill> {
    private final billRepository billRepo = new billRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

  
    public void setUpTablePaidHistory(JTable table, int resID){
        List<PaidHistory> paidList = billRepo.getAllPaidHistory(resID);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);  
        model.setRowCount(0);
        
        for (PaidHistory p : paidList) {
            model.addRow(new Object[]{
                p.getBill_id(),
                p.getPaidDate(),
                p.getAmount(),
                p.getNote()
            });
        }
        setFont(table);
    }
    
    @Override
    public void setUpTable(JTable table) { }
    
    public void setUpTableBill(JTable table, int resID) {
        List<Bill> billList = billRepo.getAllBills(resID);
        addData(table, billList);
        setFont(table);
    }
    
    @Override
    public void addData(JTable table, List<Bill> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);  
        model.setRowCount(0);
        
        for (Bill b : list) {
            model.addRow(new Object[]{
                b.getBillId(),
                b.getBillDate(),
                b.getDueDate(),
                df.format(b.getTotalAmount()),
                b.getStatus()
            });
        }
    }
    
    @Override
    public void setFont(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader()
                                    .getDefaultRenderer()).
                                    setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public boolean isSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    public boolean filterBill(JTable table, int resID, int month, int year, String status) {
        List<Bill> list = billRepo.filteredBill(resID, month, year, status);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null); 
        model.setRowCount(0);

        if (list.isEmpty()) {
            System.out.println("Danh sách rỗng");
            return false;
        } else {
            System.out.println(list);
        }

        addData(table, list);
        return true;
    }

   

    

    

}