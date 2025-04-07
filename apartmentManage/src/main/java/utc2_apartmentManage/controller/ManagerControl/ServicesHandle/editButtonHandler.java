package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import main.java.utc2_apartmentManage.model.Service;
import main.java.utc2_apartmentManage.service.managerService.serviceService;
import main.java.utc2_apartmentManage.util.ScannerUtil;


public class editButtonHandler {
    private JButton editBtn;
    private JTextField ServiceName, ServicePrice, ServiceUnit;
    private JTextArea note;
    private JComboBox<String> ServiceType;
    private JTable table;
    private JFrame edit;
    private serviceService ss = new serviceService();
        private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public editButtonHandler(JButton editBtn, JComboBox<String> ServiceType, JTextField ServiceName, 
                            JTextField ServicePrice, JTextField ServiceUnit, JTextArea note, JTable table, JFrame edit){
        this.editBtn = editBtn;
        this.ServiceName = ServiceName;
        this.ServicePrice = ServicePrice;
        this.ServiceUnit = ServiceUnit;
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
        ss.loadSelectedRowData(table, ServiceName, ServiceType, ServicePrice, ServiceUnit, note);
    }
    
    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();

        if( !ss.validateData(ServiceName, ServicePrice, ServiceUnit, ServiceType) ) {
            return;
        }

        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        Service service = new Service(id, ServiceName.getText().trim(),
                                       ServiceType.getSelectedItem().toString(),
                                       ScannerUtil.replaceDouble(ServicePrice),
                                       ServiceUnit.getText().trim(),
                                       note.getText().trim());

        if( !ss.updateService(service) ) {
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
        model.setValueAt(df.format(service.getPrice()), rowIndex, 3);
        model.setValueAt(service.getUnit(), rowIndex, 4);
        model.setValueAt(service.getDescription(), rowIndex, 5);
        
    }
}
