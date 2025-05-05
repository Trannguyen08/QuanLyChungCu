package main.java.utc2.apartmentManage.controller.ManagerControl.BillControl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import main.java.utc2.apartmentManage.service.export.BillExport;
import main.java.utc2.apartmentManage.service.export.Excel;
import main.java.utc2.apartmentManage.service.userService.billIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.BillUI;

public class billAllUserHandle {
    private JButton excelBtn, printBtn, backBtn;
    private JComboBox<String> month, year, status;
    private JTextField searchField;
    private JTable table;
    private JPanel mainPanel;
    private final billIMP billService = new billIMP();

    public billAllUserHandle(JButton backBtn, JButton excelBtn, JButton printBtn, JComboBox<String> month, JComboBox<String> year, 
                        JComboBox<String> status, JTextField searchField, JTable table, JPanel mainPanel) {
        
        this.excelBtn = excelBtn;
        this.printBtn = printBtn;
        this.month = month;
        this.year = year;
        this.status = status;
        this.searchField = searchField;
        this.table = table;
        this.mainPanel = mainPanel;
        this.backBtn = backBtn;
        
        this.backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnClick();
            }
        });
        
        this.month.addActionListener(e -> {
            loadData();
        });

        this.year.addActionListener(e -> {
            loadData();
        });
        
        this.status.addActionListener(e -> {
            loadData();
        });
        
        this.excelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directoryPath = System.getProperty("user.dir") + File.separator + "data";
                Excel.exportTableToExcelWithDirectory(directoryPath, table, "bill");
            }
        });
        
        this.printBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printBtnClick();
            }
        });
        
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                billService.searchTable(table, searchField);
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                billService.searchTable(table, searchField);
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                billService.searchTable(table, searchField);
            }
        });
        
        loadData();
        placeHolder();
    }
    
    public void printBtnClick() {
        if( !billService.isSelectedRow(table) ) {
            return;
        }
        int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        String filePath = directoryPath + File.separator + "hoadon_" + id + ".pdf";
        new BillExport().exportBillToPDF(id, filePath);
    }
    
    private void backBtnClick() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        BillUI report = new BillUI(mainPanel);
        mainPanel.add(report, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void loadData() {
        int monthNum = Integer.parseInt(month.getSelectedItem().toString());
        int yearNum = Integer.parseInt(year.getSelectedItem().toString());
        String statusVal = status.getSelectedItem().toString();
        billService.setUpTableBillManager(table, monthNum+1, yearNum, statusVal);
        
    }
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id hóa đơn, căn hộ, chủ hộ...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id hóa đơn, căn hộ, chủ hộ...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id hóa đơn, căn hộ, chủ hộ...");
                }
            }
        });
    }
    
    
}
