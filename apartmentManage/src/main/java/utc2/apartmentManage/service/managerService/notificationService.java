package main.java.utc2.apartmentManage.service.managerService;

import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2.apartmentManage.repository.ManagerRepository.notifiRepository;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import main.java.utc2.apartmentManage.model.Notification;
import main.java.utc2.apartmentManage.util.ScannerUtil;

public class notificationService {
    private final notifiRepository notifiRepository = new notifiRepository();
    
    public int getNewID() {
        return notifiRepository.getIDMinNotExist();
    }
    
    public String getTodayDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
    
    public boolean updateNotification(Notification notification){
        return notifiRepository.updateNotification(notification);
    }
    
    public boolean addNotification(Notification notification){
        return notifiRepository.addNotification(notification);
    }
    
    public boolean deleteNotification(int id){
        return notifiRepository.deleteNotification(id);
    }
    
    
    public Integer getNotificationID(JTable table){
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object value = model.getValueAt(selectedRow, 0);
        if (value == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy ID của hàng đã chọn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void setupNotificationTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

        List<Notification> notificationList = notifiRepository.getAllNotification();

        for (Notification notification : notificationList) {
            model.addRow(new Object[]{
                    notification.getID(),
                    notification.getType(),
                    notification.getTitle(),
                    notification.getMess(),
                    notification.getSentDate()
                    
            });
        }
        
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }


    public boolean confirmDelete(String s) {
        return ScannerUtil.comfirmWindow(s);
    }

    // check select từ table
    public boolean errorNotifiaction(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean loadSelectedRowData(JTable table, JTextField notiTitle, JTextField notification, JComboBox<String> notiType, JTextField notiID, JTextField ownerID) {
        int selectedRow = table.getSelectedRow();
        errorNotifiaction(table);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        notiID.setText(model.getValueAt(selectedRow, 0).toString());
        ownerID.setText(model.getValueAt(selectedRow, 1).toString());
        notiTitle.setText(model.getValueAt(selectedRow, 2).toString());
        notification.setText(model.getValueAt(selectedRow, 3).toString());
        notiType.setSelectedItem(model.getValueAt(selectedRow, 4).toString());

        return true;
    }
    public boolean validateData(JTextField notiTitle, JTextField notification, JComboBox<String> notiType, JTextField notiID, JTextField ownerID) {
        if (notiTitle.getText().trim().isEmpty() || notification.getText().trim().isEmpty() ||
                notiType.getSelectedItem() == null || notiID.getText().trim().isEmpty() || ownerID.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean updateNotification(JTable table, JTextField notiTitle, JTextField notification, JComboBox<String> notiType, JTextField notiID, JTextField ownerID) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.setValueAt(notiID.getText(), selectedRow, 0);
        model.setValueAt(ownerID.getText(), selectedRow, 1);
        model.setValueAt(notiTitle.getText(), selectedRow, 2);
        model.setValueAt(notification.getText(), selectedRow, 3);
        model.setValueAt(notiType.getSelectedItem().toString(), selectedRow, 4);

        return true;
    }

}
