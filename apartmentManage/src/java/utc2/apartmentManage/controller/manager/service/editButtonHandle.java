package java.utc2.apartmentManage.controller.manager.service;

import utc2.apartmentManage.model.Service;
import utc2.apartmentManage.service.implement.manager.serviceIMP;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;


public class editButtonHandle {
    private JButton editBtn;
    private JTextField ServiceName, ServicePrice, ServiceUnit;
    private JTextArea note;
    private JComboBox<String> ServiceType, relevant;
    private JTable table;
    private JFrame edit;
    private serviceIMP ss = new serviceIMP();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public editButtonHandle(JButton editBtn, JComboBox<String> ServiceType, JTextField ServiceName, JComboBox<String> relevant,
                            JTextField ServicePrice, JTextField ServiceUnit, JTextArea note, JTable table, JFrame edit){
        this.editBtn = editBtn;
        this.ServiceName = ServiceName;
        this.ServicePrice = ServicePrice;
        this.ServiceUnit = ServiceUnit;
        this.relevant = relevant;
        this.ServiceType = ServiceType;
        this.note = note;
        this.table = table;
        this.edit = edit;
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
    }
    
    public void loadSelectedRowData() {
        ss.loadSelectedRowData(table, ServiceName, ServiceType, relevant, ServicePrice, ServiceUnit, note);
        if( relevant.getSelectedItem().toString().trim().equals("Căn hộ") ) {
            ServiceName.setEditable(false);
            ServiceType.setEnabled(false);
            relevant.setEnabled(false);
            ServiceUnit.setEditable(false);
        }
    }
    
    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();

        if( !ss.addValidate(ServiceName, ServicePrice, ServiceUnit) ) {
            return;
        }

        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        Service service = new Service(id, ServiceName.getText().trim(),
                                       ServiceType.getSelectedItem().toString().trim(),
                                       relevant.getSelectedItem().toString().trim(),
                                       ScannerUtil.replaceDouble(ServicePrice),
                                       ServiceUnit.getText().trim(),
                                       note.getText().trim());

        if( !ss.update(service) ) {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        updateTableRow(selectedRow, service);
        edit.setVisible(false);
        JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateTableRow(int rowIndex, Service service) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setValueAt(service.getServiceName(), rowIndex, 1);
        model.setValueAt(service.getServiceType(), rowIndex, 2);
        model.setValueAt(service.getRelevant(), rowIndex, 3);
        model.setValueAt(df.format(service.getPrice()), rowIndex, 4);
        model.setValueAt(service.getUnit(), rowIndex, 5);
        model.setValueAt(service.getDescription(), rowIndex, 6);
        
    }
}
