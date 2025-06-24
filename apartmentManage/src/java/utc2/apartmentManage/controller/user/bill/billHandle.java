package java.utc2.apartmentManage.controller.user.bill;

import utc2.apartmentManage.api.vnpay.Config;
import utc2.apartmentManage.api.vnpay.ajaxServlet;
import utc2.apartmentManage.model.*;
import utc2.apartmentManage.service.export.excelExport;
import utc2.apartmentManage.service.implement.user.*;
import utc2.apartmentManage.view.user.detailWindow.detailBill;
import utc2.apartmentManage.view.user.pages.InvoiceHistoryUI;
import utc2.apartmentManage.view.user.searchWindow.searchBill;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class billHandle {
    private Account acc;
    private Resident r;
    private JButton searchIcon, payBtn, paidHistoryBtn, excelBtn, reload;
    private JTable table;
    private final billIMP billService = new billIMP();
    private final infoIMP infoService = new infoIMP();

    public billHandle(Account acc, JButton reload, JButton searchIcon, JButton payBtn,
                      JButton paidHistoryBtn, JButton excelBtn, JTable table) {
        
        this.searchIcon = searchIcon;
        this.payBtn = payBtn;
        this.paidHistoryBtn = paidHistoryBtn;
        this.excelBtn = excelBtn;
        this.table = table;
        this.acc = acc;
        this.reload = reload;
        this.r = infoService.getResidentByAccountID(acc.getId());
        
        int id = this.r.getResidentID();
        this.reload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billService.setUpTableBill(table, id);
            }
        });
        
        this.paidHistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InvoiceHistoryUI(r.getResidentID()).setVisible(true);
            }
        });
        
        this.excelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directoryPath = System.getProperty("user.dir") + File.separator + "data";
                excelExport.exportTableToExcelWithDirectory(directoryPath, table, "hoadon_" + r.getResidentID());
            }
        });
        
        this.payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payBtnClick();
            }
        });
        this.searchIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchBill(table, id).setVisible(true);
            }
        });
        
        billService.setUpTableBill(table, id);
        addEventDoubleClick();
    }
    public void payBtnClick() {
        if( !billService.isSelectedRow(table) ) {
            return;
        }
        int sel = table.getSelectedRow();
        if( table.getValueAt(sel, 4).toString().equals("Đã thanh toán") ) {
            JOptionPane.showMessageDialog(null, "Hóa đơn đã được thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Config config = new Config();
        try {
            String rawAmount = table.getValueAt(sel, 3).toString().replace(".", "");
            int billID = Integer.parseInt(table.getValueAt(sel, 0).toString());
            long amount = Long.parseLong(rawAmount);
            String url = ajaxServlet.createURL(amount, "NCB", "vn", billID);
            Desktop.getDesktop().browse(new URI(url));
            
            table.setValueAt("Đã thanh toán", sel, 4);
            LocalDate dueDate = billService.getDueDate(billID);
            LocalDate now = LocalDate.now();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String payDateStr = now.format(formatter); // Định dạng thành dd/MM/yyyy
            String note = now.isAfter(dueDate) ? "Quá hạn" : "Đúng hạn";

            billService.addInvoiceHistory(billID, payDateStr, note);
            billService.updateStatuc(billID);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void addEventDoubleClick() {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    int row = table.getSelectedRow();

                    // Giả sử bill_id ở cột 0 và deadline ở cột 3, chỉnh sửa nếu khác
                    Object billIDObj = table.getValueAt(row, 0);
                    int billID = Integer.parseInt(billIDObj.toString());
                    Object deadlineObj = table.getValueAt(row, 2);
                    String deadline = deadlineObj.toString();
                    new detailBill(billID, r.getResidentID(), "Hạn thanh toán: " + deadline).setVisible(true);
                    
                }
            }
        });

    }
 
}