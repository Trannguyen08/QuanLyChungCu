package main.java.utc2.apartmentManage.view.UserUI.detailWindow;

import javax.swing.JFrame;
import javax.swing.JTable;
import main.java.utc2.apartmentManage.controller.UserControl.BillControl.acceptTransactionHandle;


public class acceptTransaction extends javax.swing.JFrame {


    public acceptTransaction(JTable table, int id, String humanName, String total, String dueDate) {
        initComponents();
        new acceptTransactionHandle(table, id, humanName, total, dueDate, acceptBtn, 
                                    billid, human, date, money, this);
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
        c = new javax.swing.JLabel();
        acceptBtn = new javax.swing.JButton();
        money = new javax.swing.JLabel();
        human = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Xác nhận thanh toán");

        billid.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        billid.setText("Số hóa đơn: ");

        date.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        date.setText("Ngày thanh toán:");

        c.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        c.setText("Tổng tiền:");

        acceptBtn.setBackground(new java.awt.Color(50, 65, 94));
        acceptBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        acceptBtn.setForeground(new java.awt.Color(255, 255, 255));
        acceptBtn.setText("Xác nhận");
        acceptBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        money.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        money.setText("jLabel2");

        human.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        human.setText("Người thanh toán:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 72, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(39, 39, 39))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(billid)
                            .addComponent(date)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(c)
                                .addGap(35, 35, 35)
                                .addComponent(money))
                            .addComponent(human)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(acceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(36, 36, 36)
                .addComponent(billid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(human)
                .addGap(27, 27, 27)
                .addComponent(date)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c)
                    .addComponent(money))
                .addGap(29, 29, 29)
                .addComponent(acceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
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
    private javax.swing.JLabel c;
    private javax.swing.JLabel date;
    private javax.swing.JLabel human;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel money;
    // End of variables declaration//GEN-END:variables
}
