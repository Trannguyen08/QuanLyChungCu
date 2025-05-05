package main.java.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import main.java.utc2.apartmentManage.view.ManagerUI.addWindow.addNotification;
import main.java.utc2.apartmentManage.view.ManagerUI.searchWindow.searchNotification;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.java.utc2.apartmentManage.service.export.Excel;
import main.java.utc2.apartmentManage.service.managerService.notificationIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.editWindow.editNotification;


public class NotificationHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn, searchIcon, reloadBtn;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private deleteButtonHandler deleteHandler;
    private final notificationIMP notificationService = new notificationIMP();
    
    public NotificationHandler(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
                            JButton searchIcon, JButton reloadBtn, JTable table, JPanel panel){
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
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
        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtnClick();
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
                searchField.setText("Nhập id, tiêu đề thông báo...");
                notificationService.setUpTable(table);
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
        notificationService.setUpTable(table);

    }
    
    private void addBtnClick() {
        new addNotification(table).setVisible(true);
    }

    private void deleteBtnClick() {
        deleteHandler = new deleteButtonHandler(table, panel);
        if (!notificationService.isSelectedRow(table)) {
            return;
        }
        deleteHandler.deleteSelectedRow();
    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportTableToExcelWithDirectory(directoryPath, table, "notification");
        
    }
    
    private void editBtnClick() {
        if (!notificationService.isSelectedRow(table)) {
            return;
        }
        new editNotification(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchNotification(table).setVisible(true);
    }
    
    private void searchTable() {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập id, tiêu đề thông báo...")) {
            sorter.setRowFilter(null); 
        } else {
            RowFilter<DefaultTableModel, Object> idFilter = RowFilter.regexFilter("(?i)" + text, 0);
            RowFilter<DefaultTableModel, Object> ownerFilter = RowFilter.regexFilter("(?i)" + text, 2); 

            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(java.util.Arrays.asList(idFilter, ownerFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }
    

    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id, tiêu đề thông báo...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id, tiêu đề thông báo...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id, tiêu đề thông báo...");
                }
            }
        });
    }
}
