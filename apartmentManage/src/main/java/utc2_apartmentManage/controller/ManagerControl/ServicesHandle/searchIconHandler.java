package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;

import main.java.utc2_apartmentManage.util.ScannerUtil;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.utc2_apartmentManage.model.Service;
import main.java.utc2_apartmentManage.service.managerService.serviceService;

public class searchIconHandler {
    private JTextField ServiceID, ServiceName, fromServicePrice, toServicePrice, ServiceUnit;
    private JComboBox<String> ServiceType;
    private JTextArea note;
    private JTable table;
    private JFrame frame;
    private JButton searchBtn;
    private serviceService ss = new serviceService();
    
    public searchIconHandler(JTextField ServiceID, JTextField ServiceName,JTextField fromServicePrice, 
                            JTextField toServicePrice,JTextField  ServiceUnit, JComboBox<String> ServiceType, 
                            JTextArea note, JTable table, JFrame frame, JButton searchBtn){
        
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.ServiceType = ServiceType;
        this.fromServicePrice = fromServicePrice;
        this.toServicePrice = toServicePrice;
        this.ServiceUnit = ServiceUnit;
        this.table = table;
        this.note = note;
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
        boolean check = ss.validateSearchInput(ServiceID, ServiceName, fromServicePrice, ServiceUnit, ServiceType, toServicePrice);
        if( !check ) {
            return;
        }
        
        int id = (ServiceID.getText().trim().isEmpty()) ? 0 : Integer.parseInt(ServiceID.getText().trim());
        
        double fp = (fromServicePrice.getText() == null || fromServicePrice.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(fromServicePrice);

        double tp = (toServicePrice.getText() == null || toServicePrice.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(toServicePrice);
        
        Service service = new Service(id, ServiceName.getText().trim(), 
                                       ServiceType.getSelectedItem().toString().trim(),
                                       fp, ServiceUnit.getText().trim(),
                                       note.getText().trim());
        
        boolean checkRun = ss.getFilterServiceByIcon(service, tp, table);
        frame.setVisible(false);
        if( !checkRun ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
