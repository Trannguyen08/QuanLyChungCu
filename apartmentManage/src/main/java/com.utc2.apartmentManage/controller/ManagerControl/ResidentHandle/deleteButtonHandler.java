package main.java.com.utc2.apartmentManage.controller.ManagerControl.ResidentHandle;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.com.utc2.apartmentManage.service.managerService.residentService;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.Resident;



public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final residentService residentService = new residentService();

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
        
        if (residentService.confirmDelete()) {
            boolean isDeleted = (panel instanceof Resident) && 
                                residentService.deleteResident(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}

