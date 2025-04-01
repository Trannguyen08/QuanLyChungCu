
package main.java.com.utc2.apartmentManage.controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.java.com.utc2.apartmentManage.model.Resident;
import main.java.com.utc2.apartmentManage.service.managerService.residentService;



public class editButtonHandler {
    private JButton editBtn;
    private JTextField fullName, phoneNumber, email, idCard, apartmentID;
    private JComboBox<String> gender ;
    private JDateChooser birthDate;
    private JTable table;
    private JFrame edit;
    private final residentService residentService = new residentService();
    
    public editButtonHandler(JButton addBtn, JTextField apartmentID, JTextField fullName, JComboBox<String> gender, JDateChooser birthDate, 
                            JTextField phoneNumber, JTextField email, JTextField idCard, JTable table, JFrame edit) {
        this.editBtn = addBtn;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
        this.table = table;
        this.edit = edit;
        
        loadSelectedRowData();

        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
    }
    
    public void loadSelectedRowData() {
        boolean check = residentService.loadSelectedRowData(table, apartmentID, fullName, gender, birthDate, phoneNumber, email, idCard);
    }
 
    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
             
        // check input
        boolean check = residentService.validateData(table,  apartmentID, fullName, phoneNumber,  email, idCard,  birthDate,  gender);
        if (!check) {
            return;
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date utilDate = birthDate.getDate(); 
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        
        Resident resident = new Resident(id, fullName.getText().trim(), gender.getSelectedItem().toString(), sqlDate,
                                        phoneNumber.getText().trim(),email.getText().trim(), idCard.getText().trim(),
                                        Integer.parseInt(apartmentID.getText().trim()));
        
        // check update và update table
        if( residentService.updateResident(resident) ) {
            edit.setVisible(false);
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin cư dân thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            model.setValueAt(resident.getApartmentID(), selectedRow, 1);
            model.setValueAt(resident.getName(), selectedRow, 2);
            model.setValueAt(resident.getGender(), selectedRow, 3);
            model.setValueAt(resident.getBirthDate(), selectedRow, 4);
            model.setValueAt(resident.getPhoneNumber(), selectedRow, 5);
            model.setValueAt(resident.getIdCard(), selectedRow, 6);
            model.setValueAt(resident.getEmail(),selectedRow, 7);       
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin cư dân không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }   
    }
}

