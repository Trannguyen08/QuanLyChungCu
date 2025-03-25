package Controller.ManagerControl.ServicesHandle;

import Util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class searchIconHandler {
    private javax.swing.JTextField ServiceID;
    private javax.swing.JTextField ServiceName;
    private javax.swing.JComboBox<String> ServiceType;
    private javax.swing.JTextField ServicePrice;
    private javax.swing.JTextField ServiceUnit;
    private JTable table;
    private JFrame frame;
    private javax.swing.JButton searchBtn;
    
    public searchIconHandler(JTextField ServiceID, JTextField ServiceName,JTextField ServicePrice, JTextField  ServiceUnit, JComboBox<String> ServiceType, JTable table, JFrame frame, JButton searchBtn){
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.ServiceType = ServiceType;
        this.ServicePrice = ServicePrice;
        this.ServiceUnit = ServiceUnit;
        this.table = table;
        this.frame = frame;
        this.searchBtn = searchBtn;
        
        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }
    public void filterTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

        // validate dữ liệu nhập đúng kiểu
        if ((ServiceID.getText() != null && !ServiceID.getText().trim().isEmpty() &&
                !ScannerUtil.validateInteger(ServiceID.getText().trim(), "ID Dịch vụ")) ||
                (ServiceName.getText() != null && !ServiceName.getText().trim().isEmpty() &&
                        !ScannerUtil.validateInteger(ServiceName.getText().trim(), "Tên dịch vụ")) ||
                (ServicePrice.getText() != null && !ServicePrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(ServicePrice.getText().trim(), "Giá dịch vụ")) ||
                (ServiceUnit.getText() != null && !ServiceUnit.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(ServiceUnit.getText().trim(), "Đề tài"))
        ) {
            return;
        }

        if (ServiceType.getSelectedItem().toString().trim().isEmpty() ||
                ServiceType.getSelectedItem().toString().trim().equals("Chọn kiểu dịch vụ")) {
            return;
        }

        // nếu không null thì xét với table
        if (!ServiceID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + ServiceID.getText().trim(), 0));
        }
        if (!ServiceName.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + ServiceName.getText().trim(), 1));
        }
        if (ServiceType.getSelectedItem() != null && !ServiceType.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + ServiceType.getSelectedItem().toString().trim(), 2));
        }
        if (!ServicePrice.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + ServicePrice.getText().trim(), 3));
        }
        if (!ServiceUnit.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + ServiceUnit.getText().trim(), 4));
        }
        
        sorter.setRowFilter(RowFilter.andFilter(filters));
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
