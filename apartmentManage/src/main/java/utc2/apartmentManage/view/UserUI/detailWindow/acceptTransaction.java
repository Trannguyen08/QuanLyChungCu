package main.java.utc2.apartmentManage.view.UserUI.detailWindow;

import javax.swing.JFrame;
import javax.swing.JTable;
import main.java.utc2.apartmentManage.controller.UserControl.BillControl.acceptTransactionHandle;


public class acceptTransaction extends javax.swing.JFrame {


    public acceptTransaction(JTable table, int id, String total, String dueDate) {
        initComponents();
        new acceptTransactionHandle(table, id, total, dueDate, acceptBtn, 
                                    billid, date, money, this);
        this.setLocationRelativeTo(null);
        this.setTitle("Xác nhận thanh toán");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        billid = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        money = new javax.swing.JLabel();
        acceptBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Xác nhận thanh toán");

        billid.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        billid.setText("Số hóa đơn: ");

        date.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        date.setText("Ngày thanh toán:");

        money.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        money.setText("Tổng tiền:");

        acceptBtn.setBackground(new java.awt.Color(50, 65, 94));
        acceptBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        acceptBtn.setForeground(new java.awt.Color(255, 255, 255));
        acceptBtn.setText("Xác nhận");
        acceptBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(billid)
                            .addComponent(date)
                            .addComponent(money)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(acceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 52, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(39, 39, 39))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(billid)
                .addGap(29, 29, 29)
                .addComponent(date)
                .addGap(32, 32, 32)
                .addComponent(money)
                .addGap(29, 29, 29)
                .addComponent(acceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton acceptBtn;
    private javax.swing.JLabel billid;
    private javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel money;
    // End of variables declaration//GEN-END:variables
}
