package main.java.utc2_apartmentManage.controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import main.java.utc2_apartmentManage.model.Resident;
import main.java.utc2_apartmentManage.repository.managerRepository.residentRepository;
import main.java.utc2_apartmentManage.service.managerService.residentService;
import main.java.utc2_apartmentManage.util.ScannerUtil;


public class searchIconHandler {
    private javax.swing.JTextField residentID, fullName, phoneNumber,email, idCard, apartmentID ;
    private javax.swing.JComboBox<String> gender;
    private JDateChooser birthDate;
    private JDateChooser toBirthDate;
    private javax.swing.JButton searchBtn;
    private JTable table;
    private JFrame frame;
    private final residentService residentService = new residentService();

    public searchIconHandler(JTextField residentID,JTextField apartmentID,  JTextField fullName, JComboBox<String> gender,
                                JDateChooser birthDate, JDateChooser toBirthDate, JTextField phoneNumber,
                                JTextField email, JTextField idCard,JButton searchBtn, JTable table, JFrame frame) {
        this.residentID = residentID;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.toBirthDate = toBirthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
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
        boolean check = residentService.validateSearchInput( residentID, apartmentID, birthDate, 
                                                            toBirthDate, phoneNumber, email, idCard);
        if( !check ) {
            return;
        }
        
        int resID = (residentID.getText().trim().isEmpty()) ? 0 : Integer.parseInt(residentID.getText().trim());
        int aptID = (apartmentID.getText().trim().isEmpty()) ? 0 : Integer.parseInt(apartmentID.getText().trim());
        
        
        String start = ScannerUtil.convertJDateChooserToString(birthDate);
        String end = ScannerUtil.convertJDateChooserToString(toBirthDate);
        
        
        Resident resident = new Resident(resID, fullName.getText().trim(), 
                                        gender.getSelectedItem().toString(), start, 
                                        phoneNumber.getText().trim(), email.getText().trim(), 
                                        idCard.getText().trim(), aptID);
        
        List<Resident> residentList = new residentRepository().getAllFilterResident(resident, end);
        residentService.addDataToTable(residentList, table);
        frame.setVisible(false);
        if( residentList.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
}