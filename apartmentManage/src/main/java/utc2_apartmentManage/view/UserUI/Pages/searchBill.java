
package main.java.utc2_apartmentManage.view.UserUI.Pages;

import javax.swing.*;
import main.java.utc2_apartmentManage.controller.UserControl.searchIconHandler;

/**
 *
 * @author nghia
 */
public class searchBill extends javax.swing.JFrame {

    public searchBill(JTable table) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Tìm kiếm hợp đồng");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchIconHandler search = new searchIconHandler(billId, apartmentId, totalAmount, searchBtn,
                                                    billDate, dueDate, status, table, this, to_totalAmount);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        billId = new javax.swing.JTextField();
        apartmentId = new javax.swing.JTextField();
        billDate = new com.toedter.calendar.JDateChooser();
        dueDate = new com.toedter.calendar.JDateChooser();
        totalAmount = new javax.swing.JTextField();
        status = new javax.swing.JComboBox<>();
        to_totalAmount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText("ID_Hóa đơn");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText("ID_Căn hộ");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText("Ngày lập");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText("Ngày đến hạn");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText("Tổng tiền");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel6.setText("Trạng thái");

        totalAmount.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        totalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAmountActionPerformed(evt);
            }
        });

        status.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chưa thanh toán", "Đã thanh toán", "Quá hạn" }));

        to_totalAmount.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        to_totalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                to_totalAmountActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setText("Đến");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel8.setText("Từ");

        searchBtn.setBackground(new java.awt.Color(50, 65, 94));
        searchBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(to_totalAmount))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dueDate, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(billDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(billId, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apartmentId, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)))
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(totalAmount))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(billId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(apartmentId, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(billDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(to_totalAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void totalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountActionPerformed

    private void to_totalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_totalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_to_totalAmountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apartmentId;
    private com.toedter.calendar.JDateChooser billDate;
    private javax.swing.JTextField billId;
    private com.toedter.calendar.JDateChooser dueDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField to_totalAmount;
    private javax.swing.JTextField totalAmount;
    // End of variables declaration//GEN-END:variables
}
