package main.java.utc2.apartmentManage.controller.ManagerControl.EmployeeHandle;


import main.java.utc2.apartmentManage.controller.ManagerControl.ResidentHandle.ResidentHandler;
import main.java.utc2.apartmentManage.service.export.Excel;
import main.java.utc2.apartmentManage.service.managerService.employeeIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.addWindow.addEmployee;
import main.java.utc2.apartmentManage.view.ManagerUI.editWindow.editEmployee;
import main.java.utc2.apartmentManage.view.ManagerUI.searchWindow.searchEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;



public class EmployeeHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn, searchIcon, reloadBtn;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private employeeIMP es = new employeeIMP();

    public EmployeeHandler(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
                            JButton searchIcon, JButton reloadBtn, JTable table, JPanel panel) {
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
        this.searchIcon = searchIcon;
        this.table = table;
        this.panel = panel;
        this.searchField = searchField;
        this.reloadBtn = reloadBtn;

        
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
                try {
                    editBtnClick();
                } catch (ParseException ex) {
                    Logger.getLogger(ResidentHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                searchField.setText("Nhập id, tên nhân viên...");
                es.setUpTable(table);
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
        es.setUpTable(table);
        
    }


    private void addBtnClick() {
        new addEmployee(table).setVisible(true);
    }

    private void deleteBtnClick() {
        deleteButtonHandler deleteHandler = new deleteButtonHandler(table, panel);
        deleteHandler.deleteSelectedRow();

    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportTableToExcelWithDirectory(directoryPath, table, "employee");
        
    }
    
    private void editBtnClick() throws ParseException {
        if( !es.isSelectedRow(table) ) {
            return;
        }
        new editEmployee(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchEmployee(table).setVisible(true);
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

}
