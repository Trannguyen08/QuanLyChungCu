package java.utc2.apartmentManage.controller.manager.resident;

import com.toedter.calendar.JDateChooser;
import utc2.apartmentManage.model.Resident;
import utc2.apartmentManage.service.implement.manager.residentIMP;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchIconHandle {
    private JTextField fullName;
    private JComboBox<String> status;
    private JDateChooser birthDate, toBirthDate;
    private JButton searchBtn;
    private JTable table;
    private JFrame frame;
    private final residentIMP residentService = new residentIMP();

    public searchIconHandle(JTextField fullName, JComboBox<String> status, JDateChooser birthDate,
                            JDateChooser toBirthDate, JButton searchBtn, JTable table, JFrame frame) {
        this.fullName = fullName;
        this.status = status;
        this.birthDate = birthDate;
        this.toBirthDate = toBirthDate;
        this.searchBtn = searchBtn;
        this.table = table;
        this.frame = frame;


        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }

    public void filterTableData() {       
        boolean check = residentService.searchValidate(birthDate, toBirthDate);
        if( !check ) {
            return;
        }
   
        String start = ScannerUtil.convertJDateChooserToString(birthDate);
        String end = ScannerUtil.convertJDateChooserToString(toBirthDate);
        
        
        Resident resident = new Resident(0, fullName.getText().trim(),
                                        "", start, "", "", "", 0, 0, 0,
                                        status.getSelectedItem().toString().trim()
        );
        frame.setVisible(false);
        residentService.filterResident(table, resident, end);
    }
}