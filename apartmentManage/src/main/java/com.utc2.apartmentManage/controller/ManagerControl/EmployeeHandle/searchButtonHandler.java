
package main.java.com.utc2.apartmentManage.controller.ManagerControl.EmployeeHandle;

import main.java.com.utc2.apartmentManage.util.ScannerUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nghia
 */
public class searchButtonHandler {
    private JTable table;
    private JTextField searchField;
    private JButton searchButton;

    public searchButtonHandler(JTextField searchField, JButton searchButton, JTable table) {
        this.searchField = searchField;
        this.searchButton = searchButton;
        this.table = table;

        this.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findIDInTable();
            }
        });
    }

    private void findIDInTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        String id = searchField.getText().trim();
        if(ScannerUtil.validateInteger(id, "Ô tìm kiếm")) {
            return;
        }
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + id, 0));
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập id nhân viên...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập id nhân viên...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập id nhân viên...");
                }
            }
        });
    }
}
