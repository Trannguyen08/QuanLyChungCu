package java.utc2.apartmentManage.controller.user.bill;

import utc2.apartmentManage.service.implement.user.billIMP;

import javax.swing.*;

public class paidHistoryHandle {
    private int resID;
    private JTable table;
    private final billIMP billService = new billIMP();

    public paidHistoryHandle(int resID, JTable table) {
        this.resID = resID;
        this.table = table;  
        
        billService.setUpTablePaidHistory(table, resID);
    }
}
