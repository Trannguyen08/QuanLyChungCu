
package main.java.utc2_apartmentManage.controller.UserControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import main.java.utc2_apartmentManage.service.userService.utilitiesService;

public class UtilitiesHandler {
    private JButton searchButton, registerButton;
    private JTextField searchField;
    private JTable table;
    private JPanel panel;
    private final utilitiesService utilitiesService = new utilitiesService();

    public UtilitiesHandler ( JButton searchButton, JButton register, 
                      JTextField searchField, JTable table, JPanel panel) {
      
        this.searchButton = searchButton;
        this.registerButton = registerButton;
        this.searchField = searchField;
        this.table = table;
        this.panel = panel;

        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchButtonClick();
            }
        });
        this.registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerButtonClick();
            }

        });
        placeHolder();
        utilitiesService.setupUtilitiestTable(table);
    }
    
    private void searchButtonClick() {
        String name = searchField.getText().trim();
        if( name.equals("Nhập tên dịch vụ...") || name.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị cho ô tìm kiếm",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if( !ScannerUtil.isValidServiceName(name) ) {
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + name, 0));
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void registerButtonClick() {
        
    }
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập tên dịch vụ...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập tên dịch vụ...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập tên dịch vụ...");
                }
            }
        });
    }
    

} 
