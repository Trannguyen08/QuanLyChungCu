package main.java.utc2.apartmentManage.service.Interface;

import javax.swing.JTextField;
import main.java.utc2.apartmentManage.model.*;

public interface IInfomation {
    public Resident getResidentByAccountID(int accID);
    public Apartment getApartmentByResidentID(int resID);
    public Contract getContractByResidentID(int resID);
    public boolean updateResident(Resident res);
    public boolean editValidate(JTextField email, JTextField phone, JTextField idcard);
    
}
