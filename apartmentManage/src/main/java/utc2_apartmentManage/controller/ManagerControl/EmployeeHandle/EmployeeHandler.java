package main.java.utc2_apartmentManage.controller.ManagerControl.EmployeeHandle;

import main.java.utc2_apartmentManage.controller.ManagerControl.ResidentHandle.ResidentHandler;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.utc2_apartmentManage.service.export.Excel;
import main.java.utc2_apartmentManage.service.managerService.employeeService;
import main.java.utc2_apartmentManage.view.ManagerUI.addWindow.addEmployee;
import main.java.utc2_apartmentManage.view.ManagerUI.editWindow.editEmployee;
import main.java.utc2_apartmentManage.view.ManagerUI.searchWindow.searchEmployee;


public class EmployeeHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn, searchIcon, searchButton;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private employeeService es = new employeeService();

    public EmployeeHandler(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
                            JButton searchIcon, JButton searchButton, JTable table, JPanel panel) {
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
        this.searchIcon = searchIcon;
        this.searchButton = searchButton;
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

        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClick();
            }
        });
        
        placeHolder();

        es.setupEmployeeTable(table);
        
    }


    private void addBtnClick() {
        new addEmployee(table).setVisible(true);
    }

    private void deleteBtnClick() {
        deleteButtonHandler deleteHandler = new deleteButtonHandler(deleteBtn, table, panel);
        deleteHandler.deleteSelectedRow();

    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportEmployees(directoryPath);
        
    }
    
    private void editBtnClick() throws ParseException {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trước khi chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new editEmployee(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchEmployee(table).setVisible(true);
    }
    
    private void searchButtonClick() {
        searchButtonHandler sbh = new searchButtonHandler(searchField, searchButton, table);
        sbh.searchBtnClick();
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
