package main.java.utc2.apartmentManage.view.ManagerUI.detailWindow;

import javax.swing.JFrame;
import javax.swing.JTable;
import main.java.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle.detailButtonHandle;


public class apartmentDetail extends javax.swing.JFrame {

   
    public apartmentDetail(JTable table) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Chi tiết chung cư");
        new detailButtonHandle(index, area, buyPrice, imgLabel, interior, rentPrice, room, status, table);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        index = new javax.swing.JLabel();
        room = new javax.swing.JLabel();
        interior = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        area = new javax.swing.JLabel();
        rentPrice = new javax.swing.JLabel();
        buyPrice = new javax.swing.JLabel();
        imgLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        index.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        index.setText("Vị trí: ");

        room.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        room.setText("Số phòng:");

        interior.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        interior.setText("Nội thất");

        status.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status.setText("Tình trạng:");

        area.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        area.setText("Diện tích");

        rentPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        rentPrice.setText("Giá thuê:");

        buyPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        buyPrice.setText("Giá mua:");

        imgLabel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(room)
                    .addComponent(index)
                    .addComponent(interior)
                    .addComponent(status)
                    .addComponent(area)
                    .addComponent(rentPrice)
                    .addComponent(buyPrice))
                .addContainerGap(229, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(index)
                        .addGap(18, 18, 18)
                        .addComponent(room)
                        .addGap(18, 18, 18)
                        .addComponent(interior)
                        .addGap(18, 18, 18)
                        .addComponent(status)
                        .addGap(18, 18, 18)
                        .addComponent(area)
                        .addGap(18, 18, 18)
                        .addComponent(rentPrice)
                        .addGap(18, 18, 18)
                        .addComponent(buyPrice))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel area;
    private javax.swing.JLabel buyPrice;
    private javax.swing.JLabel imgLabel;
    private javax.swing.JLabel index;
    private javax.swing.JLabel interior;
    private javax.swing.JLabel rentPrice;
    private javax.swing.JLabel room;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
