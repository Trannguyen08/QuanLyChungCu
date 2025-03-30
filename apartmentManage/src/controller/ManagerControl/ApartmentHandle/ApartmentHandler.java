package controller.ManagerControl.ApartmentHandle;

import dao.managerDAO.ApartmentDAO;
import service.export.Excel;
import view.ManagerUI.addApartment;
import view.ManagerUI.editApartment;
import view.ManagerUI.searchApartment;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import service.managerService.apartmentService;

public class ApartmentHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn, searchIcon, searchButton;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private deleteButtonHandler deleteHandler;
    private final apartmentService apartmentService = new apartmentService();
    
    public ApartmentHandler(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
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
                editBtnClick();
            }
        });
        this.searchIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchInconClick();
            }
        });

        searchButtonHandler searchButtonHandler = new searchButtonHandler(searchField, searchButton, table);

        apartmentService.setupApartmentTable(table);
        
    }

    private void addBtnClick() {
        new addApartment(table).setVisible(true);
    }

    private void deleteBtnClick() {
        deleteHandler = new deleteButtonHandler(deleteBtn, table, panel);
        deleteHandler.deleteSelectedRow();
    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportApartments(directoryPath);
        
    }
    
    private void editBtnClick() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng trước khi chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        new editApartment(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchApartment(table).setVisible(true);
    }

}