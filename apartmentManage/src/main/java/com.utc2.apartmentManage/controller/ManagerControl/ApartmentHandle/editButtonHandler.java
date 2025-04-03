package main.java.com.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.com.utc2.apartmentManage.model.Apartment;
import main.java.com.utc2.apartmentManage.service.managerService.apartmentService;
import main.java.com.utc2.apartmentManage.view.ManagerUI.editWindow.editImages;

public class editButtonHandler {
    private JButton editBtn, editImg;
    private JComboBox<String> apartmentIndex, building, floor, roomNum, status;
    private JTextField area, buyPrice, rentPrice;
    private JTable table;
    private JFrame edit;
    private final apartmentService apartmentservice = new apartmentService();
    
    public editButtonHandler(JButton editImg, JButton addBtn, JComboBox<String> apartmentIndex, JTextField area,
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
        this.editImg = editImg;

        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
                new editImages(table).addImage();
            }
        });

        this.editImg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });
    }
    
    public void loadSelectedRowData() {
        boolean check = apartmentservice.loadSelectedRowData(table, apartmentIndex, floor, building, roomNum, status, area, rentPrice, buyPrice);
    }

    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();

        if (!apartmentservice.notification(table)) {
            return;
        }

        if (!apartmentservice.validateData(apartmentIndex, floor, building, roomNum, status, area, rentPrice, buyPrice)) {
            return;
        }

        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        Apartment apartment = getApartmentFromForm(id);

        if (!apartmentservice.updateApartment(apartment)) {
            JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        updateTableRow(selectedRow, apartment);
        JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        edit.setVisible(false);
    }

    private Apartment getApartmentFromForm(int id) {
        return new Apartment(
            id,
            Integer.parseInt(apartmentIndex.getSelectedItem().toString()),
            Integer.parseInt(floor.getSelectedItem().toString()),
            building.getSelectedItem().toString(),
            Integer.parseInt(roomNum.getSelectedItem().toString()),
            status.getSelectedItem().toString(),
            Double.parseDouble(area.getText().trim()),
            Double.parseDouble(rentPrice.getText().trim()),
            Long.parseLong(buyPrice.getText().trim())
        );
    }

    private void updateTableRow(int rowIndex, Apartment apartment) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setValueAt(apartment.getIndex(), rowIndex, 1);
        model.setValueAt(apartment.getFloor(), rowIndex, 2);
        model.setValueAt(apartment.getBuilding(), rowIndex, 3);
        model.setValueAt(apartment.getNumRooms(), rowIndex, 4);
        model.setValueAt(apartment.getStatus(), rowIndex, 5);
        model.setValueAt(apartment.getArea(), rowIndex, 6);
        model.setValueAt(apartment.getRentPrice(), rowIndex, 7);
        model.setValueAt(apartment.getPurchasePrice(), rowIndex, 8);
    }

    private void loadImage() {
        new editImages(table).setVisible(true);
    }

}

