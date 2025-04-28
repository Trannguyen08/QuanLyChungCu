package main.java.utc2.apartmentManage.service.Interface;

import javax.swing.*;
import main.java.utc2.apartmentManage.model.Service;

public interface IService {
    public boolean loadSelectedRowData(JTable table, JTextField serviceName, JComboBox<String> serviceType,
                                       JComboBox<String> relevant, JTextField price, JTextField unit, JTextArea description);
    public boolean getFilterServiceByIcon(Service service, double toPrice, JTable table);
    public boolean confirmDelete(String s);
    
}
