package main.java.utc2.apartmentManage.service.Interface;

import javax.swing.*;

public interface INotification {
    public boolean confirmDelete(String s);
    public boolean loadSelectedRowData(JTable table, JTextField title, JTextArea mess, JComboBox<String> type);
}
