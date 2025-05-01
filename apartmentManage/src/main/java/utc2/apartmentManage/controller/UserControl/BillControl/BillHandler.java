
package main.java.utc2.apartmentManage.controller.UserControl.BillControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.model.Resident;
import main.java.utc2.apartmentManage.service.userService.billIMP;
import main.java.utc2.apartmentManage.service.userService.infoIMP;
import main.java.utc2.apartmentManage.view.UserUI.searchWindow.searchBill;
import main.java.utc2.apartmentManage.view.UserUI.Pages.InvoiceHistoryUI;
import main.java.utc2.apartmentManage.view.UserUI.Pages.MoMoPayment;
import main.java.utc2.apartmentManage.view.UserUI.Pages.VNPAYQRCodeFrame;

public class BillHandler {
    private Account acc;
    private JButton searchIcon, payBtn, paidHistoryBtn, excelBtn;
    private JTable table;
    private final billIMP billService = new billIMP();
    private final infoIMP infoService = new infoIMP();

    public BillHandler(Account acc,JButton searchIcon, JButton payBtn, 
                       JButton paidHistoryBtn, JButton excelBtn, JTable table) {
        
        this.searchIcon = searchIcon;
        this.payBtn = payBtn;
        this.paidHistoryBtn = paidHistoryBtn;
        this.excelBtn = excelBtn;
        this.table = table;
        this.acc = acc;
        
        Resident r = infoService.getResidentByAccountID(acc.getId());
        
        this.paidHistoryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InvoiceHistoryUI(r.getResidentID()).setVisible(true);
            }
        });
        
        this.payBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try {
                    String payUrl = MoMoPayment.createPaymentAndGetQRCodeUrl();
                    VNPAYQRCodeFrame frame = new VNPAYQRCodeFrame(payUrl);
                    frame.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi tạo mã QR thanh toán MoMo!");
                }
            }
        });
        
        this.searchIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new searchBill(table, r.getResidentID()).setVisible(true);
            }
        });
    }

    

    
}