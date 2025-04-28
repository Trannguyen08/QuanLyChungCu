
package main.java.utc2.apartmentManage.controller.UserControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import main.java.utc2.apartmentManage.service.userService.billService;
import main.java.utc2.apartmentManage.view.ManagerUI.searchWindow.searchBill;

public class BillHandler {
    private JButton pdfBtn, searchButton, searchIcon;
    private JTextField searchField;
    private JTable table;
    private JPanel panel;
    private final billService billService = new billService();

    public BillHandler( JButton pdfBtn, JButton searchButton, JButton searchIcon, 
                      JTextField searchField, JTable table, JPanel panel) {
      
        this.pdfBtn = pdfBtn;
        this.searchButton = searchButton;
        this.searchIcon = searchIcon;
        this.searchField = searchField;
        this.table = table;
        this.panel = panel;

        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClick();
            }
        });

        this.searchIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchInconClick();
            }
        });

        
        this.pdfBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pdfBtnClick();
            }
        });
        placeHolder();
        billService.setupBilltTable(table);
    }

    private void pdfBtnClick() {
        
    }

    private void searchInconClick() {
        new searchBill(table).setVisible(true);
    }
    
    private void searchButtonClick() {
        String id = searchField.getText().trim();
        if( id.equals("Nhập id hóa đơn...") || id.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị cho ô tìm kiếm",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if( !ScannerUtil.validateInteger(id, "Ô tìm kiếm") ) {
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + id, 0));
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id hóa đơn...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id hóa đơn...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id hóa đơn...");
                }
            }
        });
    }

}