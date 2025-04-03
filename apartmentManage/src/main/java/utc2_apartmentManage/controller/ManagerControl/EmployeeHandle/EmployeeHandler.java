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
    private deleteButtonHandler deleteHandler;
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

        searchButtonHandler searchButtonHandler = new searchButtonHandler(searchField, searchButton, table);

        es.setupEmployeeTable(table);
        
    }


    private void addBtnClick() {
        new addEmployee(table).setVisible(true);
    }

    private void deleteBtnClick() {
        if (deleteHandler == null) {
            deleteHandler = new deleteButtonHandler(deleteBtn, table, panel);
        }
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

}
