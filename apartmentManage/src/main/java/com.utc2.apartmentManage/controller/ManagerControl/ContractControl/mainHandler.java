package main.java.com.utc2.apartmentManage.controller.ManagerControl.ContractControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import main.java.com.utc2.apartmentManage.service.export.Excel;
import main.java.com.utc2.apartmentManage.service.export.PDF;
import main.java.com.utc2.apartmentManage.service.managerService.contractService;
import main.java.com.utc2.apartmentManage.view.ManagerUI.searchWindow.searchContract;


public class mainHandler {
    private JComboBox<String> contractType;
    private JButton deleteBtn, excelBtn, pdfBtn, searchButton, searchIcon;
    private JTextField searchField;
    private JTable table;
    private JPanel panel;
    private final contractService contract_ervice = new contractService();

    public mainHandler(JComboBox<String> contractType, JButton deleteBtn, JButton excelBtn, JButton pdfBtn, 
                        JButton searchButton, JButton searchIcon, JTextField searchField, JTable table, JPanel panel) {
        
        this.contractType = contractType;
        this.deleteBtn = deleteBtn;
        this.excelBtn = excelBtn;
        this.pdfBtn = pdfBtn;
        this.searchButton = searchButton;
        this.searchIcon = searchIcon;
        this.searchField = searchField;
        this.table = table;
        this.panel = panel;

        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtnClick();
            }
        });
        
        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClick();
            }
        });

        this.searchIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchIconClick();
            }
        });
        
        this.excelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excelBtnClick();
            }
        });
        
        this.pdfBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pdfBtnClick();
            }
        });

        contract_ervice.setupContractTable(table);

    }
    
    private void deleteBtnClick() {
        deleteButtonHandler handler = new deleteButtonHandler(table, panel);
        handler.dltBtnClick();
    }
    
    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportContracts(directoryPath);
    }
    
    private void pdfBtnClick() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hợp đồng!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        String filePath = System.getProperty("user.dir") + "/Data/contract.pdf";
        PDF.exportContractToPDF(filePath, id);
        PDF.openPDF(filePath);
    }
    
    private void searchButtonClick() {
        searchButtonHandler handler = new searchButtonHandler(searchField, searchButton, table);
        handler.searchBtnClick();
    }
    private void searchIconClick() {
        new searchContract(table).setVisible(true);
    }
     
    
    
}
