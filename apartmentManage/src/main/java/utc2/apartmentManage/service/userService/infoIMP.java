package main.java.utc2.apartmentManage.service.userService;

import javax.swing.*;
import main.java.utc2.apartmentManage.model.Apartment;
import main.java.utc2.apartmentManage.model.Contract;
import main.java.utc2.apartmentManage.model.Resident;
import main.java.utc2.apartmentManage.repository.ManagerRepository.apartmentRepository;
import main.java.utc2.apartmentManage.repository.UserRepository.infoUserRepository;
import main.java.utc2.apartmentManage.service.Interface.IInfomation;
import main.java.utc2.apartmentManage.util.ScannerUtil;

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
