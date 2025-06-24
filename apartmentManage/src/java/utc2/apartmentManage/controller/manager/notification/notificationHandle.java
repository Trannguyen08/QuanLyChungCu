package java.utc2.apartmentManage.controller.manager.notification;

import utc2.apartmentManage.service.export.excelExport;
import utc2.apartmentManage.service.implement.manager.notificationIMP;
import utc2.apartmentManage.view.manager.addWindow.addNotification;
import utc2.apartmentManage.view.manager.editWindow.editNotification;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;


public class notificationHandle {
    private JButton addBtn, editBtn, deleteBtn, excelBtn, reloadBtn;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private deleteButtonHandle deleteHandler;
    private final notificationIMP notificationService = new notificationIMP();
    
    public notificationHandle(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
                              JButton reloadBtn, JTable table, JPanel panel){
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
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
        deleteHandler = new deleteButtonHandle(table, panel);
        if (!notificationService.isSelectedRow(table)) {
            return;
        }
        deleteHandler.deleteSelectedRow();
    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        excelExport.exportTableToExcelWithDirectory(directoryPath, table, "notification");
        
    }
    
    private void editBtnClick() {
        if (!notificationService.isSelectedRow(table)) {
            return;
        }
        new editNotification(table).setVisible(true);
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
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (searchField.getText().equals("Nhập id, tiêu đề thông báo...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id, tiêu đề thông báo...");
                }
            }
        });
    }
}
