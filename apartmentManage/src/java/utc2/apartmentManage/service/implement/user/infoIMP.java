package java.utc2.apartmentManage.service.implement.user;

import utc2.apartmentManage.model.*;
import utc2.apartmentManage.repository.manager.apartmentRepository;
import utc2.apartmentManage.repository.user.infoUserRepository;
import utc2.apartmentManage.service.interfaces.IInfomation;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;

public class infoIMP implements IInfomation {
    private final infoUserRepository infoUserRepo = new infoUserRepository();
    private final apartmentRepository aptRepo = new apartmentRepository();
    
    @Override
    public Resident getResidentByAccountID(int accID) {
        return infoUserRepo.getResidentByAccountID(accID);
    }

    @Override
    public Apartment getApartmentByResidentID(int aptID) {
        return aptRepo.getApartmentById(aptID);
    }

    @Override
    public Contract getContractByResidentID(int resID) {
        return infoUserRepo.getContractByResidentID(resID);
    }
    
    @Override
    public boolean updateResident(Resident res) {
        return infoUserRepo.updateResident(res);
    }
    
    @Override
    public boolean editValidate(JTextField email, JTextField phone, JTextField idcard) {
        String emailStr = email.getText().trim();
        String phoneStr = phone.getText().trim();
        String idcardStr = idcard.getText().trim();
        
        if( emailStr.isEmpty() || phoneStr.isEmpty() || idcardStr.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", 
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if( !ScannerUtil.validateEmail(emailStr) ) {
            return false;
        }
        
        if( !ScannerUtil.validatePhoneNumber(phoneStr) ) {
            return false;
        }
        
        return ScannerUtil.isValidCCCD(idcardStr);
    }
    
}
