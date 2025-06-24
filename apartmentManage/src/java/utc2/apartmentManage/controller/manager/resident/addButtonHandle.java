package java.utc2.apartmentManage.controller.manager.resident;

import com.toedter.calendar.JDateChooser;
import org.mindrot.jbcrypt.BCrypt;
import utc2.apartmentManage.model.*;
import utc2.apartmentManage.repository.manager.*;
import utc2.apartmentManage.service.implement.login.registerIMP;
import utc2.apartmentManage.service.implement.manager.*;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class addButtonHandle {
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

    public addButtonHandle(JButton addBtn, JTextField username, JTextField fullname,
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
        if (!checkValidate) {
            return;
        }

        int aptID = Integer.parseInt(idch.getText().trim());
        int newAccountID = registerService.getNewID();
        Account acc = new Account(newAccountID, username.getText().trim(),
                                  BCrypt.hashpw(password.getText().trim(), BCrypt.gensalt(12)), 
                                  email.getText().trim(),
                                  phonenum.getText().trim(), "user");

        int newResidentID = residentService.getNewID();
        Resident resident = new Resident(newResidentID,
                                        fullname.getText().trim(),
                                        residentService.getApartmentInfo(aptID),
                                        ScannerUtil.convertJDateChooserToString(bDate),
                                        phonenum.getText().trim(),
                                        email.getText().trim(),
                                        idcard.getText().trim(),
                                        aptID,
                                        newAccountID,
                                        ir.getNewID(),
                                        "Hiệu lực"
        );

        // VALIDATE THỜI HẠN HỢP ĐỒNG
        java.util.Date startDateValue = startDate.getDate();
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(startDateValue);

        if (contracttype.getSelectedItem().toString().trim().equals("Cho thuê")) {
            int monthsToAdd = Integer.parseInt(deadline.getSelectedItem().toString());
            cal.add(java.util.Calendar.MONTH, monthsToAdd);
        } else if (contracttype.getSelectedItem().toString().trim().equals("Mua bán")) {
            cal.add(java.util.Calendar.YEAR, 60); // Cộng thêm 60 năm
        }

        java.util.Date endDateValue = cal.getTime();

        String startDateString = ScannerUtil.convertJDateChooserToString(startDate);
        String endDateString = ScannerUtil.convertDateToString(endDateValue);
        
        

        Apartment apartment = aptService.getObject(aptID);

        int newContractID = contractService.getNewID();
        Contract contract = new Contract(newContractID,
                                        fullname.getText().trim(),
                                        "", contracttype.getSelectedItem().toString().trim(),
                                        startDateString, endDateString, 0, "Hiệu lực");

        if (contracttype.getSelectedItem().toString().trim().equals("Cho thuê")) {
            contract.setContractValue(apartment.getRentPrice());
            new apartmentRepository().updateApartmentStatus(aptID, "Đã thuê");
        } else {
            contract.setContractValue(apartment.getPurchasePrice());
            new apartmentRepository().updateApartmentStatus(aptID, "Đã bán");
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                    resident.getResidentID(),
                    resident.getName(),
                    resident.getGender(),
                    resident.getBirthDate(),
                    resident.getPhoneNumber(),
                    resident.getIdCard(),
                    resident.getEmail(),
                    resident.getContractStatus()
        });

        frame.setVisible(false);
        if (registerService.addAccount(acc) &&
            residentService.add(resident) &&
            contractService.addContract(contract, aptID, newResidentID)) {

            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
}

}

