package main.java.utc2.apartmentManage.view.UserUI.Pages;

import main.java.utc2.apartmentManage.controller.UserControl.BillControl.BillHandler;
import main.java.utc2.apartmentManage.model.Account;


public class Bill extends javax.swing.JPanel {

    public Bill(Account acc) {
        initComponents();
        this.setVisible(true);
        new BillHandler(acc, searchIcon, payBtn, paidHistoryBtn, excelBtn, table);
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        payBtn = new javax.swing.JButton();
        searchIcon = new javax.swing.JButton();
        paidHistoryBtn = new javax.swing.JButton();
        excelBtn = new javax.swing.JButton();

        table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID_Hóa đơn", "Ngày lập", "Ngày đến hạn", "Tổng tiền", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(30);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(100);
            table.getColumnModel().getColumn(3).setPreferredWidth(100);
            table.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        payBtn.setBackground(new java.awt.Color(13, 50, 107));
        payBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        payBtn.setForeground(new java.awt.Color(255, 255, 255));
        payBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        payBtn.setLabel("Thanh toán");
        payBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payBtnActionPerformed(evt);
            }
        });

        searchIcon.setBackground(new java.awt.Color(253, 253, 253));
        searchIcon.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resources/images/icons/icons8-filter-30.png"))); // NOI18N
        searchIcon.setText("Lọc");

        paidHistoryBtn.setBackground(new java.awt.Color(13, 50, 107));
        paidHistoryBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        paidHistoryBtn.setForeground(new java.awt.Color(255, 255, 255));
        paidHistoryBtn.setText("Xem lịch sử thanh toán");
        paidHistoryBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        paidHistoryBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paidHistoryBtnActionPerformed(evt);
            }
        });

        excelBtn.setBackground(new java.awt.Color(13, 50, 107));
        excelBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        excelBtn.setForeground(new java.awt.Color(255, 255, 255));
        excelBtn.setText("Xuất danh sách");
        excelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        excelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(searchIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(payBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(paidHistoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(excelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(payBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchIcon))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paidHistoryBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void payBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_payBtnActionPerformed

    private void paidHistoryBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paidHistoryBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paidHistoryBtnActionPerformed

    private void excelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excelBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_excelBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton excelBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton paidHistoryBtn;
    private javax.swing.JButton payBtn;
    private javax.swing.JButton searchIcon;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
