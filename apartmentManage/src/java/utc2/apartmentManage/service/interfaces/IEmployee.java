package java.utc2.apartmentManage.service.interfaces;

import utc2.apartmentManage.model.Employee;

import javax.swing.*;

public interface IEmployee {
    public boolean loadSelectedRowData(JTable table, JComboBox<String> position, JTextField salary);
    public boolean filterEmployeeIcon(JTable table, Employee emp, double toValue);
    public boolean confirmDelete(String s);
    public Integer getEmployeeId(JTable table);
    
}
