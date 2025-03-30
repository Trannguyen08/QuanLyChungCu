package controller.ManagerControl.ApartmentHandle;

import model.Apartment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.managerService.apartmentService;

public class addButtonHandler {
    private JButton addBtn;
    private JComboBox<String> apartmentIndex, building, floor, roomNum, status;
    private JTextField area, buyPrice, rentPrice;
    private JTable table;
    private JFrame add;
    private final apartmentService apartmentService = new apartmentService();
    
    public addButtonHandler(JButton addBtn, JComboBox<String> apartmentIndex, JTextField area,
                       JComboBox<String> building, JTextField buyPrice, JComboBox<String> floor, 
                       JTextField rentPrice, JComboBox<String> roomNum, JComboBox<String> status, JTable table, JFrame add) {
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
        Object aIndex = apartmentIndex.getSelectedItem();
        Object floorNum = floor.getSelectedItem();
        Object buildingNum = building.getSelectedItem();
        Object roomNumber = roomNum.getSelectedItem();
        Object statusVal = status.getSelectedItem();
        String areaVal = area.getText().trim();
        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        boolean check = apartmentService.validateData(apartmentIndex, floor, building, roomNum, status, area, rentPrice, buyPrice );
        if( !check ) {
            return;
        }

        Apartment apartment = new Apartment(0, Integer.parseInt(aIndex.toString()), Integer.parseInt(floorNum.toString()),
                                            buildingNum.toString(), Integer.parseInt(roomNumber.toString()), statusVal.toString(),
                                            Double.parseDouble(areaVal), Double.parseDouble(rent), Long.parseLong(buy));

        // check trùng căn hộ
        if (apartmentService.isDuplicate(apartment, table)) {
            JOptionPane.showMessageDialog(null,
                    "Căn hộ này đã tồn tại trong cùng tầng và tòa nhà!",
                    "Lỗi trùng lặp",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // thêm vào database và table
        boolean isAddedComplete = apartmentService.addApartment(apartment);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        model.addRow(new Object[] {apartment.getId(), apartment.getIndex(), apartment.getFloor(),
                apartment.getBuilding(), apartment.getNumRooms(), apartment.getStatus(),
                apartment.getArea(), apartment.getRentPrice(), apartment.getPurchasePrice()});

        if( isAddedComplete ) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
