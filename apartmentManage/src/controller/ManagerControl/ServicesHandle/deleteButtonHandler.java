package controller.ManagerControl.ServicesHandle;

import service.managerService.serviceService;
import view.ManagerUI.ServicesForm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final serviceService serviceService = new serviceService();

    public deleteButtonHandler(JButton deleteBtn, JTable table, JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;

        for (ActionListener al : deleteBtn.getActionListeners()) {
            deleteBtn.removeActionListener(al);
        }

        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });
    }

    private void deleteSelectedRow() {
        Integer id = serviceService.getServiceId(table);
        if (id == null) {
            return;
        }
        if (serviceService.confirmDelete()) {
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