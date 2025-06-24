package java.utc2.apartmentManage.view.manager.detailWindow;

import utc2.apartmentManage.controller.manager.apartment.detailButtonHandle;

import javax.swing.*;

public class apartmentDetail extends JFrame {

   
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

        index = new JLabel();
        room = new JLabel();
        interior = new JLabel();
        status = new JLabel();
        area = new JLabel();
        rentPrice = new JLabel();
        buyPrice = new JLabel();
        imgLabel = new JLabel();
        roomwc = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(room)
                    .addComponent(index)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(interior)
                            .addComponent(status)
                            .addComponent(area))
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(rentPrice)
                            .addComponent(roomwc)
                            .addComponent(buyPrice)))
                    .addComponent(imgLabel, GroupLayout.PREFERRED_SIZE, 438, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(imgLabel, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(index)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(interior)
                    .addComponent(roomwc))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(status)
                    .addComponent(rentPrice))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(area)
                    .addComponent(buyPrice))
                .addGap(18, 18, 18)
                .addComponent(room)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel area;
    private JLabel buyPrice;
    private JLabel imgLabel;
    private JLabel index;
    private JLabel interior;
    private JLabel rentPrice;
    private JLabel room;
    private JLabel roomwc;
    private JLabel status;
    // End of variables declaration//GEN-END:variables
}
