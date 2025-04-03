package main.java.com.utc2.apartmentManage.view.ManagerUI.editWindow;

import javax.swing.*;
import main.java.com.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle.editButtonHandler;

public class editApartment extends javax.swing.JFrame {
    public editApartment(JTable table) {
        initComponents();
        editButtonHandler edit = new editButtonHandler(editImg, editBtn, apartmentIndex, area, building, buyPrice, floor,
                                                        rentPrice, roomNum, status, table, this);
        edit.loadSelectedRowData();
        setLocationRelativeTo(null);
        setTitle("Edit Apartment");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        apartmentIndex = new javax.swing.JComboBox<>();
        building = new javax.swing.JComboBox<>();
        floor = new javax.swing.JComboBox<>();
        roomNum = new javax.swing.JComboBox<>();
        status = new javax.swing.JComboBox<>();
        area = new javax.swing.JTextField();
        rentPrice = new javax.swing.JTextField();
        buyPrice = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        editImg = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(253, 253, 253));

        jLabel38.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel38.setText("Tòa");

        jLabel39.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel39.setText("Tầng");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel40.setText("Số phòng ");

        jLabel41.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel41.setText("Tình trạng");

        jLabel42.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel42.setText("Diện tích (m^2)");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel43.setText("Giá thuê");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel44.setText("Giá mua");

        editBtn.setBackground(new java.awt.Color(50, 65, 94));
        editBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Cập nhật");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        apartmentIndex.setBackground(new java.awt.Color(250, 250, 250));
        apartmentIndex.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        apartmentIndex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        building.setBackground(new java.awt.Color(250, 250, 250));
        building.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        building.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B", "C", "D", " " }));

        floor.setBackground(new java.awt.Color(250, 250, 250));
        floor.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        floor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        roomNum.setBackground(new java.awt.Color(250, 250, 250));
        roomNum.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        roomNum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        status.setBackground(new java.awt.Color(250, 250, 250));
        status.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thuê", "Đã bán", "Trống", "Bảo trì ", "Chờ duyệt" }));

        area.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        rentPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        buyPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel45.setText("Số căn hộ");

        editImg.setBackground(new java.awt.Color(249, 249, 249));
        editImg.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        editImg.setText("Sửa ảnh");
        editImg.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel40))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel44)
                            .addComponent(editImg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(41, 41, 41)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buyPrice)
                            .addComponent(rentPrice)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(roomNum, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(floor, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(building, javax.swing.GroupLayout.Alignment.LEADING, 0, 1, Short.MAX_VALUE)
                                    .addComponent(apartmentIndex, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(39, 39, 39))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 76, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(apartmentIndex, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(building, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(floor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roomNum, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel41))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(area, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rentPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(editImg, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> apartmentIndex;
    private javax.swing.JTextField area;
    private javax.swing.JComboBox<String> building;
    private javax.swing.JTextField buyPrice;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton editImg;
    private javax.swing.JComboBox<String> floor;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField rentPrice;
    private javax.swing.JComboBox<String> roomNum;
    private javax.swing.JComboBox<String> status;
    // End of variables declaration//GEN-END:variables
}
