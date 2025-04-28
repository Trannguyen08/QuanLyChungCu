package main.java.utc2.apartmentManage.controller.ManagerControl.ServicesHandle;


import main.java.utc2.apartmentManage.model.Service;
import main.java.utc2.apartmentManage.service.managerService.serviceService;
import main.java.utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class searchIconHandler {
    private JTextField ServiceName, fromServicePrice, toServicePrice;
    private JComboBox<String> ServiceType, relevant;
    private JTable table;
    private JFrame frame;
    private JButton searchBtn;
    private serviceService ss = new serviceService();
    
    public searchIconHandler(JTextField ServiceName, JComboBox<String> ServiceType, 
                            JComboBox<String> relevant, JTextField fromServicePrice, JTextField toServicePrice, 
                            JButton searchBtn, JTable table, JFrame frame){
        
        this.ServiceName = ServiceName;
        this.ServiceType = ServiceType;
        this.relevant = relevant;
        this.fromServicePrice = fromServicePrice;
        this.toServicePrice = toServicePrice;
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
        boolean check = ss.validateSearchInput(fromServicePrice, toServicePrice);
        if( !check ) {
            return;
        }
        
        double fp = (fromServicePrice.getText() == null || fromServicePrice.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(fromServicePrice);

        double tp = (toServicePrice.getText() == null || toServicePrice.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(toServicePrice);
        
        Service service = new Service(0, ServiceName.getText().trim(),
                                       ServiceType.getSelectedItem().toString().trim(),
                                       relevant.getSelectedItem().toString().trim(),
                                       fp, "",
                                       "");
        
        boolean checkRun = ss.getFilterServiceByIcon(service, tp, table);
        frame.setVisible(false);
        if( !checkRun ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
