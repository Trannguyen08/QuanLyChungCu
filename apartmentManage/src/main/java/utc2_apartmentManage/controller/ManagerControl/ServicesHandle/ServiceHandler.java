package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;


import main.java.utc2_apartmentManage.service.export.Excel;
import main.java.utc2_apartmentManage.view.ManagerUI.editWindow.editService;
import main.java.utc2_apartmentManage.view.ManagerUI.searchWindow.searchService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;

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
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }
    
    private void addBtnClick() {
        //new addService(table).setVisible(true);
    }

    private void deleteBtnClick() {
        if (deleteHandler == null) {
            deleteHandler = new deleteButtonHandler(deleteBtn, table, panel);
        }
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
        new editService(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchService(table).setVisible(true);
    }
}
