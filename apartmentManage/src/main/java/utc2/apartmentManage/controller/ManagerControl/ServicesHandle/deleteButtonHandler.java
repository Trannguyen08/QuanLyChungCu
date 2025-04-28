package main.java.utc2.apartmentManage.controller.ManagerControl.ServicesHandle;

import main.java.utc2.apartmentManage.service.managerService.serviceService;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.ServicesUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;


public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final serviceService serviceService = new serviceService();
    private List<String> serviceImportant = Arrays.asList(
        "Điện",
        "Nước",
        "Phí quản lý",
        "Vệ sinh"
    );

    public deleteButtonHandler(JButton deleteBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;
    }

    
    
    public void deleteSelectedRow() {
        Integer id = serviceService.getServiceId(table);
        if (id == null) {
            return;
        }
        if( true ) {
            JOptionPane.showMessageDialog(null, "Đây là 1 dịch vụ quan trọng. Không thể xóa", 
                                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (serviceService.confirmDelete("dịch vụ")) {
            boolean isDeleted = (panel instanceof ServicesUI) && serviceService.deleteService(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}