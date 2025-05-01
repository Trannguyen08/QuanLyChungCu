package main.java.utc2.apartmentManage.controller.UserControl.InfomationControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import main.java.utc2.apartmentManage.model.Resident;
import main.java.utc2.apartmentManage.service.userService.infoIMP;


public class editResidentInfoHandle {
    private int infoID;
    private JLabel mailLabel, phoneLabel, idcardLabel;
    private JTextField email, phone, idcard;
    private JButton editBtn;
    private JFrame frame;
    private final infoIMP infoService = new infoIMP();

    public editResidentInfoHandle(int infoID, JLabel mailLabel, JLabel phoneLabel, JLabel idcardLabel, 
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
        
        Resident res = new Resident(
                0, "", "", "",
                phone.getText().trim(),
                email.getText().trim(),
                idcard.getText().trim(),
                0,0,infoID
        );
        
        mailLabel.setText(email.getText().trim());
        phoneLabel.setText(phone.getText().trim());
        idcardLabel.setText(idcard.getText().trim());
        
        frame.setVisible(false);
        if( infoService.updateResident(res) ) {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    
    
    
}
