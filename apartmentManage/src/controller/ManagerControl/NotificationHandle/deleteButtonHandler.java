package controller.ManagerControl.NotificationHandle;

import view.ManagerUI.NotificationForm;

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
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object value = model.getValueAt(selectedRow, 0);
        if( value == null ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy ID của hàng đã chọn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int id;
        try {
            id = Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa hàng này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if( confirm == JOptionPane.YES_OPTION ) {
            model.removeRow(selectedRow);
            if( panel instanceof NotificationForm ) {
                //deleteButton.deleteRowInTable("notification", id);
            }// else if( panel instanceof Resident ) {
//                deleteButton.deleteRowInTable("residents", id);
//            } else if( panel instanceof Employee ) {
//                deleteButton.deleteRowInTable("employees", id);
//            }
        }
    }
}
