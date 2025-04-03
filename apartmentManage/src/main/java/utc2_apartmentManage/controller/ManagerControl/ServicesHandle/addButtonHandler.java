package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class addButtonHandler {
    private JButton addBtn;
    private  JComboBox<String> ServiceType;
    private JTextField ServiceID, ServiceName, ServicePrice, ServiceUnit;
    private JTable table;
    private JFrame add;
    
    public addButtonHandler(JTextField ServiceID,JTextField ServiceName,JTextField ServicePrice,JTextField ServiceUnit, JComboBox<String> ServiceType){
        this.ServiceType = ServiceType;
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.ServicePrice = ServicePrice;
        this.ServiceUnit = ServiceUnit;
        this.table = table;
        this.add = add;
        
        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }
    private void addNewRow(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                null,
                ServiceID.getText(),
                ServiceName.getText(),
                ServicePrice.getText(),
                ServiceType.getSelectedItem(),
                ServiceUnit.getText()
        });
        add.setVisible(false);
        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}
