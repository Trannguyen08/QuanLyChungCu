package java.utc2.apartmentManage.controller.manager.bill;

import utc2.apartmentManage.api.mail.SendMail;
import utc2.apartmentManage.service.export.billExport;
import utc2.apartmentManage.service.export.excelExport;
import utc2.apartmentManage.service.implement.user.billIMP;
import utc2.apartmentManage.view.manager.pages.BillUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

public class billAllUserHandle {
    private JButton excelBtn, printBtn, backBtn, notiBtn;
    private JComboBox<String> month, year, status;
    private JTextField searchField;
    private JTable table;
    private JPanel mainPanel;
    private final billIMP billService = new billIMP();

    public billAllUserHandle(JButton notiBtn, JButton backBtn, JButton excelBtn, JButton printBtn, JComboBox<String> month, JComboBox<String> year, 
                        JComboBox<String> status, JTextField searchField, JTable table, JPanel mainPanel) {
        
        this.excelBtn = excelBtn;
        this.printBtn = printBtn;
        this.month = month;
        this.year = year;
        this.status = status;
        this.searchField = searchField;
        this.table = table;
        this.mainPanel = mainPanel;
        this.notiBtn = notiBtn;
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
                excelExport.exportTableToExcelWithDirectory(directoryPath, table, "bill");
            }
        });
        
        this.printBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printBtnClick();
            }
        });
        
        this.notiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SendMail().send();
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
        
        placeHolder();
        loadData();
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
        timer.start();
    }
    
    public void printBtnClick() {
        if( !billService.isSelectedRow(table) ) {
            return;
        }
        int id = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        String filePath = directoryPath + File.separator + "hoadon_" + id + ".pdf";
        new billExport().exportBillToPDF(id, filePath);
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
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (searchField.getText().equals("Nhập id hóa đơn, căn hộ, chủ hộ...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id hóa đơn, căn hộ, chủ hộ...");
                }
            }
        });
    }
    
    
}
