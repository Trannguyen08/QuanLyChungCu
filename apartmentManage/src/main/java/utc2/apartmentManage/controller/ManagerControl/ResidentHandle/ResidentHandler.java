package main.java.utc2.apartmentManage.controller.ManagerControl.ResidentHandle;


import main.java.utc2.apartmentManage.repository.ManagerRepository.residentRepository;
import main.java.utc2.apartmentManage.service.export.Excel;
import main.java.utc2.apartmentManage.view.ManagerUI.searchWindow.searchResident;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.java.utc2.apartmentManage.service.managerService.residentIMP;
import main.java.utc2.apartmentManage.view.ManagerUI.addWindow.addResident;



public class ResidentHandler {
    
     private JButton addBtn, deleteBtn, excelBtn, searchIcon, reloadBtn;
    private JTable table;
    private JPanel panel;
    private JTextField searchField;
    private deleteButtonHandler deleteHandler;
    private residentIMP rs = new residentIMP();
    private residentRepository rdao = new residentRepository();

    public ResidentHandler(JTextField searchField, JButton addBtn, JButton deleteBtn, JButton excelBtn,
                            JButton searchIcon, JButton reloadBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.excelBtn = excelBtn;
        this.searchIcon = searchIcon;
        this.table = table;
        this.panel = panel;
        this.searchField = searchField;
        this.reloadBtn = reloadBtn;
        this.addBtn = addBtn;


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
        this.searchIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchInconClick();
            }
        });
        this.reloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setForeground(java.awt.Color.GRAY);
                searchField.setText("Nhập id, tên cư dân...");
                rs.setUpTable(table);
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
        rs.setUpTable(table);
    }
    
    private void addBtnClick() {
        new addResident(table).setVisible(true);
    }
    
    private void searchTable() {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập id, tên cư dân...")) {
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

    private void deleteBtnClick() {
        deleteButtonHandler handler = new deleteButtonHandler(deleteBtn, table, panel);
        handler.deleteSelectedRow();
    }

    private void excelBtnClick() {
        String directoryPath = System.getProperty("user.dir") + File.separator + "data";
        Excel.exportTableToExcelWithDirectory(directoryPath, table, "resident");
        
    }

    private void searchInconClick() {
        new searchResident(table).setVisible(true);
    }

}