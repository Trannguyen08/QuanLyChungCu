package main.java.utc2.apartmentManage.controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import main.java.utc2.apartmentManage.model.Resident;
import main.java.utc2.apartmentManage.repository.ManagerRepository.residentRepository;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import main.java.utc2.apartmentManage.service.managerService.residentIMP;



public class searchIconHandler {
    private JTextField fullName;
    private JComboBox<String> gender;
    private JDateChooser birthDate, toBirthDate;
    private JButton searchBtn;
    private JTable table;
    private JFrame frame;
    private final residentIMP residentService = new residentIMP();

    public searchIconHandler(JTextField fullName, JComboBox<String> gender,JDateChooser birthDate, 
                            JDateChooser toBirthDate, JButton searchBtn, JTable table, JFrame frame) {
        this.fullName = fullName;
        this.gender = gender;
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
                                        gender.getSelectedItem().toString(), start, 
                                        "", "", "", 0, 0, 0);
        frame.setVisible(false);
        residentService.filterResident(table, resident, end);
    }
}