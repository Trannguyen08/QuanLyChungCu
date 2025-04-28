package main.java.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle;


import main.java.utc2.apartmentManage.service.managerService.apartmentIMP;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class editImageHandle {
    private JTable table;
    private JLabel label1, label2, label3, label4;
    private JPanel panel1, panel2, panel3, panel4;
    private JButton addButton, editButton, deleteButton;
    private final apartmentIMP Apartmentservice = new apartmentIMP();
    private List<String> imgList = new ArrayList<>();
    private JLabel selectedLabel = null; 
    private String selectedImagePath = null;
    private int selectedRow;
    private int id;


    public editImageHandle(JButton addButton, JButton deleteButton, JButton editButton,
                            JLabel label1, JLabel label2, JLabel label3, JLabel label4, 
                            JPanel panel1, JPanel panel2, JPanel panel3, JPanel panel4, JTable table) {
        this.addButton = addButton;
        this.deleteButton = deleteButton;
        this.editButton = editButton;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
        this.label4 = label4;
        this.panel1 = panel1;
        this.panel2 = panel2;
        this.panel3 = panel3;
        this.panel4 = panel4;
        
        this.table = table;
        selectedRow = table.getSelectedRow();
        id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());

        
        this.addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBtnClick();
            }
        });
        
        this.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBtnClick();
            }
        });
        
        
        addClickListener(panel1);
        addClickListener(panel2);
        addClickListener(panel3);
        addClickListener(panel4);


        loadImage();
    }

    public void loadImage() {
        List<String> imagePaths = Apartmentservice.getAllImageByID(id);
        if ( !imagePaths.isEmpty() ) {
            // Resize ảnh trước khi hiển thị
            setImageToLabel(label1, imagePaths, 0);
            setImageToLabel(label2, imagePaths, 1);
            setImageToLabel(label3, imagePaths, 2);
            setImageToLabel(label4, imagePaths, 3);
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
//        boolean check = Apartmentservice.selectImages(imgList, maxImgToAdd);
//        if( !check ) {
//            return;
//        }
        
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
        selectedRow = table.getSelectedRow();
        int apartmentID = Integer.parseInt(table.getValueAt(selectedRow, 0).toString()); 
        boolean checkSave = Apartmentservice.saveApartmentImages(apartmentID, imgList);
    }
    
    private void setImageToLabel(JLabel label, List<String> imagePaths, int index) {
        if (index < imagePaths.size()) {
            ImageIcon icon = new ImageIcon(imagePaths.get(index));
            Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
            label.setName(imagePaths.get(index)); 
        }
    }

    private void addClickListener(JPanel panel) {
        panel.setOpaque(true); 
        panel.setBackground(Color.LIGHT_GRAY); 
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("Panel được click!"); 

                Component[] components = panel.getComponents();
                for (Component comp : components) {
                    if (comp instanceof JLabel) {
                        JLabel label = (JLabel) comp;
                        if (label.getIcon() != null) {
                            selectedLabel = label;
                            selectedImagePath = label.getName(); 
                            System.out.println("Đã chọn ảnh: " + selectedImagePath);
                            return;
                        }
                    }
                }
            }
        });
    }


    public void deleteBtnClick() {
        if (selectedLabel != null && selectedLabel.getIcon() != null) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa ảnh này không?", 
                                                        "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                selectedLabel.setIcon(null); 
                imgList.remove(selectedImagePath); 
                Apartmentservice.deleteImage(id, selectedImagePath);
                selectedLabel = null; 
                selectedImagePath = null; 
                System.out.println("Ảnh đã bị xóa: " + selectedImagePath);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một ảnh để xóa!", 
                                          "Thông báo", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    
}
