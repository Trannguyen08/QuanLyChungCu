package main.java.utc2.apartmentManage.controller.ManagerControl.ContractControl;

import com.toedter.calendar.JDateChooser;
import main.java.utc2.apartmentManage.model.Contract;
import main.java.utc2.apartmentManage.service.managerService.contractIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



public class searchIconHandler {
    private JTextField ownerName, toValue, fromValue;
    private JButton searchBtn;
    private com.toedter.calendar.JDateChooser startDate, endDate;
    private JComboBox<String> contractType, contractStatus;
    private JTable table;
    private JFrame frame;
    private contractIMP contract_service = new contractIMP();

    public searchIconHandler(JTextField ownerName, JComboBox<String> contractType, JTextField fromValue,
                             JTextField toValue, JDateChooser startDate, JDateChooser endDate,
                             JComboBox<String> contractStatus, JButton searchBtn,  JTable table, JFrame frame) {
        
        this.ownerName = ownerName;
        this.toValue = toValue;
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
        boolean check = contract_service.searchValidate(fromValue, toValue,
                                                            startDate, endDate);
        
        if( !check ) {
            return;
        }
        
        if( contract_service.checkAllNull(ownerName, fromValue, toValue, 
                                        startDate, endDate, contractStatus, contractType) ) {
            
            frame.setVisible(false);
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", 
                                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        
        String start = ScannerUtil.convertJDateChooserToString(startDate);
        String end = ScannerUtil.convertJDateChooserToString(endDate);
       

        // Xử lý giá trị value
        double minValue = fromValue.getText().trim().isEmpty() ? 0 : Double.parseDouble(fromValue.getText().trim());
        double maxValue = toValue.getText().trim().isEmpty() ? 20000000 : Double.parseDouble(toValue.getText().trim());

        Contract contract = new Contract(0, ownerName.getText().trim(), " ",
                                         contractType.getSelectedItem().toString().trim(), 
                                         " ", " ", 0, 
                                         contractStatus.getSelectedItem().toString().trim());

        boolean checkRun = contract_service.filterContracts(contract, start, end, 
                                         minValue, maxValue, table);
        
        frame.setVisible(false);
        if( !checkRun ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", 
                                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}

