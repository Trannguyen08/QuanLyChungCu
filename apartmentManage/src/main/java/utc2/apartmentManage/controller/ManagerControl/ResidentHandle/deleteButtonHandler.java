package main.java.utc2.apartmentManage.controller.ManagerControl.ResidentHandle;

import main.java.utc2.apartmentManage.view.ManagerUI.Pages.ResidentUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2.apartmentManage.service.managerService.residentIMP;


public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final residentIMP residentService = new residentIMP();

    public deleteButtonHandler(JButton deleteBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;
    }

    public void deleteSelectedRow() {
        Integer id = residentService.getResidentId(table);
        if( id == null ) {
            return;
        }
        
        if( residentService.isStillContract(id) ) {
            JOptionPane.showMessageDialog(null, "Không thể xóa cư dân còn hợp đồng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (residentService.confirmDelete("cư dân")) {
            boolean isDeleted = (panel instanceof ResidentUI) && residentService.delete(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}

