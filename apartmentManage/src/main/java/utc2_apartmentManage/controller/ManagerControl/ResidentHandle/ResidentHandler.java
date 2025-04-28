package main.java.utc2_apartmentManage.controller.ManagerControl.ResidentHandle;

import main.java.utc2_apartmentManage.repository.managerRepository.residentRepository;
import main.java.utc2_apartmentManage.service.export.Excel;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import main.java.utc2_apartmentManage.model.Resident;
import main.java.utc2_apartmentManage.service.managerService.residentService;
import main.java.utc2_apartmentManage.view.ManagerUI.editWindow.editResident;
import main.java.utc2_apartmentManage.view.ManagerUI.searchWindow.searchResident;


public class ResidentHandler {
    
     private JButton editBtn, deleteBtn, excelBtn, searchIcon, searchButton;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private deleteButtonHandler deleteHandler;
    private residentService rs = new residentService();
    private residentRepository rdao = new residentRepository();

    public ResidentHandler(JTextField searchField, JButton deleteBtn, JButton editBtn, JButton excelBtn,
                            JButton searchIcon, JButton searchButton, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
        this.searchIcon = searchIcon;
        this.searchButton = searchButton;
        this.table = table;
        this.panel = panel;
        this.searchField = searchField;


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

        rs.setupResidentTable(table);
    }
    
    private void searchButtonClick() {
        String content = searchField.getText().trim();
        if( content.equals("Nhập id, tên cư dân...") || searchField.getText() == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị cho ô tìm kiếm",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if( content.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng không nhập chuỗi chỉ chứa khoảng trắng",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        List<Resident> residentList = rdao.getFilterResidentWithKeyword(content);
        rs.addDataToTable(residentList, table);
        
    }
    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id, tên cư dân...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id, tên cư dân...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id, tên cư dân...");
                }
            }
        });
    }

    private void deleteBtnClick() {
        deleteButtonHandler handler = new deleteButtonHandler(deleteBtn, table, panel);
        handler.deleteSelectedRow();
    }

    private void excelBtnClick() {
        String filePath = System.getProperty("user.dir") + "/Data/";
        Excel.exportResidents(filePath);
        
    }
    
    private void editBtnClick() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trước khi chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        editResident er = new editResident(table);
        er.managerEditResident();
        er.setVisible(true);
    }

    private void searchInconClick() {
        new searchResident(table).setVisible(true);
    }

}