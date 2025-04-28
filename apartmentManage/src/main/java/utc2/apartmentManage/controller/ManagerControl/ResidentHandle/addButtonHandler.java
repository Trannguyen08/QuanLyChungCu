package main.java.utc2.apartmentManage.controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.model.Apartment;
import main.java.utc2.apartmentManage.model.Contract;
import main.java.utc2.apartmentManage.model.Resident;
import main.java.utc2.apartmentManage.repository.ManagerRepository.infoRepository;
import main.java.utc2.apartmentManage.service.loginService.registerIMP;
import main.java.utc2.apartmentManage.service.managerService.apartmentIMP;
import main.java.utc2.apartmentManage.service.managerService.contractIMP;
import main.java.utc2.apartmentManage.service.managerService.residentIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;

public class addButtonHandler {
    private JButton addBtn;
    private JTextField username, fullname, idcard, idch, phonenum, email;
    private JPasswordField password;
    private JComboBox<String> gender, contracttype, deadline;
    private JDateChooser bDate, startDate;
    private JFrame frame;
    private JTable table;
    private residentIMP residentService = new residentIMP();
    private registerIMP registerService = new registerIMP();
    private contractIMP contractService = new contractIMP();
    private apartmentIMP aptService = new apartmentIMP();
    private infoRepository ir = new infoRepository();

    public addButtonHandler(JButton addBtn, JTextField username, JTextField fullname, 
                            JTextField idcard, JTextField idch, JTextField phonenum, 
                            JTextField email, JPasswordField password, JComboBox<String> gender, 
                            JComboBox<String> contracttype, JComboBox<String> deadline, 
                            JDateChooser bDate, JDateChooser startDate, JFrame frame, JTable table) {
        
        this.addBtn = addBtn;
        this.username = username;
        this.fullname = fullname;
        this.idcard = idcard;
        this.idch = idch;
        this.phonenum = phonenum;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contracttype = contracttype;
        this.deadline = deadline;
        this.bDate = bDate;
        this.startDate = startDate;
        this.frame = frame;
        this.table = table;
        
        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
    }
    
    public void add() {
        boolean checkValidate = residentService.addValidate(username, password, fullname, phonenum,
                                                            idcard, email, idch, bDate, startDate);
        if( !checkValidate ) {
            return;
        }
        int aptID = Integer.parseInt(idch.getText().trim());
        int newAccountID = registerService.getNewID();
        Account acc = new Account(newAccountID, username.getText().trim(),
                                  password.getText().trim(), email.getText().trim(),
                                  phonenum.getText().trim(), "user");
        
        int newResidentID = residentService.getNewID();
        Resident resident = new Resident(newResidentID,
                                        fullname.getText().trim(),
                                        gender.getSelectedItem().toString().trim(),
                                        ScannerUtil.convertJDateChooserToString(bDate),
                                        phonenum.getText().trim(),
                                        email.getText().trim(),
                                        idcard.getText().trim(),
                                        aptID,
                                        newAccountID,
                                        ir.getNewID()
        );
        
        java.util.Date startDateValue = startDate.getDate();
        int monthsToAdd = Integer.parseInt(deadline.getSelectedItem().toString());

        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(startDateValue);
        cal.add(java.util.Calendar.MONTH, monthsToAdd);

        java.util.Date endDateValue = cal.getTime();

        String startDateString = ScannerUtil.convertJDateChooserToString(startDate);
        String endDateString = ScannerUtil.convertDateToString(endDateValue);
        
        Apartment apartment = aptService.getObject(aptID);
        
        int newContractID = contractService.getNewID();
        Contract contract = new Contract(newContractID,
                                        fullname.getText().trim(),
                                        "", contracttype.getSelectedItem().toString().trim(),
                                        startDateString, endDateString, 0, "Hiệu lực");
        
        if( contracttype.getSelectedItem().toString().trim().equals("Cho thuê") ) {
            contract.setContractValue(apartment.getRentPrice());
        } else {
            contract.setContractValue(apartment.getPurchasePrice());
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                    resident.getResidentID(),
                    resident.getName(),
                    resident.getGender(),
                    resident.getBirthDate(),
                    resident.getPhoneNumber(),
                    resident.getIdCard(),
                    resident.getEmail()
        });
        
        
        frame.setVisible(false);
        if ( registerService.addAccount(acc) &&
            residentService.add(resident) &&
            contractService.addContract(contract, aptID, newResidentID) ) {
            
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

