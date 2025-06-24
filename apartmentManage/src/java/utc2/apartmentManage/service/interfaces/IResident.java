package java.utc2.apartmentManage.service.interfaces;

import utc2.apartmentManage.model.Resident;

import javax.swing.*;

public interface IResident {
    public void filterResident(JTable table, Resident resident, String toDate);
    public boolean isStillContract(int id);
    public boolean confirmDelete(String s);
    public Integer getResidentId(JTable table);
}
