package main.java.com.utc2.apartmentManage.controller.ManagerControl.NotificationHandle;

import main.java.com.utc2.apartmentManage.util.ScannerUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class searchIconHandler {
    private javax.swing.JTextField notiID;
    private javax.swing.JTextField ownerID;
    private javax.swing.JTextField notiTitle;
    private javax.swing.JTextField notification;
    private javax.swing.JComboBox<String> notiType;
    private JTable table;
    private JFrame frame;
    private javax.swing.JButton searchBtn;

    public searchIconHandler(JTextField notiID, JTextField ownerID, JTextField notiTitle, JTextField notification, JComboBox<String> notiType, JTable table, JFrame frame, JButton searchBtn) {
        this.notiID = notiID;
        this.ownerID = ownerID;
        this.notiTitle = notiTitle;
        this.notification = notification;
        this.notiType = notiType;
        this.frame = frame;
        this.table = table;
        this.searchBtn = searchBtn;

        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    } // Kết thúc constructor - đây là lỗi trước đó, thiếu dấu đóng.

    // ✅ Di chuyển phương thức filterTableData() ra khỏi constructor
    public void filterTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

        // validate dữ liệu nhập đúng kiểu
        if ((notiID.getText() != null && !notiID.getText().trim().isEmpty() &&
                ScannerUtil.validateInteger(notiID.getText().trim(), "ID Thông báo")) ||
                (ownerID.getText() != null && !ownerID.getText().trim().isEmpty() &&
                        ScannerUtil.validateInteger(ownerID.getText().trim(), "ID Cư dân")) ||
                (notiTitle.getText() != null && !notiTitle.getText().trim().isEmpty() &&
                        ScannerUtil.validateDouble(notiTitle.getText().trim(), "Tiêu đề")) ||
                (notification.getText() != null && !notification.getText().trim().isEmpty() &&
                        ScannerUtil.validateDouble(notification.getText().trim(), "Tin nhắn"))
        ) {
            return;
        }

        if (notiType.getSelectedItem().toString().trim().isEmpty() ||
                notiType.getSelectedItem().toString().trim().equals("Chọn loại thông báo")) {
            return;
        }

        // nếu không null thì xét với table
        if (!notiID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + notiID.getText().trim(), 0));
        }
        if (!ownerID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + ownerID.getText().trim(), 1));
        }
        if (!notiTitle.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + notiTitle.getText().trim(), 2));
        }
        if (!notification.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + notification.getText().trim(), 3));
        }
        if (notiType.getSelectedItem() != null && !notiType.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + notiType.getSelectedItem().toString().trim(), 4));
        }
        sorter.setRowFilter(RowFilter.andFilter(filters));
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
