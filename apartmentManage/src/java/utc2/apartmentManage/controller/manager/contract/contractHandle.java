package java.utc2.apartmentManage.controller.manager.contract;

import utc2.apartmentManage.service.export.contractsExport;
import utc2.apartmentManage.service.export.excelExport;
import utc2.apartmentManage.service.implement.manager.contractIMP;
import utc2.apartmentManage.view.manager.searchWindow.searchContract;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;


public class contractHandle {
    private JButton deleteBtn, excelBtn, reloadBtn, searchIcon, detailBtn;
    private JTextField searchField;
    private JTable table;
    private JPanel panel;
    private final contractIMP contract_ervice = new contractIMP();

    public contractHandle(JButton deleteBtn, JButton excelBtn, JButton reloadBtn, JButton detailBtn,
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
        if (!contract_ervice.isSelectedRow(table)) {
            return;
        }
        deleteButtonHandle handler = new deleteButtonHandle(table, panel);
        handler.dltBtnClick();
    }
    
    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        excelExport.exportTableToExcelWithDirectory(directoryPath, table, "contract");
    }

    private void detailBtnClick() {
        if (!contract_ervice.isSelectedRow(table)) {
            return;
        }

        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int contractId = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

        String dirPath = System.getProperty("user.dir") + File.separator + "Data";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fullFilePath = dirPath + File.separator + "contract_" + contractId + ".pdf";
        contractsExport.exportContractToPDF(fullFilePath, contractId);

        // Mở file nếu đã tạo thành công
        File pdfFile = new File(fullFilePath);
        if (pdfFile.exists()) {
            contractsExport.openPDF(fullFilePath);
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
            RowFilter<DefaultTableModel, Object> idFilter = RowFilter.regexFilter("(?i)" + text, 0); 
            RowFilter<DefaultTableModel, Object> ownerFilter = RowFilter.regexFilter("(?i)" + text, 1);

            // Kết hợp 2 bộ lọc bằng OR (chỉ cần khớp 1 trong 2 cột)
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(java.util.Arrays.asList(idFilter, ownerFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }


    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id hợp đồng, tên chủ sở hữu...");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (searchField.getText().equals("Nhập id hợp đồng, tên chủ sở hữu...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id hợp đồng, tên chủ sở hữu...");
                }
            }
        });
        
    }
 
}
