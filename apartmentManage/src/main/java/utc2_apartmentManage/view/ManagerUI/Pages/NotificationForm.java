package main.java.utc2_apartmentManage.view.ManagerUI.Pages;

import main.java.utc2_apartmentManage.controller.ManagerControl.NotificationHandle.NotificationHandler;


public class NotificationForm extends javax.swing.JPanel {

    public NotificationForm() {
        initComponents();
        NotificationHandler notificationHandler = new NotificationHandler(searchField, addBtn, deleteBtn, 
                                            editBtn, excelBtn, searchIcon, searchBtn, table, this);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                NotificationForm.this.requestFocusInWindow();
            }
        });
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchIcon = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        excelBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        searchIcon.setBackground(new java.awt.Color(250, 250, 250));
        searchIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resouces/images/filter.png"))); // NOI18N
        searchIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        searchBtn.setBackground(new java.awt.Color(30, 90, 115));
        searchBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        searchBtn.setForeground(new java.awt.Color(255, 255, 255));
        searchBtn.setText("Tìm kiếm");
        searchBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        addBtn.setBackground(new java.awt.Color(14, 41, 114));
        addBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resouces/images/add.png"))); // NOI18N
        addBtn.setText("Thêm");
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        deleteBtn.setBackground(new java.awt.Color(32, 86, 150));
        deleteBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        deleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resouces/images/delete.png"))); // NOI18N
        deleteBtn.setText("Xóa");
        deleteBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        editBtn.setBackground(new java.awt.Color(119, 164, 206));
        editBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resouces/images/update.png"))); // NOI18N
        editBtn.setText("Sửa");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        excelBtn.setBackground(new java.awt.Color(74, 158, 52));
        excelBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        excelBtn.setForeground(new java.awt.Color(255, 255, 255));
        excelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resouces/images/excel.png"))); // NOI18N
        excelBtn.setText("Xuất Excel");
        excelBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        table.setBackground(new java.awt.Color(250, 250, 250));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID_ThôngBáo", "Kiểu thông báo", "Tiêu đề", "Nội dung", "Ngày gửi"
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
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(60);
            table.getColumnModel().getColumn(2).setPreferredWidth(70);
            table.getColumnModel().getColumn(3).setPreferredWidth(250);
            table.getColumnModel().getColumn(4).setPreferredWidth(65);
        }

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        searchField.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/main/resouces/images/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchField, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(excelBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(excelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton excelBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchField;
    private javax.swing.JButton searchIcon;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
