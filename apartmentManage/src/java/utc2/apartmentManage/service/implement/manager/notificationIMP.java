package java.utc2.apartmentManage.service.implement.manager;

import com.toedter.calendar.JDateChooser;
import utc2.apartmentManage.model.Notification;
import utc2.apartmentManage.repository.manager.notificationRepository;
import utc2.apartmentManage.service.interfaces.*;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class notificationIMP implements ISQL<Notification>, ITable<Notification>, IValidate, INotification {
    private final notificationRepository noticationRepo = new notificationRepository();
    
    @Override
    public boolean add(Notification object) {
        return noticationRepo.addNoti(object);
    }

    @Override
    public boolean update(Notification object) {
        return noticationRepo.updateNoti(object);
    }

    @Override
    public boolean delete(int id) {
        return noticationRepo.deleteNoti(id);
    }

    @Override
    public int getNewID() {
        return noticationRepo.getIDMinNotExist();
    }

    @Override
    public boolean isExist(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean isDuplicate(Notification object) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Notification getObject(int id) {
        return noticationRepo.getNotiByID(id);
    }

    @Override
    public void setUpTable(JTable table) {
        List<Notification> list = noticationRepo.getAllNotifications();
        addData(table, list);
        setFont(table);
    }

    @Override
    public void addData(JTable table, List<Notification> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); 

        for (Notification notification : list) {
            model.addRow(new Object[]{
                    notification.getID(),
                    notification.getRecipant(),
                    notification.getType(),
                    notification.getTitle(),
                    notification.getMess(),
                    ScannerUtil.formatDate(notification.getSentDate())
            });
        } 
    }

    @Override
    public void setFont(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            if (i == 3 || i == 4) {
                table.getColumnModel().getColumn(i).setCellRenderer(new MultiLineCellRenderer());
            } else {
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        ((DefaultTableCellRenderer) table.getTableHeader()
                                    .getDefaultRenderer()).
                                    setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public boolean isSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean confirmDelete(String s) {
        return ScannerUtil.comfirmWindow(s);
    }
    
    @Override
    public boolean loadSelectedRowData(JTable table, JTextField title, JTextArea mess, 
                                    JComboBox<String> type) {
        
        boolean error = isSelectedRow(table);
        if( !error ) {
            return false;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        title.setText(model.getValueAt(selectedRow, 3).toString());
        type.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        mess.setText(model.getValueAt(selectedRow, 4).toString());
        
        return true;
    }
    
    @Override
    public boolean filterNotification(JTable table, Notification noti) {
        List<Notification> list = noticationRepo.filterNotifications(noti);
        if( list.isEmpty() ){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            table.setRowSorter(null);
            model.setRowCount(0); 
            
            return false;
        }
        addData(table, list);
        return true;
    }

    @Override
    public boolean addValidate(Object... obj) {
        JTextField notiTitle = (JTextField) obj[0];
        JTextArea mess = (JTextArea) obj[1];

        if (notiTitle.getText().trim().isEmpty() || mess.getText().trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean editValidate(Object... obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean searchValidate(Object... obj) {
        JDateChooser sendDateChooser = (JDateChooser) obj[0];

        if (sendDateChooser == null) {
            return true;
        }

        try {
            Date selectedDate = sendDateChooser.getDate();

            if (sendDateChooser.getDateEditor().getUiComponent() instanceof JTextField) {
                String text = ((JTextField) sendDateChooser.getDateEditor().getUiComponent()).getText();
                if (!text.trim().isEmpty() && selectedDate == null) {
                    JOptionPane.showMessageDialog(null, "Ngày gửi không hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ngày gửi không hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    
}
