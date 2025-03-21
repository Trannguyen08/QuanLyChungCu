package Controller.ManagerControl;

import Model.ManagerDAO.deleteButton;
import View.ManagerUI.Apartment;
import View.ManagerUI.Contract;
import View.ManagerUI.Employee;
import View.ManagerUI.Resident;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteButtonHandler {
    private JButton deleteBtn;
    private JTable table;
    private JPanel panel;

    public deleteButtonHandler(JButton deleteBtn, JTable table,JPanel panel) {
        this.deleteBtn = deleteBtn;
        this.table = table;
        this.panel = panel;

        this.deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedRow();
            }
        });
    }


    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow != -1 ) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int id = (int) model.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc muốn xóa hàng này khỏi?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if( confirm == JOptionPane.YES_OPTION ) {
                model.removeRow(selectedRow);
                if( panel.equals(new Apartment()) ) {
                    deleteButton.deleteRowInTable("apartments", id);
                } else if( panel.equals(new Resident()) ) {
                    deleteButton.deleteRowInTable("residents", id);
                } else if( panel.equals(new Employee()) ) {
                    deleteButton.deleteRowInTable("employees", id);
                } 

            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa!",
                    "Lỗi", JOptionPane.WARNING_MESSAGE);
        }
    }
}
