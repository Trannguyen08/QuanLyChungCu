package main.java.utc2_apartmentManage.view.ManagerUI.editWindow;

import main.java.utc2_apartmentManage.controller.ManagerControl.ApartmentHandle.editImageHandler;
import javax.swing.*;
import java.awt.*;

public class editImages extends javax.swing.JFrame {
    private editImageHandler ei;
    public editImages(JTable table) {
        initComponents();
        
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        JLabel label4 = new JLabel();

        // Đặt kích thước cho JLabel
        label1.setPreferredSize(new Dimension(170, 170));
        label2.setPreferredSize(new Dimension(170, 170));
        label3.setPreferredSize(new Dimension(170, 170));
        label4.setPreferredSize(new Dimension(170, 170));

        // Tạo các JPanel chứa JLabel
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.add(label1);
        panel2.add(label2);
        panel3.add(label3);
        panel4.add(label4);

        // Đặt layout cho các panel
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        panel3.setLayout(new FlowLayout());
        panel4.setLayout(new FlowLayout());

        // Thêm các panel con vào panel chính
        jPanel1.setLayout(new GridLayout(2, 2, 10, 10)); // 2 hàng, 2 cột
        jPanel1.add(panel1);
        jPanel1.add(panel2);
        jPanel1.add(panel3);
        jPanel1.add(panel4);

        pack();
    
        ei = new editImageHandler(jButton1, jButton3, jButton2,
                                    label1, label2, label3, label4, 
                                    panel1, panel2, panel3, panel4, table);
        

        setLocationRelativeTo(null);
        setTitle("Sửa ảnh");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public void addImage() {
        ei.addImage();
    }
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(2,2,5,5));

        jButton1.setBackground(new java.awt.Color(245, 246, 248));
        jButton1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButton1.setText("Thêm");

        jButton2.setBackground(new java.awt.Color(245, 246, 248));
        jButton2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButton2.setText("Sửa");

        jButton3.setBackground(new java.awt.Color(245, 246, 248));
        jButton3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jButton3.setText("Xóa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
