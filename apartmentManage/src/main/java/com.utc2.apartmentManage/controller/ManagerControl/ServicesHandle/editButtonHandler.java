package main.java.com.utc2.apartmentManage.controller.ManagerControl.ServicesHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class editButtonHandler {
    private JButton editBtn;
    private JTextField ServiceID, ServiceName, ServicePrice, ServiceUnit;
    private JComboBox<String> ServiceType;
    private JTable table;
    private JFrame edit;
    
    public editButtonHandler(JButton editBtn, JComboBox<String> ServiceType, JTextField ServiceID, JTextField ServiceName, JTextField ServicePrice, JTextField ServiceUnit, JTable table, JFrame edit){
        this.editBtn =editBtn;
        this.ServiceID = ServiceID;
        this.ServiceName = ServiceName;
        this.ServicePrice = ServicePrice;
        this.ServiceUnit = ServiceUnit;
        this.ServiceType = ServiceType;
        this.table = table;
        this.edit = edit;
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
    }
    public void loadSelectedRowData() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if( model == null ) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for( int col = 0 ; col < model.getColumnCount() ; col++ ) {
            if( model.getValueAt(selectedRow, col ) == null) {
                model.setValueAt("", selectedRow, col);
            }
        }
        ServiceID.setText(model.getValueAt(selectedRow, 1).toString());
        ServiceName.setText(model.getValueAt(selectedRow, 2).toString());
        ServiceType.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        ServicePrice.setText(model.getValueAt(selectedRow, 4).toString());
        ServiceUnit.setText(model.getValueAt(selectedRow, 5).toString());
  
    }
    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if( model == null ) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        model.setValueAt(ServiceID.getText(), selectedRow, 1);
        model.setValueAt(ServiceName.getText(), selectedRow, 2);
        model.setValueAt(ServiceType.getSelectedItem(), selectedRow, 3);
        model.setValueAt(ServicePrice.getText(), selectedRow, 4);
        model.setValueAt(ServiceUnit.getText(), selectedRow, 5);
        
        edit.setVisible(false);
        JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}
