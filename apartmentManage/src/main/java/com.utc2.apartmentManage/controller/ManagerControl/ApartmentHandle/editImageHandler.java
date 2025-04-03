package main.java.com.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle;

import main.java.com.utc2.apartmentManage.service.managerService.apartmentService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class editImageHandler {
    private JTable table;
    private JLabel label1, label2, label3, label4;
    private JButton addButton, editButton, deleteButton;
    private final apartmentService apartmentservice = new apartmentService();
    private List<String> imgList = new ArrayList<>();

    public editImageHandler(JButton addButton, JButton deleteButton, JButton editButton,
                            JLabel label1, JLabel label2, JLabel label3, JLabel label4, JTable table) {
        this.addButton = addButton;
        this.deleteButton = deleteButton;
        this.editButton = editButton;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.table = table;
        
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBtnClick();
            }
        });

        loadImage();
    }

    public void loadImage() {
        int selectedRow = table.getSelectedRow();
        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        List<String> imagePaths = apartmentservice.getAllImageByID(id);
        if ( !imagePaths.isEmpty() ) {
            // Resize ảnh trước khi hiển thị
            setImageToLabel(label1, imagePaths, 0);
            setImageToLabel(label2, imagePaths, 1);
            setImageToLabel(label3, imagePaths, 2);
            setImageToLabel(label4, imagePaths, 3);
        }
    }

    private void setImageToLabel(JLabel label, List<String> imagePaths, int index) {
        if( index < imagePaths.size() ) {
            ImageIcon icon = new ImageIcon(imagePaths.get(index));
            Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
    }
    
    private void addBtnClick() {
        if( label4.getIcon() != null ) {
            JOptionPane.showMessageDialog(null, "Căn hộ đã có đủ ảnh",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int count = 0;
        if( label1.getIcon() != null ) count++;
        
        if( label2.getIcon() != null ) count++;

        if( label3.getIcon() != null ) count++;
        
        
        int maxImgToAdd = 4-count;
        boolean check = apartmentservice.selectImages(imgList, maxImgToAdd);
        if( !check ) {
            return;
        }
        
        int index = 0;
        if (label1.getIcon() == null ) {
            setImageToLabel(label1, imgList, index++);
        }
        if (label2.getIcon() == null ) {
            setImageToLabel(label2, imgList, index++);
        }
        if (label3.getIcon() == null ) {
            setImageToLabel(label3, imgList, index++);
        }
        if (label4.getIcon() == null ) {
            setImageToLabel(label4, imgList, index++);
        }
        
    }
    
    public void addImage() {
        int selectedRow = table.getSelectedRow();
        int apartmentID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString()); 
        boolean checkSave = apartmentservice.saveApartmentImages(apartmentID, imgList);
    }
}
