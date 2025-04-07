package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;

import main.java.utc2_apartmentManage.view.ManagerUI.Pages.ServicesForm;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import main.java.utc2_apartmentManage.service.managerService.serviceService;

public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final serviceService serviceService = new serviceService();
    private List<String> serviceImportant = Arrays.asList(
        "Điện",
        "Nước",
        "Phí quản lý",
        "Phí vệ sinh",
        "Internet",
        "Gửi xe máy",
        "Gửi xe ô tô"
    );

    public deleteButtonHandler(JButton deleteBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;
    }

    private boolean isImportantService(String serviceName) {
        for( String s : serviceImportant ) {
            if( serviceName.equals(s) ) {
                return true;
            }
        }
        return false;
    }
    public void deleteSelectedRow() {
        Integer id = serviceService.getServiceId(table);
        String serviceName = serviceService.getServiceName(table);
        if (id == null) {
            return;
        }
        if( isImportantService(serviceName) ) {
            JOptionPane.showMessageDialog(null, "Đây là 1 dịch vụ quan trọng. Không thể xóa", 
                                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if (serviceService.confirmDelete("dịch vụ")) {
            boolean isDeleted = (panel instanceof ServicesForm) && serviceService.deleteService(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}