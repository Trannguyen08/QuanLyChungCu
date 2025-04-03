package main.java.utc2_apartmentManage.controller.ManagerControl.ContractControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import main.java.utc2_apartmentManage.service.export.Excel;
import main.java.utc2_apartmentManage.service.export.PDF;
import main.java.utc2_apartmentManage.service.managerService.contractService;
import main.java.utc2_apartmentManage.view.ManagerUI.searchWindow.searchContract;


public class mainHandler {
    private JButton deleteBtn, excelBtn, pdfBtn, searchButton, searchIcon;
    private JTextField searchField;
    private JTable table;
    private JPanel panel;
    private final contractService contract_ervice = new contractService();

    public mainHandler(JButton deleteBtn, JButton excelBtn, JButton pdfBtn, 
                        JButton searchButton, JButton searchIcon, JTextField searchField, JTable table, JPanel panel) {
      
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

        placeHolder();
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

        String filePath = System.getProperty("user.dir") + File.separator + "Data";

        // Kiểm tra và tạo thư mục nếu chưa tồn tại
        File directory = new File(filePath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Kiểm tra và in ra đường dẫn đầy đủ
        String fullFilePath = filePath + File.separator + "contract_" + id + ".pdf";
        File file = new File(fullFilePath);
        try {
            if (file.createNewFile()) {
                System.out.println("File đã được tạo: " + fullFilePath);
            } else {
                System.out.println("File đã tồn tại hoặc không thể tạo file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Xuất hợp đồng
        PDF.exportContractToPDF(fullFilePath, id);

        // Kiểm tra xem file có tồn tại sau khi xuất
        File generatedFile = new File(fullFilePath);
        if (generatedFile.exists()) {
            PDF.openPDF(fullFilePath);
        } else {
            JOptionPane.showMessageDialog(null, "Không thể tạo hoặc tìm thấy file: " + fullFilePath);
        }
    }



    private void searchButtonClick() {
        searchButtonHandler handler = new searchButtonHandler(searchField, searchButton, table);
        handler.searchBtnClick();
    }
    private void searchIconClick() {
        new searchContract(table).setVisible(true);
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
     
    
    
}
