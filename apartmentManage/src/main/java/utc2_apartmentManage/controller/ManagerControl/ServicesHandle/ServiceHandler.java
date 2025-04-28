package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;


import main.java.utc2_apartmentManage.service.export.Excel;
import main.java.utc2_apartmentManage.view.ManagerUI.editWindow.editService;
import main.java.utc2_apartmentManage.view.ManagerUI.searchWindow.searchService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import main.java.utc2_apartmentManage.service.managerService.serviceService;
import main.java.utc2_apartmentManage.view.ManagerUI.addWindow.addService;

public class ServiceHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn, searchIcon, searchButton;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private deleteButtonHandler deleteHandler;
    
    public ServiceHandler(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
                            JButton searchIcon, JButton searchButton, JTable table, JPanel panel){
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
                editBtnClick();
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

        new serviceService().setupServiceTable(table);

    }
    
    private void addBtnClick() {
        new addService(table).setVisible(true);
    }

    private void deleteBtnClick() {
        deleteHandler = new deleteButtonHandler(deleteBtn, table, panel);
        deleteHandler.deleteSelectedRow();
    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportServices(directoryPath);
        
    }
    
    private void editBtnClick() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trước khi chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new editService(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchService(table).setVisible(true);
    }
    
    private void searchButtonClick() {
        searchButtonHandler sbh = new searchButtonHandler(searchField, searchButton, table);
        sbh.filterWithSearchField();
    }
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id, tên dịch vụ...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id, tên dịch vụ...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id, tên dịch vụ...");
                }
            }
        });
    }
}
