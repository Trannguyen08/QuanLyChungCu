package main.java.utc2.apartmentManage.view.ManagerUI.searchWindow;

import javax.swing.*;
import main.java.utc2.apartmentManage.controller.ManagerControl.ResidentHandle.searchIconHandler;


public class searchResident extends JFrame {
    public searchResident(JTable table) {
        initComponents();
        searchIconHandler search = new searchIconHandler(fullName, status, birthDate,toBirthDate,searchBtn, table, this);
        this.setLocationRelativeTo(null);
        this.setTitle("Tìm kiếm cư dân");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fullName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        searchBtn = new javax.swing.JButton();
        status = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        birthDate = new com.toedter.calendar.JDateChooser();
        toBirthDate = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText("Tên chủ hộ");

        fullName.setBackground(new java.awt.Color(250, 250, 250));
        fullName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        fullName.setSelectionColor(new java.awt.Color(113, 193, 220));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText("Hợp đồng");

        searchBtn.setBackground(new java.awt.Color(50, 65, 94));
        searchBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Tìm kiếm");
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        status.setBackground(new java.awt.Color(249, 248, 248));
        status.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Hiệu lực", "Hết hạn" }));
        status.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText("Ngày sinh");

        birthDate.setDateFormatString("dd/MM/yyyy");
        birthDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        toBirthDate.setDateFormatString("dd/MM/yyyy");
        toBirthDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel18.setText("Đến");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel19.setText("Từ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(toBirthDate, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(birthDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(46, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(115, 115, 115))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(birthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toBirthDate, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser birthDate;
    private javax.swing.JTextField fullName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox<String> status;
    private com.toedter.calendar.JDateChooser toBirthDate;
    // End of variables declaration//GEN-END:variables
}
