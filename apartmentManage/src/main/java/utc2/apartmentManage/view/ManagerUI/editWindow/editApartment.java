package main.java.utc2.apartmentManage.view.ManagerUI.editWindow;

import javax.swing.*;
import main.java.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle.editButtonHandle;

public class editApartment extends javax.swing.JFrame {
    public editApartment(JTable table) {
        initComponents();
        editButtonHandle edit = new editButtonHandle(interior, status, rentPrice, buyPrice, 
                                                        editBtn, table, this);
        edit.loadSelectedRowData();
        setLocationRelativeTo(null);
        setTitle("Edit Apartment");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        status = new javax.swing.JComboBox<>();
        rentPrice = new javax.swing.JTextField();
        buyPrice = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        interior = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(253, 253, 253));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel41.setText("Tình trạng");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel43.setText("Giá thuê");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel44.setText("Giá mua");

        editBtn.setBackground(new java.awt.Color(50, 65, 94));
        editBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Cập nhật");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        status.setBackground(new java.awt.Color(250, 250, 250));
        status.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thuê", "Đã bán", "Trống", "Bảo trì ", "Chờ duyệt" }));

        rentPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        buyPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel46.setText("Nội thất");

        interior.setBackground(new java.awt.Color(250, 250, 250));
        interior.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        interior.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trống", "Cơ bản", "Đầy đủ" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel44))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buyPrice)
                    .addComponent(rentPrice)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(interior, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(74, 74, 74))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(interior, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(rentPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(buyPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buyPrice;
    private javax.swing.JButton editBtn;
    private javax.swing.JComboBox<String> interior;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField rentPrice;
    private javax.swing.JComboBox<String> status;
    // End of variables declaration//GEN-END:variables
}
