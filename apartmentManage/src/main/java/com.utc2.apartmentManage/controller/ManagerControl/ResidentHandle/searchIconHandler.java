package main.java.com.utc2.apartmentManage.controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.com.utc2.apartmentManage.model.Resident;
import main.java.com.utc2.apartmentManage.service.managerService.residentService;



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


        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }

    public void filterTableData() {       
        boolean check = residentService.validateSearchInput( residentID, apartmentID, fullName, gender, birthDate, 
                                                            toBirthDate, phoneNumber, email, idCard);
        if( !check ) {
            return;
        }
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (birthDate.getDate() != null && toBirthDate.getDate() == null) {
                toBirthDate.setDate(new Date()); // Set endDate thành ngày hôm nay
            } else if (birthDate.getDate() == null && toBirthDate.getDate() != null) {
                birthDate.setDate(dateFormat.parse("2000-01-01"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = birthDate.getDate(); 
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        Resident resident = new Resident(Integer.parseInt(residentID.getText().trim()), fullName.getText().trim(), 
                                        gender.getSelectedItem().toString(), sqlDate, 
                                        phoneNumber.getText().trim(), email.getText().trim(), 
                                        idCard.getText().trim(), Integer.parseInt(apartmentID.getText().trim()));
        
        

        
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}