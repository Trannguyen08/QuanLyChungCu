package java.utc2.apartmentManage.view.manager.editWindow;

import utc2.apartmentManage.controller.manager.apartment.editButtonHandle;

import javax.swing.*;

public class editApartment extends JFrame {
    public editApartment(JTable table) {
        initComponents();
        editButtonHandle edit = new editButtonHandle(interior, status, rentPrice, buyPrice,
                                                        editBtn, table, this);
        edit.loadSelectedRowData();
        setLocationRelativeTo(null);
        setTitle("Edit Apartment");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new JPanel();
        jLabel41 = new JLabel();
        jLabel43 = new JLabel();
        jLabel44 = new JLabel();
        editBtn = new JButton();
        status = new JComboBox<>();
        rentPrice = new JTextField();
        buyPrice = new JTextField();
        jLabel46 = new JLabel();
        interior = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(253, 253, 253));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel41.setText("Tình trạng");

        jLabel43.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel43.setText("Giá thuê");

        jLabel44.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel44.setText("Giá mua");

        editBtn.setBackground(new java.awt.Color(50, 65, 94));
        editBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Cập nhật");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        status.setBackground(new java.awt.Color(250, 250, 250));
        status.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        status.setModel(new DefaultComboBoxModel<>(new String[] { "Đã thuê", "Đã bán", "Trống", "Bảo trì ", "Chờ duyệt" }));

        rentPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        buyPrice.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel46.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel46.setText("Nội thất");

        interior.setBackground(new java.awt.Color(250, 250, 250));
        interior.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        interior.setModel(new DefaultComboBoxModel<>(new String[] { "Trống", "Cơ bản", "Đầy đủ" }));

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel46, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel41, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel43, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel44))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buyPrice)
                    .addComponent(rentPrice)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(interior, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                            .addComponent(status, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(74, 74, 74))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(status, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(interior, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(rentPrice, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(buyPrice, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField buyPrice;
    private JButton editBtn;
    private JComboBox<String> interior;
    private JLabel jLabel41;
    private JLabel jLabel43;
    private JLabel jLabel44;
    private JLabel jLabel46;
    private JPanel jPanel5;
    private JTextField rentPrice;
    private JComboBox<String> status;
    // End of variables declaration//GEN-END:variables
}
