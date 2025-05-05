package main.java.utc2.apartmentManage.controller.employeesControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.java.utc2.apartmentManage.model.Employee;
import main.java.utc2.apartmentManage.service.employeesService.employeeInfoIMP;
import main.java.utc2.apartmentManage.service.managerService.residentIMP;
import main.java.utc2.apartmentManage.service.userService.infoIMP;


public class editEmployeeInfoHandle {
    private int infoID, employee_id;
    private JLabel mailLabel, phoneLabel, idcardLabel;
    private JTextField email, phone, idcard;
    private JButton editBtn;
    private JFrame frame;
    private final infoIMP infoService = new infoIMP();
    private final residentIMP residentService = new residentIMP();
    private final employeeInfoIMP employeeInfoService = new employeeInfoIMP();

    public editEmployeeInfoHandle(int infoID, int employee_id, JLabel mailLabel, JLabel phoneLabel, JLabel idcardLabel, 
                                JTextField email, JTextField phone, JTextField idcard, JButton editBtn, JFrame frame) {
        this.mailLabel = mailLabel;
        this.phoneLabel = phoneLabel;
        this.idcardLabel = idcardLabel;
        this.email = email;
        this.phone = phone;
        this.idcard = idcard;
        this.editBtn = editBtn;
        this.infoID = infoID;
        this.frame = frame;
        this.employee_id = employee_id;
        
        this.email.setText(mailLabel.getText());
        this.phone.setText(phoneLabel.getText());
        this.idcard.setText(idcardLabel.getText());
        
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editBtnClick();
            }
        });
    }
    
    public void editBtnClick() {
        if( !infoService.editValidate(email, phone, idcard) ) {
            return;
        }
        
        Employee emp = new Employee(employee_id, "", "", "", 
                                    phone.getText().trim(),
                                    email.getText().trim(),
                                    idcard.getText().trim(),
                                    "", 0, "", infoID, 0, ""
        );
        if( !employeeInfoService.isDuplicate(emp) ) {
            return;
        }
        
        mailLabel.setText(email.getText().trim());
        phoneLabel.setText(phone.getText().trim());
        idcardLabel.setText(idcard.getText().trim());
        
        frame.setVisible(false);
        if( employeeInfoService.update(emp) ) {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
    
    
}
