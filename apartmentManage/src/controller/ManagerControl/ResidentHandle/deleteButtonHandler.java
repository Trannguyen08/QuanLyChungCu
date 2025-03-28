
package controller.ManagerControl.ResidentHandle;

import dao.managerDAO.ResidentDAO;
import service.managerService.residentService;
import view.ManagerUI.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author nghia
 */
public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;
    private final residentService residentService = new residentService();

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
        Integer id = residentService.getResidentId(table);
        if( id == null ) {
            return;
        }
        if (residentService.confirmDelete()) {
            boolean isDeleted = (panel instanceof Employee) && residentService.deleteResident(id);
            if (isDeleted) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

}

