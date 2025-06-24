package java.utc2.apartmentManage.service.interfaces;

import utc2.apartmentManage.model.*;

import javax.swing.*;

public interface IInfomation {
    public Resident getResidentByAccountID(int accID);
    public Apartment getApartmentByResidentID(int resID);
    public Contract getContractByResidentID(int resID);
    public boolean updateResident(Resident res);
    public boolean editValidate(JTextField email, JTextField phone, JTextField idcard);
    
}
