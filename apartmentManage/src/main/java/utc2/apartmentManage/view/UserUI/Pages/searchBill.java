package main.java.utc2.apartmentManage.view.UserUI.Pages;

import javax.swing.*;
import main.java.utc2.apartmentManage.controller.UserControl.searchIconHandler;


public class searchBill extends JFrame {

    public searchBill(JTable table) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Tìm kiếm hóa đơn");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        searchIconHandler search = new searchIconHandler(billId, totalAmount, searchBtn,
                                                    billDate, dueDate, status, table, this, to_totalAmount);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        billId = new JTextField();
        billDate = new com.toedter.calendar.JDateChooser();
        dueDate = new com.toedter.calendar.JDateChooser();
        totalAmount = new JTextField();
        status = new JComboBox<>();
        to_totalAmount = new JTextField();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        searchBtn = new JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText("ID_Hóa đơn");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText("Ngày lập");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText("Ngày đến hạn");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText("Tổng tiền");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel6.setText("Trạng thái");

        billId.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        billDate.setDateFormatString("dd/MM/yyyy");
        billDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        dueDate.setDateFormatString("dd/MM/yyyy");
        dueDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        totalAmount.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        totalAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalAmountActionPerformed(evt);
            }
        });

        status.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status.setModel(new DefaultComboBoxModel<>(new String[] { " ", "Chưa thanh toán", "Đã thanh toán", "Quá hạn" }));

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
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(billId, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(billDate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addComponent(dueDate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(totalAmount, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(to_totalAmount, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 38, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(billId, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(billDate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueDate, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalAmount, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(to_totalAmount, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(status, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(searchBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void totalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalAmountActionPerformed

    private void to_totalAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_to_totalAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_to_totalAmountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser billDate;
    private JTextField billId;
    private com.toedter.calendar.JDateChooser dueDate;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JButton searchBtn;
    private JComboBox<String> status;
    private JTextField to_totalAmount;
    private JTextField totalAmount;
    // End of variables declaration//GEN-END:variables
}
