package Controller.ManagerControl.NotificationHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editButtonHandler {
    private JButton editBtn;
    private JComboBox<String> notiType;
    private JTextField notiID, ownerID, notiTitle, notification;
    private JTable table;
    private JFrame edit;
    
    public editButtonHandler(JButton editBtn, JComboBox<String> notiType, JTextField notiID, JTextField ownerID, JTextField notiTitle, JTextField notification, JTable table, JFrame edit){
        this.editBtn =editBtn;
        this.notiType = notiType;
        this.notiID = notiID;
        this.ownerID = ownerID;
        this.notiTitle = notiTitle;
        this.notification = notification;
        this.table = table;
        this.edit = edit;
        
        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
    }
    
    public void loadSelectedRowData() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if( model == null ) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for( int col = 0 ; col < model.getColumnCount() ; col++ ) {
            if( model.getValueAt(selectedRow, col ) == null) {
                model.setValueAt("", selectedRow, col);
            }
        }
        notiID.setText(model.getValueAt(selectedRow, 1).toString());
        ownerID.setText(model.getValueAt(selectedRow, 2).toString());
        notiTitle.setText(model.getValueAt(selectedRow, 3).toString());
        notification.setText(model.getValueAt(selectedRow, 4).toString());
        notiType.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
    }
    
    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if( model == null ) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        model.setValueAt(notiID.getText(), selectedRow, 1);
        model.setValueAt(ownerID.getText(), selectedRow, 2);
        model.setValueAt(notiTitle.getText(), selectedRow, 3);
        model.setValueAt(notification.getText(), selectedRow, 4);
        model.setValueAt(notiType.getSelectedItem(), selectedRow, 5);
        edit.setVisible(false);
        JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}
