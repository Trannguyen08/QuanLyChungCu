package main.java.utc2.apartmentManage.controller.ManagerControl.ServicesHandle;

import main.java.utc2.apartmentManage.view.ManagerUI.Pages.ServicesUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2.apartmentManage.model.Service;
import main.java.utc2.apartmentManage.service.managerService.serviceIMP;



public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final serviceIMP serviceService = new serviceIMP();
    

    public deleteButtonHandler(JButton deleteBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;
    }

    
    
    public void deleteSelectedRow() {
        if( !serviceService.isSelectedRow(table) ) {
            return;
        }
        int selectedRow = table.getSelectedRow();
        int id = (Integer) table.getValueAt(selectedRow, 0);
        Service s = serviceService.getObject(id);
        
        if( s.getRelevant().equals("Căn hộ") ) {
            JOptionPane.showMessageDialog(null, "Đây là dịch vụ liên quan đến căn hộ. Không thể xóa", 
                                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (serviceService.confirmDelete("dịch vụ")) {
            boolean isDeleted = (panel instanceof ServicesUI) && serviceService.delete(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}