
package main.java.utc2.apartmentManage.view.ManagerUI.Pages;

import javax.swing.JPanel;
import main.java.utc2.apartmentManage.controller.ManagerControl.ReportControl.reportHandle;


public class ReportUI extends javax.swing.JPanel {
    public ReportUI(JPanel mainPanel) {
        initComponents();
        new reportHandle(finance, employ, mainPanel);
        setVisible(true);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        employ = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        finance = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        employ.setBackground(new java.awt.Color(33, 100, 126));
        employ.setForeground(new java.awt.Color(255, 255, 255));
        employ.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BÁO CÁO LƯƠNG NHÂN VIÊN");

        javax.swing.GroupLayout employLayout = new javax.swing.GroupLayout(employ);
        employ.setLayout(employLayout);
        employLayout.setHorizontalGroup(
            employLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employLayout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(51, 51, 51))
        );
        employLayout.setVerticalGroup(
            employLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(58, 58, 58))
        );

        finance.setBackground(new java.awt.Color(33, 100, 126));
        finance.setForeground(new java.awt.Color(255, 255, 255));
        finance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("BÁO CÁO DOANH THU");

        javax.swing.GroupLayout financeLayout = new javax.swing.GroupLayout(finance);
        finance.setLayout(financeLayout);
        financeLayout.setHorizontalGroup(
            financeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(72, 72, 72))
        );
        financeLayout.setVerticalGroup(
            financeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, financeLayout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addComponent(finance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(employ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(finance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(277, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel employ;
    private javax.swing.JPanel finance;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
