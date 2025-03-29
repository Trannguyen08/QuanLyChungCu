package controller.ManagerControl.ContractControl;

import service.managerService.contractService;
import view.ManagerUI.searchContract;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import service.export.Excel;

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

        contract_ervice.setupApartmentTable(table);

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
        
    }
    
    private void searchButtonClick() {
        searchButtonHandler handler = new searchButtonHandler(searchField, searchButton, table);
        handler.searchBtnClick();
    }
    private void searchIconClick() {
        new searchContract(table).setVisible(true);
    }
     
    
    
}
