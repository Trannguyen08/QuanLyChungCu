package controller.ManagerControl.ApartmentHandle;

import model.Apartment;
import dao.managerDAO.ApartmentDAO;
import service.managerService.apartmentService;
import util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editButtonHandler {
    private JButton editBtn;
    private JComboBox<String> apartmentIndex, building, floor, roomNum, status;
    private JTextField area, buyPrice, rentPrice;
    private JTable table;
    private JFrame edit;
    private final apartmentService apartmentservice = new apartmentService();
    
    public editButtonHandler(JButton addBtn, JComboBox<String> apartmentIndex, JTextField area,
                       JComboBox<String> building, JTextField buyPrice, JComboBox<String> floor, 
                       JTextField rentPrice, JComboBox<String> roomNum, JComboBox<String> status, JTable table, JFrame edit) {
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
        boolean check = apartmentservice.loadSelectedRowData(table, apartmentIndex, floor, building, roomNum, status, area, rentPrice, buyPrice);
    }

    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        boolean error = apartmentservice.errorNotifiaction(table);
        if( !error ) {
            return;
        }

        Object apartment = apartmentIndex.getSelectedItem();
        Object floorNum = floor.getSelectedItem();
        Object buildingNum = building.getSelectedItem();
        Object roomNumber = roomNum.getSelectedItem();
        Object statusVal = status.getSelectedItem();
        String areaVal = area.getText().trim();
        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        boolean check = apartmentservice.validateData(table, apartmentIndex, floor, building, roomNum, status, area, rentPrice, buyPrice);
        if( !check ) {
            return;
        }

        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        model.setValueAt(apartmentIndex.getSelectedItem(), selectedRow, 1);
        model.setValueAt(floor.getSelectedItem(), selectedRow, 2);
        model.setValueAt(building.getSelectedItem(), selectedRow, 3);
        model.setValueAt(roomNum.getSelectedItem(), selectedRow, 4);
        model.setValueAt(status.getSelectedItem(), selectedRow, 5);
        model.setValueAt(area.getText(), selectedRow, 6);
        model.setValueAt(rentPrice.getText(), selectedRow, 7);
        model.setValueAt(buyPrice.getText(), selectedRow, 8);
        edit.setVisible(false);

        Apartment a = new Apartment(id, Integer.parseInt(apartmentIndex.getSelectedItem().toString()), Integer.parseInt(floor.getSelectedItem().toString()),
                building.getSelectedItem().toString(), Integer.parseInt(roomNum.getSelectedItem().toString()), status.getSelectedItem().toString(),
                Double.parseDouble(area.getText()), Double.parseDouble(rentPrice.getText()), Double.parseDouble(buyPrice.getText()));

        boolean isUpdatedComplete = apartmentservice.updateApartment(a);
        if( isUpdatedComplete ) {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

