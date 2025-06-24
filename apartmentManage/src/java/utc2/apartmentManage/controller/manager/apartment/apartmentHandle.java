package java.utc2.apartmentManage.controller.manager.apartment;

import utc2.apartmentManage.service.export.excelExport;
import utc2.apartmentManage.service.implement.manager.apartmentIMP;
import utc2.apartmentManage.view.manager.detailWindow.apartmentDetail;
import utc2.apartmentManage.view.manager.editWindow.editApartment;
import utc2.apartmentManage.view.manager.searchWindow.searchApartment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;


public class apartmentHandle {
    private JButton editBtn, reloadBtn, excelBtn, searchIcon, detailBtn;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private final apartmentIMP apartmentService = new apartmentIMP();
    
    public apartmentHandle(JTextField searchField, JButton editBtn, JButton reloadBtn, JButton excelBtn,
                            JButton searchIcon, JButton detailBtn, JTable table, JPanel panel) {
        this.reloadBtn = reloadBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
        this.searchIcon = searchIcon;
        this.detailBtn = detailBtn;
        this.table = table;
        this.panel = panel;
        this.searchField = searchField;
        

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
                searchField.setText("Nhập id căn hộ...");
                apartmentService.setUpTable(table);
            }
        });
        
        this.detailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailBtnClick();
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
        apartmentService.setUpTable(table);
        
    }
    
    private void detailBtnClick() {
        new apartmentDetail(table).setVisible(true);
    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        excelExport.exportTableToExcelWithDirectory(directoryPath, table, "apartment");
        
    }
    
    private void editBtnClick() {
        if( !apartmentService.isSelectedRow(table) ) {
            return;
        }
        if (!apartmentService.isStillContract(table)) {
            return;
        }
        new editApartment(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchApartment(table).setVisible(true);
    }
    
    private void searchTable() {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập id căn hộ...")) {
            sorter.setRowFilter(null); // không lọc gì
        } else {
            RowFilter<DefaultTableModel, Object> idFilter = RowFilter.regexFilter("(?i)" + text, 0); 
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(java.util.Arrays.asList(idFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }
    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id căn hộ...");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (searchField.getText().equals("Nhập id căn hộ...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id căn hộ...");
                }
            }
        });
    }

}