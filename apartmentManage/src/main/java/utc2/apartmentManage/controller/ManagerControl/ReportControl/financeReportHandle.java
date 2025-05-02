package main.java.utc2.apartmentManage.controller.ManagerControl.ReportControl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.java.utc2.apartmentManage.service.managerService.reportFinanceIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.ReportUI;


public class financeReportHandle {
    private JButton backBtn;
    private JTable chiTable, thuTable;
    private JComboBox<String> month, year;
    private JPanel thuPanel, chiPanel, mainPanel;
    private final reportFinanceIMP rf = new reportFinanceIMP();

    public financeReportHandle(JButton backBtn, JTable chiTable, JTable thuTable, 
                                JComboBox<String> month, JComboBox<String> year, 
                                JPanel thuPanel, JPanel chiPanel, JPanel mainPanel) {
        this.backBtn = backBtn;
        this.chiTable = chiTable;
        this.thuTable = thuTable;
        this.month = month;
        this.year = year;
        this.mainPanel = mainPanel;
        this.thuPanel = thuPanel;
        this.chiPanel = chiPanel;
        
        this.backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnClick();
            }
        });
        
        this.month.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTable(); 
            }
        });

        this.year.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTable(); 
            }
        });
        
        loadTable();
        
    }
    
    private void loadTable() {
        int monthNum = Integer.parseInt(month.getSelectedItem().toString());
        int yearNum = Integer.parseInt(year.getSelectedItem().toString());
        rf.setUpTable1(thuTable, monthNum, yearNum, thuPanel);
        rf.setUpTable2(chiTable, monthNum, yearNum, chiPanel);
    }
    
    private void backBtnClick() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ReportUI report = new ReportUI(mainPanel);
        mainPanel.add(report, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
}
