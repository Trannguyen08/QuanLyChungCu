package service.managerService;

import dao.managerDAO.NotificationDAO;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Notification;
import util.ScannerUtil;

import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class notificationService {
    private final NotificationDAO notificationDAO = new NotificationDAO();
    
    public boolean updateNotification(Notification notification){
        return notificationDAO.updateNotification(notification);
    }
    
    public boolean addNotification(Notification notification){
        return notificationDAO.addNotification(notification);
    }
    
    public boolean deleteNotification(int id){
        return notificationDAO.deleteNotification(id);
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

        List<Notification> notificationList = notificationDAO.getAllNotification();

        for (Notification notification : notificationList) {
            model.addRow(new Object[]{
                    notification.getID(),
                    notification.getOwnerID(),
                    notification.getTitle(),
                    notification.getMess(),
                    notification.getType()
            });
        }
        
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 15));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }


    public boolean confirmDelete() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa hàng này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
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
        if (selectedRow == -1) {
            return false;
        }
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

    public List<RowFilter<DefaultTableModel, Integer>> createFilters(JTextField notiID, JTextField ownerID, JTextField notiTitle, JComboBox<String> notiType) {
        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

        if (!notiID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + notiID.getText().trim(), 0));
        }
        if (!ownerID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + ownerID.getText().trim(), 1));
        }
        if (!notiTitle.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + notiTitle.getText().trim(), 2));
        }
        if (notiType.getSelectedItem() != null && !notiType.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + notiType.getSelectedItem().toString().trim(), 4));
        }

        return filters;
    }

}
