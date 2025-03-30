package controller.ManagerControl.NotificationHandle;

import dao.managerDAO.NotificationDAO;
import service.export.Excel;
import view.ManagerUI.addNotification;
import view.ManagerUI.editNotification;
import view.ManagerUI.searchNotification;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NotificationHandler {
    private JButton addBtn, editBtn, deleteBtn, excelBtn, searchIcon, searchButton;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private deleteButtonHandler deleteHandler;
    
    public NotificationHandler(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton editBtn, JButton excelBtn,
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
        
        new NotificationDAO().addDataToTable(table);

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
        new addNotification(table).setVisible(true);
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
        new editNotification(table).setVisible(true);
    }

    private void searchInconClick() {
        new searchNotification(table).setVisible(true);
    }
}
