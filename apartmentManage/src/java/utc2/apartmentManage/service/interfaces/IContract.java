package java.utc2.apartmentManage.service.interfaces;

import utc2.apartmentManage.model.Contract;

import javax.swing.*;

public interface IContract {
    public boolean confirmDelete(String s);
    public boolean isAcceptedDelete(int id);
    public boolean filterContracts(Contract contract, String startDate, String endDate,
                                   double fromValue, double toValue, JTable table);
    public void updateTableWithContracts(String keyword, JTable table);
    public boolean addContract(Contract object, int aptID, int resID);
}
