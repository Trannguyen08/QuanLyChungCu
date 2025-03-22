package Controller.ManagerControl.ApartmentHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class addButtonHandler {
    private JButton addBtn;
    private JComboBox<String> apartmentIndex, building, floor, roomNum, status;
    private JTextField area, buyPrice, rentPrice, ownerID;
    private JTable table;
    private JFrame add;
    
    public addButtonHandler(JButton addBtn, JTextField ownerID, JComboBox<String> apartmentIndex, JTextField area,
                       JComboBox<String> building, JTextField buyPrice, JComboBox<String> floor, 
                       JTextField rentPrice, JComboBox<String> roomNum, JComboBox<String> status, JTable table, JFrame add) {
        this.ownerID = ownerID;
        this.addBtn = addBtn;
        this.apartmentIndex = apartmentIndex;
        this.area = area;
        this.building = building;
        this.buyPrice = buyPrice;
        this.floor = floor;
        this.rentPrice = rentPrice;
        this.roomNum = roomNum;
        this.status = status;
        this.table = table;
        this.add = add;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }
    private void addNewRow() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[]{
                null,
                ownerID.getText(),
                apartmentIndex.getSelectedItem(),
                floor.getSelectedItem(),
                building.getSelectedItem(),
                roomNum.getSelectedItem(),
                status.getSelectedItem(),
                area.getText(),
                rentPrice.getText(),
                buyPrice.getText(),
        });
        add.setVisible(false);
        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    
}
