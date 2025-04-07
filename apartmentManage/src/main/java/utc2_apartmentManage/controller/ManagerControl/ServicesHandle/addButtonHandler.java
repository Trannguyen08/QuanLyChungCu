package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2_apartmentManage.model.Service;
import main.java.utc2_apartmentManage.service.managerService.serviceService;
import main.java.utc2_apartmentManage.util.ScannerUtil;

public class addButtonHandler {
    private JButton addBtn;
    private JComboBox<String> ServiceType;
    private JTextField ServiceName, ServicePrice, ServiceUnit;
    private JTextArea ServiceNote;
    private JTable table;
    private JFrame add;
    private serviceService ss = new serviceService();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public addButtonHandler(JButton addBtn, JTextArea ServiceNote ,JTextField ServiceName, JTextField ServicePrice,
                            JTextField ServiceUnit, JComboBox<String> ServiceType, JTable table, JFrame add){
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
        // Kiểm tra dữ liệu hợp lệ trước khi tiếp tục
        boolean check = ss.validateData(ServiceName, ServicePrice, ServiceUnit, ServiceType);
        if (!check) {
            return;
        }

        // Tạo đối tượng Employee
        int id = ss.getNewID();
        
        Service service = new Service(id, ServiceName.getText().trim(),
                                      ServiceType.getSelectedItem().toString().trim(),
                                      ScannerUtil.replaceDouble(ServicePrice), 
                                      ServiceUnit.getText().trim(),
                                      ServiceNote.getText().trim());
        
        // Kiểm tra trùng lặp nhân viên
        if (ss.isDuplicate(service)) {
            JOptionPane.showMessageDialog(null, "Dịch vụ này đã tồn tại!", "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // thêm vào database và table
        boolean isAddedComplete = ss.addService(service);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] {service.getServiceId(), service.getServiceName(),
                                            service.getServiceType(), df.format(service.getPrice()),
                                            service.getUnit(), service.getDescription()});

        add.setVisible(false);
        if( isAddedComplete ) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
