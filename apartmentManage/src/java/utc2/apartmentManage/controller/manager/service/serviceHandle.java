package java.utc2.apartmentManage.controller.manager.service;

import utc2.apartmentManage.service.export.excelExport;
import utc2.apartmentManage.service.implement.manager.serviceIMP;
import utc2.apartmentManage.view.manager.addWindow.addService;
import utc2.apartmentManage.view.manager.editWindow.editService;
import utc2.apartmentManage.view.manager.searchWindow.searchService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;

public class serviceHandle {
    private JButton addBtn, editBtn, excelBtn, searchIcon, reloadBtn;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private serviceIMP ss = new serviceIMP();
    
    public serviceHandle(JTextField searchField, JButton addBtn, JButton editBtn, JButton excelBtn,
                         JButton searchIcon, JButton reloadBtn, JTable table, JPanel panel){
        this.addBtn = addBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
        this.searchIcon = searchIcon;
        this.reloadBtn = reloadBtn;
        this.table = table;
        this.panel = panel;
        this.searchField = searchField;
        
        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBtnClick();
            }
        });

        
        this.excelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excelBtnClick();
            }
        });
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBtnClick();
            }
        });
        
        this.searchIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchInconClick();
            }
        });
        
        this.reloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setForeground(java.awt.Color.GRAY);
                searchField.setText("Nhập id, tên dịch vụ...");
                ss.setUpTable(table);
            }
        });
        
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
        

        placeHolder();
        ss.setUpTable(table);

    }
    
    private void addBtnClick() {
        new addService(table).setVisible(true);
    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        excelExport.exportTableToExcelWithDirectory(directoryPath, table, "service");
        
    }
    
    private void editBtnClick() {
        if( !ss.isSelectedRow(table) ) {
            return;
        }
        new editService(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchService(table).setVisible(true);
    }
    
    private void searchTable() {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập id, tên dịch vụ...")) {
            sorter.setRowFilter(null); // không lọc gì
        } else {
            RowFilter<DefaultTableModel, Object> idFilter = RowFilter.regexFilter("(?i)" + text, 0); // lọc cột id
            RowFilter<DefaultTableModel, Object> ownerFilter = RowFilter.regexFilter("(?i)" + text, 1); // lọc cột tên chủ

            // Kết hợp 2 bộ lọc bằng OR (chỉ cần khớp 1 trong 2 cột)
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(java.util.Arrays.asList(idFilter, ownerFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }
    
    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id, tên dịch vụ...");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (searchField.getText().equals("Nhập id, tên dịch vụ...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id, tên dịch vụ...");
                }
            }
        });
    }
}
