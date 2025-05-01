package main.java.utc2.apartmentManage.controller.ManagerControl.ServicesHandle;

import main.java.utc2.apartmentManage.model.Service;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2.apartmentManage.service.managerService.serviceIMP;


public class addButtonHandler {
    private JButton addBtn;
    private JComboBox<String> ServiceType, relevant;
    private JTextField ServiceName, ServicePrice, ServiceUnit;
    private JTextArea ServiceNote;
    private JTable table;
    private JFrame add;
    private serviceIMP ss = new serviceIMP();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public addButtonHandler(JButton addBtn, JTextArea ServiceNote ,JTextField ServiceName, JComboBox<String> relevant,
                            JTextField ServicePrice, JTextField ServiceUnit, 
                            JComboBox<String> ServiceType, JTable table, JFrame add){
        this.ServiceType = ServiceType;
        this.ServiceName = ServiceName;
        this.ServicePrice = ServicePrice;
        this.ServiceUnit = ServiceUnit;
        this.ServiceNote = ServiceNote;
        this.addBtn = addBtn;
        this.table = table;
        this.add = add;
        
        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }
    private void addNewRow() {
        
        boolean check = ss.addValidate(ServiceName, ServicePrice, ServiceUnit);
        if (!check) {
            return;
        }

        int id = ss.getNewID();
        Service service = new Service(id, ServiceName.getText().trim(),
                                      ServiceType.getSelectedItem().toString().trim(),
                                      relevant.getSelectedItem().toString().trim(),
                                      ScannerUtil.replaceDouble(ServicePrice),
                                      ServiceUnit.getText().trim(),
                                      ServiceNote.getText().trim());
        
        // Kiểm tra trùng lặp tên
        if (ss.isDuplicate(service)) {
            JOptionPane.showMessageDialog(null, "Dịch vụ này đã tồn tại!", "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // thêm vào database và table
        if( ss.add(service) ) {
            add.setVisible(false);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.addRow(new Object[] { service.getServiceId(), service.getServiceName(),
                                            service.getServiceType(), service.getRelevant(),
                                            df.format(service.getPrice()),
                                            service.getUnit(), service.getDescription()});
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
