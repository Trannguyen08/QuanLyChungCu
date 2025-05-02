package main.java.utc2.apartmentManage.controller.ManagerControl.ReportControl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.java.utc2.apartmentManage.model.EmployeeInfo;
import main.java.utc2.apartmentManage.service.managerService.reportEmployeeIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.ReportUI;
import main.java.utc2.apartmentManage.view.ManagerUI.detailWindow.employeeDetail;
import main.java.utc2.apartmentManage.service.export.Excel;

public class employeeReportHandle {
    private JButton backBtn, detailBtn, excelBtn;
    private JTextField searchField;
    private JComboBox<String> month, year;
    private JTable table;
    private JPanel mainPanel;
    private final reportEmployeeIMP reportService = new reportEmployeeIMP();

    public employeeReportHandle(JButton backBtn, JButton detailBtn, JButton excelBtn, JComboBox<String> month,
                                JComboBox<String> year, JTextField searchField, JTable table, JPanel panel) {
        this.backBtn = backBtn;
        this.detailBtn = detailBtn;
        this.excelBtn = excelBtn;
        this.searchField = searchField;
        this.table = table;
        this.mainPanel = panel;
        this.month = month;
        this.year = year;
        
        this.backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnClick();
            }
        });
        
        this.excelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String directoryPath = System.getProperty("user.dir") + File.separator + "data";
                Excel.exportTableToExcelWithDirectory(directoryPath, table);
                
            }
        });
        
        
        this.detailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailBtnClick();
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
        
        placeHolder();
        loadTable();
        
        
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }
        });
    }
    
    public void loadTable() {
        int monthNum = Integer.parseInt(month.getSelectedItem().toString());
        int yearNum = Integer.parseInt(year.getSelectedItem().toString());
        reportService.setUpTable1(table, monthNum, yearNum);
    }
    
    private void backBtnClick() {
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        ReportUI report = new ReportUI(mainPanel);
        mainPanel.add(report, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void searchTable() {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập id, tên nhân viên...")) {
            sorter.setRowFilter(null); // không lọc gì
        } else {
            RowFilter<DefaultTableModel, Object> idFilter = RowFilter.regexFilter("(?i)" + text, 0); // lọc cột id
            RowFilter<DefaultTableModel, Object> ownerFilter = RowFilter.regexFilter("(?i)" + text, 1); // lọc cột tên 

            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(java.util.Arrays.asList(idFilter, ownerFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }
    

    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id, tên nhân viên...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id, tên nhân viên...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id, tên nhân viên...");
                }
            }
        });
    }
    
    public void detailBtnClick() {
        if( !reportService.isSelectedRow(table) ) {
            return;
        }
        int selectedRow = table.getSelectedRow();
        int id = (Integer) table.getValueAt(selectedRow, 0);
        String name = (String) table.getValueAt(selectedRow, 1);
        String job = (String) table.getValueAt(selectedRow, 2);
        String shift = (String) table.getValueAt(selectedRow, 3);
        int numDate = (Integer) table.getValueAt(selectedRow, 4);
        
        EmployeeInfo er = new EmployeeInfo(id, name, job, shift, numDate);
        new employeeDetail(er, month, year).setVisible(true);
    }
    


    
}
