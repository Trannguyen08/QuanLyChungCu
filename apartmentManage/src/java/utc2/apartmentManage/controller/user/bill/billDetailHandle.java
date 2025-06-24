package java.utc2.apartmentManage.controller.user.bill;

import utc2.apartmentManage.model.BillDetail;
import utc2.apartmentManage.repository.user.billRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class billDetailHandle {
    private int bill_id, resident_id;
    private String dealineS;
    private JLabel aptID, billID, dealine, totalAmount;
    private JTable table;
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    public billDetailHandle(int bill_id, int resident_id, String dealineS, JLabel aptID, 
                            JLabel billID, JLabel totalAmount, JLabel dealine, JTable table) {
        this.bill_id = bill_id;
        this.resident_id = resident_id;
        this.aptID = aptID;
        this.billID = billID;
        this.dealine = dealine;
        this.table = table;
        this.dealineS = dealineS;
        this.totalAmount = totalAmount;
        

        setUpTable(table);
        
    }
    
    public void setUpTable(JTable table) {
        List<BillDetail> list = new billRepository().getAllDetailByBillID(bill_id);
        addData(table, list);
        setFont(table);
        
        aptID.setText("Căn hộ của cư dân:  " + resident_id);
        billID.setText("Số: " + bill_id);
        dealine.setText(dealineS);

        double total = 0.0;
        for (BillDetail detail : list) {
            total += detail.getAmount();
        }
        
        totalAmount.setText("Tổng tiền: " + df.format(total) + " VNĐ");
    }

    public void addData(JTable table, List<BillDetail> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (BillDetail bd : list) {
            model.addRow(new Object[]{
                    bd.getName(),
                    df.format(bd.getPrice()),
                    bd.getNum(),
                    df.format(bd.getAmount())
            });
        }
    }

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

}
