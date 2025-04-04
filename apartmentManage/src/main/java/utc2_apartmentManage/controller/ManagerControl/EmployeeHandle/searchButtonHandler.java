package main.java.utc2_apartmentManage.controller.ManagerControl.EmployeeHandle;

import javax.swing.*;
import main.java.utc2_apartmentManage.service.managerService.employeeService;

public class searchButtonHandler {
    private JTable table;
    private JTextField searchField;
    private JButton searchButton;
    private employeeService es = new employeeService();

    public searchButtonHandler(JTextField searchField, JButton searchButton, JTable table) {
        this.searchField = searchField;
        this.searchButton = searchButton;
        this.table = table;

    }

    public void searchBtnClick() {
        String content = searchField.getText().trim();
        if( content.equals("Nhập id, tên nhân viên...") || searchField.getText() == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị cho ô tìm kiếm",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if( content.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng không nhập chuỗi chỉ chứa khoảng trắng",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        es.updateTable(content, table);
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
