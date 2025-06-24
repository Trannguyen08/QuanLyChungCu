package java.utc2.apartmentManage.view.manager.addWindow;

import utc2.apartmentManage.controller.manager.service.addButtonHandle;

import javax.swing.*;

public class addService extends JFrame {

    public addService(JTable table) {
        initComponents();
        pack();
        addButtonHandle adds = new addButtonHandle(add, note, name, relevant, price, unit, type, table, this);
        this.setLocationRelativeTo(null);
        this.setTitle("Thêm thông tin");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        type = new JComboBox<>();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        name = new JTextField();
        add = new JButton();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        price = new JTextField();
        unit = new JTextField();
        jLabel6 = new JLabel();
        jScrollPane1 = new JScrollPane();
        note = new JTextArea();
        jLabel7 = new JLabel();
        relevant = new JComboBox<>();

        type.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        type.setModel(new DefaultComboBoxModel<>(new String[] { "Cố định", "Tính theo sử dụng" }));
        type.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText("Đơn giá");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText("Đơn vị");

        name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        add.setBackground(new java.awt.Color(50, 65, 94));
        add.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Thêm");
        add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText("Tên Dịch Vụ");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText("Kiểu dịch vụ");

        price.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        unit.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel6.setText("Ghi chú");

        note.setColumns(10);
        note.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        note.setRows(3);
        jScrollPane1.setViewportView(note);

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setText("Liên quan đến");

        relevant.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        relevant.setModel(new DefaultComboBoxModel<>(new String[] { "Căn hộ", "Cá nhân" }));
        relevant.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(69, 69, 69)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(unit, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                            .addComponent(price, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(relevant, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(type, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(name))))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(add, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(name, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(type, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(relevant, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(price, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(unit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(30, 30, 30)
                .addComponent(add, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton add;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JScrollPane jScrollPane1;
    private JTextField name;
    private JTextArea note;
    private JTextField price;
    private JComboBox<String> relevant;
    private JComboBox<String> type;
    private JTextField unit;
    // End of variables declaration//GEN-END:variables
}
