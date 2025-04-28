package main.java.utc2.apartmentManage.controller.ManagerControl.ContractControl;

import main.java.utc2.apartmentManage.service.export.Excel;
import main.java.utc2.apartmentManage.service.export.PDF;
import main.java.utc2.apartmentManage.service.managerService.contractIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.searchWindow.searchContract;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class mainHandler {
    private JButton deleteBtn, excelBtn, reloadBtn, searchIcon, detailBtn;
    private JTextField searchField;
    private JTable table;
    private JPanel panel;
    private final contractIMP contract_ervice = new contractIMP();

    public mainHandler(JButton deleteBtn, JButton excelBtn, JButton reloadBtn, JButton detailBtn,
                       JButton searchIcon, JTextField searchField, JTable table, JPanel panel) {
      
        this.deleteBtn = deleteBtn;
        this.excelBtn = excelBtn;
        this.reloadBtn = reloadBtn;
        this.searchIcon = searchIcon;
        this.searchField = searchField;
        this.table = table;
        this.panel = panel;
        this.detailBtn = detailBtn;

        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtnClick();
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
        
        this.reloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setForeground(java.awt.Color.GRAY);
                searchField.setText("Nhập id hợp đồng, tên chủ sở hữu...");
                contract_ervice.setUpTable(table);
            }
        });
        
        this.detailBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detailBtnClick();
            }
        });
        
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }
        });

        placeHolder();
        contract_ervice.setUpTable(table);

    }
    
    private void deleteBtnClick() {
        deleteButtonHandler handler = new deleteButtonHandler(table, panel);
        handler.dltBtnClick();
    }
    
    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportContracts(directoryPath);
    }

    private void detailBtnClick() {
        if( !contract_ervice.isSelectedRow(table) ) {
            return;
        } 
        int selectedRow = table.getSelectedRow();
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


    private void searchIconClick() {
        new searchContract(table).setVisible(true);
    }
    
    private void searchTable() {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập id hợp đồng, tên chủ sở hữu...")) {
            sorter.setRowFilter(null); // không lọc gì
        } else {
            RowFilter<DefaultTableModel, Object> idFilter = RowFilter.regexFilter("(?i)" + text, 0); // lọc cột id
            RowFilter<DefaultTableModel, Object> ownerFilter = RowFilter.regexFilter("(?i)" + text, 1); // lọc cột tên chủ

            // Kết hợp 2 bộ lọc bằng OR (chỉ cần khớp 1 trong 2 cột)
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(java.util.Arrays.asList(idFilter, ownerFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }


    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id hợp đồng, tên chủ sở hữu...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id hợp đồng, tên chủ sở hữu...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id hợp đồng, tên chủ sở hữu ...");
                }
            }
        });
        
    }
 
}
