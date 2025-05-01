package main.java.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle;

import main.java.utc2.apartmentManage.model.Apartment;
import main.java.utc2.apartmentManage.service.managerService.apartmentIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;



public class addButtonHandle {
    private JButton addBtn, addImage;
    private JComboBox<String> apartmentIndex, building, floor, roomNum, status, wc, interior;
    private JTextField area, buyPrice, rentPrice;
    private JTable table;
    private JFrame add;
    private JLabel imglabel;
    private final apartmentIMP ApartmentIMP = new apartmentIMP();
    private List<String> selectedImageNames = new ArrayList<>();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public addButtonHandle(JButton addBtn, JButton addImage, JComboBox<String> apartmentIndex,
                            JTextField area, JComboBox<String> building, JTextField buyPrice,
                            JComboBox<String> floor, JComboBox<String> interior, JTextField rentPrice,
                            JComboBox<String> roomNum, JComboBox<String> wc,
                            JComboBox<String> status, JTable table, JFrame add, JLabel imglabel) {
        
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
        this.wc = wc;
        this.interior = interior;
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
        if( !ApartmentIMP.selectImageAndSetToLabel(selectedImageNames, imglabel) ) {
            return;
        }
    }


    private void addNewApartment() {
        if (imglabel.getIcon() == null) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng chọn một ảnh cho căn hộ!",
                    "Thiếu ảnh", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int aIndex = Integer.parseInt(apartmentIndex.getSelectedItem().toString());
        int floorNum = Integer.parseInt(floor.getSelectedItem().toString());
        String buildingNum = building.getSelectedItem().toString();
        int roomNumber = Integer.parseInt(roomNum.getSelectedItem().toString());
        String statusVal = status.getSelectedItem().toString();
        int numwc = Integer.parseInt(wc.getSelectedItem().toString());
        String interiorVal = interior.getSelectedItem().toString();

        boolean check = ApartmentIMP.addValidate(area, rentPrice, buyPrice);
        if( !check ) {
            return;
        }

        int id = ApartmentIMP.getNewID();
        Apartment apartment = new Apartment(id, aIndex, floorNum,
                                buildingNum, roomNumber,
                                numwc, interiorVal,statusVal,
                                ScannerUtil.replaceDouble(area),
                                ScannerUtil.replaceDouble(rentPrice), 
                                ScannerUtil.replaceDouble(buyPrice));

        if (ApartmentIMP.isDuplicate(apartment)) {
            JOptionPane.showMessageDialog(null,
                    "Căn hộ này đã tồn tại trong cùng tầng và tòa nhà!",
                    "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Thêm căn hộ vào database
        boolean isAddedComplete = ApartmentIMP.add(apartment);
        if (!isAddedComplete) {
            JOptionPane.showMessageDialog(null,
                    "Thêm căn hộ thất bại.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Cập nhật bảng giao diện
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(new Object[] {apartment.getId(), apartment.getIndex(), apartment.getFloor(),
                                    apartment.getBuilding(), apartment.getInterior(), df.format(apartment.getArea()),
                                    apartment.getStatus(), df.format(apartment.getRentPrice()),
                                    df.format(apartment.getPurchasePrice())});

        // Thêm ảnh vào DB (sau khi thêm căn hộ)
        boolean isImageSaved = ApartmentIMP.saveApartmentImages(apartment.getId(), selectedImageNames);

        if (isImageSaved) {
            JOptionPane.showMessageDialog(null,
                    "Thêm căn hộ và lưu ảnh thành công!",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(null,
                    "Căn hộ đã được thêm nhưng lưu ảnh thất bại!",
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE
            );
        }

        add.setVisible(false);
    }


}
