package Controller.ManagerControl.ApartmentHandle;

import Util.ScannerUtil;

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
        // Lấy dữ liệu từ các ô nhập
        String owner = ownerID.getText().trim();
        Object apartment = apartmentIndex.getSelectedItem();
        Object floorNum = floor.getSelectedItem();
        Object buildingNum = building.getSelectedItem();
        Object roomNumber = roomNum.getSelectedItem();
        Object statusVal = status.getSelectedItem();
        String areaVal = area.getText().trim();
        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        // Kiểm tra dữ liệu trống
        if (apartment == null || floorNum == null ||
                buildingNum == null || roomNumber == null || statusVal == null ||
                areaVal.isEmpty() || rent.isEmpty() || buy.isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "Vui lòng điền đầy đủ thông tin trước khi thêm!",
                    "Lỗi nhập liệu",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if( owner != null && !ScannerUtil.validateInteger(owner, "ID Chủ hộ") ||
            !ScannerUtil.validateDouble(areaVal, "Diện tích") ||
            !ScannerUtil.validateDouble(rent, "Giá thuê") ||
            !ScannerUtil.validateDouble(buy, "Giá mua") ||
            !ScannerUtil.spaceDouble(areaVal, 50, 70,"Diện tích") ||
            !ScannerUtil.spaceDouble(rent, 5000000, 10000000, "Giá thuê") ||
            !ScannerUtil.spaceDouble(buy, 2.5e9, 7e9, "Giá mua") ) {

            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        // Kiểm tra ID chủ hộ đã tồn tại chưa
        for (int i = 0; i < model.getRowCount(); i++) {
            String existingOwner = model.getValueAt(i, 1).toString();
            if (existingOwner.equals(owner)) {
                JOptionPane.showMessageDialog(null,
                        "ID Chủ hộ đã tồn tại!",
                        "Lỗi trùng ID",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Kiểm tra trùng ApartmentIndex, Floor, Building
        for (int i = 0; i < model.getRowCount(); i++) {
            Object existingApartment = model.getValueAt(i, 2);
            Object existingFloor = model.getValueAt(i, 3);
            Object existingBuilding = model.getValueAt(i, 4);

            if (existingApartment.equals(apartment) &&
                    existingFloor.equals(floorNum) &&
                    existingBuilding.equals(buildingNum)) {
                JOptionPane.showMessageDialog(null,
                        "Căn hộ này đã tồn tại trong cùng tầng và tòa nhà!",
                        "Lỗi trùng lặp",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Thêm vào bảng nếu hợp lệ
        model.addRow(new Object[]{"2",owner, apartment, floorNum, buildingNum, roomNumber, statusVal, areaVal, rent, buy});

        add.setVisible(false);
        JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }



}
