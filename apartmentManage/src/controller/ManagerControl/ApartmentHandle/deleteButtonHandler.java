package controller.ManagerControl.ApartmentHandle;

import service.managerService.apartmentService;
import view.ManagerUI.ApartmentUI;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final apartmentService apartmentservice = new apartmentService();

    public deleteButtonHandler(JButton deleteBtn, JTable table,JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;

    }


    public void deleteSelectedRow() {
        Integer id = apartmentservice.getApartmentId(table);
        if( id == null ) {
            return;
        }
        if (apartmentservice.confirmDelete()) {
            boolean isDeleted = (panel instanceof ApartmentUI) && apartmentservice.deleteApartment(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}
