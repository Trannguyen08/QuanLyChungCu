package main.java.utc2.apartmentManage.controller.UserControl.BillControl;

import javax.swing.*;
import main.java.utc2.apartmentManage.service.userService.billIMP;

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
