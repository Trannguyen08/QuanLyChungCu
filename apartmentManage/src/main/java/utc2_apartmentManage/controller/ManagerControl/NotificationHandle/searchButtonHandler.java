
package main.java.utc2_apartmentManage.controller.ManagerControl.NotificationHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + id, 0));
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
