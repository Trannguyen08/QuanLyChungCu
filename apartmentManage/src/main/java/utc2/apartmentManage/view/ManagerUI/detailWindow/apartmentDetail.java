package main.java.utc2.apartmentManage.view.ManagerUI.detailWindow;

import javax.swing.JFrame;
import javax.swing.JTable;
import main.java.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle.detailButtonHandle;


public class apartmentDetail extends javax.swing.JFrame {

   
    public apartmentDetail(JTable table) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Chi tiết chung cư");
        new detailButtonHandle(index, area, buyPrice, imgLabel, interior, rentPrice, room, roomwc, status, table);
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
        roomwc = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 204, 255));

        index.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        index.setText("Vị trí: ");

        room.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        room.setText("Số phòng ngủ:");

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

        roomwc.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        roomwc.setText("Số phòng wc:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(room)
                    .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(index)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(interior)
                            .addComponent(status)
                            .addComponent(area))
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rentPrice)
                            .addComponent(roomwc)
                            .addComponent(buyPrice))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(imgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(index)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(interior)
                    .addComponent(roomwc))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status)
                    .addComponent(rentPrice))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(area)
                    .addComponent(buyPrice))
                .addGap(18, 18, 18)
                .addComponent(room)
                .addContainerGap(11, Short.MAX_VALUE))
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
    private javax.swing.JLabel roomwc;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
