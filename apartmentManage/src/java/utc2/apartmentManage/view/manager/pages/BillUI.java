package java.utc2.apartmentManage.view.manager.pages;

import utc2.apartmentManage.controller.manager.bill.billHandle;

import javax.swing.*;

public class BillUI extends JPanel {

    public BillUI(JPanel main) {
        initComponents();
        new billHandle(hdcd, hdql, main);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hdcd = new JPanel();
        jLabel2 = new javax.swing.JLabel();
        hdql = new JPanel();
        jLabel1 = new javax.swing.JLabel();

        hdcd.setBackground(new java.awt.Color(33, 100, 126));
        hdcd.setForeground(new java.awt.Color(255, 255, 255));
        hdcd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("QUẢN LÝ HÓA ĐƠN CƯ DÂN");

        javax.swing.GroupLayout hdcdLayout = new javax.swing.GroupLayout(hdcd);
        hdcd.setLayout(hdcdLayout);
        hdcdLayout.setHorizontalGroup(
            hdcdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hdcdLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel2)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        hdcdLayout.setVerticalGroup(
            hdcdLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hdcdLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(58, 58, 58))
        );

        hdql.setBackground(new java.awt.Color(33, 100, 126));
        hdql.setForeground(new java.awt.Color(255, 255, 255));
        hdql.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ CÁC KHOẢN CHI TIÊU");

        javax.swing.GroupLayout hdqlLayout = new javax.swing.GroupLayout(hdql);
        hdql.setLayout(hdqlLayout);
        hdqlLayout.setHorizontalGroup(
            hdqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(hdqlLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        hdqlLayout.setVerticalGroup(
            hdqlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, hdqlLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(153, Short.MAX_VALUE)
                .addComponent(hdcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(hdql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(250, 250, 250)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hdql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hdcd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(263, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel hdcd;
    private JPanel hdql;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
