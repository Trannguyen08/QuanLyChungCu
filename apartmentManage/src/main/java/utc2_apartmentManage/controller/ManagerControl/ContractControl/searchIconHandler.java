package main.java.utc2_apartmentManage.controller.ManagerControl.ContractControl;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import main.java.utc2_apartmentManage.util.ScannerUtil;
import main.java.utc2_apartmentManage.model.Contract;
import main.java.utc2_apartmentManage.service.managerService.contractService;


public class searchIconHandler {
    private javax.swing.JTextField ownerName, toValue, id, fromValue;
    private javax.swing.JButton searchBtn;
    private com.toedter.calendar.JDateChooser startDate, endDate;
    private javax.swing.JComboBox<String> contractType, contractStatus;
    private JTable table;
    private JFrame frame;
    private contractService contract_service = new contractService();

    public searchIconHandler(JTextField ownerName, JTextField toValue, JTextField id, JTextField fromValue, 
                            JButton searchBtn, JDateChooser startDate, JDateChooser endDate, 
                            JComboBox<String> contractType, JComboBox<String> contractStatus, JTable table, JFrame frame) {
        
        this.ownerName = ownerName;
        this.toValue = toValue;
        this.id = id;
        this.fromValue = fromValue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.contractType = contractType;
        this.contractStatus = contractStatus;
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
        boolean check = contract_service.validateSeachInput(id, ownerName, fromValue, toValue, 
                                                            startDate, endDate, contractType);
        
        if( !check ) {
            return;
        }
        
        if( contract_service.checkAllNull(id, ownerName, fromValue, toValue, 
                                        startDate, endDate, contractStatus, contractType) ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", 
                                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            frame.setVisible(false);
            return;
        }
        int myID = 0;
        String idText = id.getText().trim();

        if( !idText.isEmpty() ) { 
            myID = Integer.parseInt(idText);
            System.out.println(myID);
        }
        
        String start = ScannerUtil.convertJDateChooserToString(startDate);
        String end = ScannerUtil.convertJDateChooserToString(endDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        if (startDate.getDate() == null && endDate.getDate() != null) {
            start = "01/01/2015";
        } else if (startDate.getDate() != null && endDate.getDate() == null) {
            end = "01/01/2190";
        }

        // Xử lý giá trị value
        double minValue = fromValue.getText().trim().isEmpty() ? 0 : Double.parseDouble(fromValue.getText().trim());
        double maxValue = toValue.getText().trim().isEmpty() ? Double.MAX_VALUE : Double.parseDouble(toValue.getText().trim());

        Contract contract = new Contract(myID, ownerName.getText().trim(), " ", 
                                         contractType.getSelectedItem().toString().trim(), 
                                         " ", " ", 0, 
                                         contractStatus.getSelectedItem().toString().trim());

        boolean checkRun = contract_service.filterContracts(contract, start, end, 
                                         minValue, maxValue, table);
        
        frame.setVisible(false);
        JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", 
                                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
    
}

