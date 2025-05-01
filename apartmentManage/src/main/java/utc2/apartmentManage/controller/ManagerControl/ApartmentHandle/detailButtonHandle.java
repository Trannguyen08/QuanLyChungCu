package main.java.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle;

import java.awt.Image;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import main.java.utc2.apartmentManage.model.Apartment;
import main.java.utc2.apartmentManage.service.managerService.apartmentIMP;

public class detailButtonHandle {
    private JLabel index, area, buyPrice, imgLabel, interior, rentPrice, room, status, wc;
    private JTable table;
    private apartmentIMP ap = new apartmentIMP();
    private List<String> list;
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    

    public detailButtonHandle(JLabel index, JLabel area, JLabel buyPrice, JLabel imgLabel, JLabel interior, 
                            JLabel rentPrice, JLabel room, JLabel wc, JLabel status, JTable table) {
        this.index = index;
        this.area = area;
        this.buyPrice = buyPrice;
        this.imgLabel = imgLabel;
        this.interior = interior;
        this.rentPrice = rentPrice;
        this.room = room;
        this.status = status;
        this.table = table;
        this.wc = wc;
        
        int id = ap.getApartmentId(table);
        Apartment apartment = ap.getObject(id);
        list = ap.getAllImageByID(id);
        
        
        displayImage();
        displayContent(apartment);
    }
    
    public void displayContent(Apartment apartment) {
        index.setText("Căn hộ số:  " + apartment.getIndex() + 
                        "    Tầng:  " + apartment.getFloor() + 
                        "    Tòa:  " + apartment.getBuilding());
        room.setText("Số phòng ngủ:  " + apartment.getNumRooms()); 
        wc.setText("Số phòng vệ sinh:  " + apartment.getNumWc());
        interior.setText("Nội thất:  " + apartment.getInterior());
        status.setText("Tình trạng:  " + apartment.getStatus());
        area.setText("Diện tích:  " + df.format(apartment.getArea()));
        rentPrice.setText("Giá thuê:  " + df.format(apartment.getRentPrice()));
        buyPrice.setText("Giá mua:  " + df.format(apartment.getPurchasePrice()));

    }
    
    public void displayImage() {
        ImageIcon imageIcon = new ImageIcon(list.get(0));

        Image img = imageIcon.getImage();

        int labelWidth = imgLabel.getWidth();
        int labelHeight = imgLabel.getHeight();

        int newWidth = labelWidth;
        int newHeight = (int) ((double) img.getHeight(null) / img.getWidth(null) * labelWidth);

        if (newHeight > labelHeight) {
            newHeight = labelHeight;
            newWidth = (int) ((double) img.getWidth(null) / img.getHeight(null) * labelHeight);
        }

        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        imgLabel.setIcon(new ImageIcon(scaledImg));
    }

    
    
    
    
    
    
}
