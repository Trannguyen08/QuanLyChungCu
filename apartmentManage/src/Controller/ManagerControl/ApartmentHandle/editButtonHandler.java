package Controller.ManagerControl.ApartmentHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editButtonHandler {
    private JButton editBtn;
    private JComboBox<String> apartmentIndex, building, floor, roomNum, status;
    private JTextField area, buyPrice, rentPrice, ownerID;
    private JTable table;
    private JFrame edit;
    
    public editButtonHandler(JButton addBtn, JTextField ownerID, JComboBox<String> apartmentIndex, JTextField area,
                       JComboBox<String> building, JTextField buyPrice, JComboBox<String> floor, 
                       JTextField rentPrice, JComboBox<String> roomNum, JComboBox<String> status, JTable table, JFrame edit) {
        this.ownerID = ownerID;
        this.editBtn = addBtn;
        this.apartmentIndex = apartmentIndex;
        this.area = area;
        this.building = building;
        this.buyPrice = buyPrice;
        this.floor = floor;
        this.rentPrice = rentPrice;
        this.roomNum = roomNum;
        this.status = status;
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
        ownerID.setText(model.getValueAt(selectedRow, 1).toString());
        apartmentIndex.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        floor.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        building.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
        roomNum.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
        status.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
        area.setText(model.getValueAt(selectedRow, 7).toString());
        rentPrice.setText(model.getValueAt(selectedRow, 8).toString());
        buyPrice.setText(model.getValueAt(selectedRow, 9).toString());
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
        model.setValueAt(ownerID.getText(), selectedRow, 1);
        model.setValueAt(apartmentIndex.getSelectedItem(), selectedRow, 2);
        model.setValueAt(floor.getSelectedItem(), selectedRow, 3);
        model.setValueAt(building.getSelectedItem(), selectedRow, 4);
        model.setValueAt(roomNum.getSelectedItem(), selectedRow, 5);
        model.setValueAt(status.getSelectedItem(), selectedRow, 6);
        model.setValueAt(area.getText(), selectedRow, 7);
        model.setValueAt(buyPrice.getText(), selectedRow, 8);
        model.setValueAt(rentPrice.getText(), selectedRow, 9);
        edit.setVisible(false);
        JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}

