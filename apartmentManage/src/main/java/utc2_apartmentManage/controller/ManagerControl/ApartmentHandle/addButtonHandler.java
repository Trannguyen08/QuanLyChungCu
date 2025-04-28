package main.java.utc2_apartmentManage.controller.ManagerControl.ApartmentHandle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2_apartmentManage.model.Apartment;
import main.java.utc2_apartmentManage.service.managerService.apartmentService;
import main.java.utc2_apartmentManage.util.ScannerUtil;



public class addButtonHandler {
    private JButton addBtn, addImage;
    private JLabel imglabel;
    private JComboBox<String> apartmentIndex, building, floor, roomNum, status;
    private JTextField area, buyPrice, rentPrice;
    private JTable table;
    private JFrame add;
    private final apartmentService apartmentService = new apartmentService();
    private List<String> selectedImageNames = new ArrayList<>();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public addButtonHandler(JButton addBtn, JButton addImage, JLabel imglabel, JComboBox<String> apartmentIndex, JTextField area,
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
        this.addImage = addImage;
        this.imglabel = imglabel;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewApartment();
            }
        });

        this.addImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectImages();
            }
        });

    }

    private void selectImages() {
        if( !apartmentService.selectImages(selectedImageNames, 4) ) {
            return;
        }
    }


    // Bước 2: Thêm căn hộ -> lấy ID -> thêm ảnh vào DB
    private void addNewApartment() {
        Object aIndex = apartmentIndex.getSelectedItem();
        Object floorNum = floor.getSelectedItem();
        Object buildingNum = building.getSelectedItem();
        Object roomNumber = roomNum.getSelectedItem();
        Object statusVal = status.getSelectedItem();
        String areaVal = area.getText().trim();
        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        boolean check = apartmentService.validateData(apartmentIndex, floor, building, roomNum, 
                                                    status, area, rentPrice, buyPrice );
        if( !check ) {
            return;
        }

        int id = apartmentService.getIDMin();
        Apartment apartment = new Apartment(id, Integer.parseInt(aIndex.toString()), Integer.parseInt(floorNum.toString()),
                                buildingNum.toString(), Integer.parseInt(roomNumber.toString()), statusVal.toString(),
                                ScannerUtil.replaceDouble(area), ScannerUtil.replaceDouble(rentPrice), ScannerUtil.replaceDouble(buyPrice));

        if (apartmentService.isDuplicate(apartment, table)) {
            JOptionPane.showMessageDialog(null, "Căn hộ này đã tồn tại trong cùng tầng và tòa nhà!", "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Thêm căn hộ vào database
        boolean isAddedComplete = apartmentService.addApartment(apartment);
        if (!isAddedComplete) {
            JOptionPane.showMessageDialog(null, "Thêm căn hộ thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cập nhật bảng giao diện
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Apartment a = apartmentService.getLastRow();
        model.addRow(new Object[] {a.getId(), a.getIndex(), a.getFloor(), a.getBuilding(), a.getNumRooms(), 
                                    a.getStatus(),df.format(a.getArea()), df.format(a.getRentPrice()), 
                                    df.format(a.getPurchasePrice())});

        // Thêm ảnh vào DB (sau khi thêm căn hộ)
        boolean isImageSaved = apartmentService.saveApartmentImages(a.getId(), selectedImageNames);

        if (isImageSaved) {
            JOptionPane.showMessageDialog(null, "Thêm căn hộ và lưu ảnh thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Căn hộ đã được thêm nhưng lưu ảnh thất bại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
        }

        add.setVisible(false);
    }


}
