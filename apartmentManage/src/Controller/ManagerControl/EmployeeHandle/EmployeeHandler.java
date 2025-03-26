package Controller.ManagerControl.EmployeeHandle;

import Model.ManagerDAO.Excel;
import View.ManagerUI.addEmployee;
import View.ManagerUI.editEmployee;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EmployeeHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn;
    private JTable table;
    private JPanel panel;
    private deleteButtonHandler deleteHandler;

    public EmployeeHandler(JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
                               JTable table, JPanel panel) {
        this.addBtn = addBtn;
        this.deleteBtn = deleteBtn;
        this.editBtn = editBtn;
        this.excelBtn = excelBtn;
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
                editBtnClick();
            }
        });
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
    
    private void editBtnClick() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trước khi chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new editEmployee(table).setVisible(true);
    }
}
