package java.utc2.apartmentManage.controller.manager.resident;

import utc2.apartmentManage.service.implement.manager.residentIMP;
import utc2.apartmentManage.view.manager.pages.ResidentUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class deleteButtonHandle {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final residentIMP residentService = new residentIMP();

    public deleteButtonHandle(JButton deleteBtn, JTable table, JPanel panel) {
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

