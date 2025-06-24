package java.utc2.apartmentManage.service.implement.user;

import utc2.apartmentManage.model.*;
import utc2.apartmentManage.repository.user.billRepository;
import utc2.apartmentManage.service.interfaces.ITable;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class billIMP implements ITable<Bill> {
    private final billRepository billRepo = new billRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    
    public LocalDate getDueDate(int billID) {
        return billRepo.getDueDate(billID);
    }
    
    public boolean addInvoiceHistory(int billID, String payDate, String note) {
        return billRepo.addInvoiceHistory(billID, payDate, note);
    }
    
    public boolean updateStatuc(int billID) {
        return billRepo.updateBillStatus(billID);
    }
    
    
    public void setUpTablePaidHistory(JTable table, int resID){
        List<PaidHistory> paidList = billRepo.getAllPaidHistory(resID);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);  
        model.setRowCount(0);
        
        for (PaidHistory p : paidList) {
            model.addRow(new Object[]{
                p.getBill_id(),
                p.getPaidDate(),
                df.format(p.getAmount()),
                p.getNote()
            });
        }
        setFont(table);
    }
    
    public void setUpTableBillManager(JTable table, int month, int year, String status) {
        List<BillManager> list = billRepo.getAllBillForManager(month, year, status);
        DefaultTableModel model = (DefaultTableModel) table.getModel();  
        model.setRowCount(0);
        if( list.isEmpty() ) {
            System.out.println("rong");
        }
        
        for (BillManager bm : list) {
            model.addRow(new Object[]{
                bm.getBillId(),
                bm.getApartmentId(),
                bm.getFullName(),
                bm.getDueDate(),
                df.format(bm.getTotalAmount()),
                bm.getStatus(),
                bm.getPaymentDate()
            });
        }
        setFont(table);
    }
    
    public void setUpTableBillManagerDetail(JTable table, int month, int year){
        List<BillManagerDetail> list = billRepo.getAllBillManagerDetail(month, year);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);  
        model.setRowCount(0);
        
        for (BillManagerDetail p : list) {
            model.addRow(new Object[]{
                p.getName(),
                df.format(p.getPrice()),
                p.getPaidDate()
            });
        }
        setFont(table);
    }
    
    public void searchTable(JTable table, JTextField searchField) {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập id hóa đơn, căn hộ, chủ hộ...")) {
            sorter.setRowFilter(null); // không lọc gì
        } else {
            RowFilter<DefaultTableModel, Object> idBillFilter = RowFilter.regexFilter("(?i)" + text, 0); 
            RowFilter<DefaultTableModel, Object> idAPTFilter = RowFilter.regexFilter("(?i)" + text, 1);
            RowFilter<DefaultTableModel, Object> ownerFilter = RowFilter.regexFilter("(?i)" + text, 2);

            // Kết hợp 2 bộ lọc bằng OR (chỉ cần khớp 1 trong 2 cột)
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(Arrays.asList(idBillFilter, idAPTFilter, ownerFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }
    public void searchTable2(JTable table, JTextField searchField) {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập tên dịch vụ...")) {
            sorter.setRowFilter(null); // không lọc gì
        } else {
            RowFilter<DefaultTableModel, Object> idBillFilter = RowFilter.regexFilter("(?i)" + text, 0); 

            // Kết hợp 2 bộ lọc bằng OR (chỉ cần khớp 1 trong 2 cột)
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(Arrays.asList(idBillFilter));
            sorter.setRowFilter(combinedFilter);
        }
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
    
    public boolean add(BillManagerDetail bm) {
        return billRepo.add(bm);
    }

   

    

    

}