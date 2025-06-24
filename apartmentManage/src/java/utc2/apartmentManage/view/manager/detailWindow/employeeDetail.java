package java.utc2.apartmentManage.view.manager.detailWindow;

import utc2.apartmentManage.controller.manager.report.employeeDetailHandle;
import utc2.apartmentManage.model.EmployeeInfo;

import javax.swing.*;

public class employeeDetail extends JFrame {

    public employeeDetail(EmployeeInfo er, JComboBox<String> month, JComboBox<String> year) {
        initComponents();
        employeeDetailHandle employeeDetailHandle = new employeeDetailHandle(month, year, table, id, name, chucvu,
                                                                            calam, songay, er);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Chi tiết nhân viên");
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        id = new JLabel();
        name = new JLabel();
        calam = new JLabel();
        songay = new JLabel();
        jScrollPane1 = new JScrollPane();
        table = new JTable();
        chucvu = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        id.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        id.setText("ID:");

        name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        name.setText("Tên nhân viên");

        calam.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        calam.setText("Ca làm");

        songay.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        songay.setText("Số ngày công");

        table.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Ngày", "Giờ vào ca", "Giờ ra ca"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        chucvu.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        chucvu.setText("Chức vụ");

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(id, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                            .addComponent(chucvu, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(calam)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(name)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(8, 8, 8)
                        .addComponent(songay)
                        .addGap(134, 134, 134)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(id)
                    .addComponent(name))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(calam)
                    .addComponent(songay)
                    .addComponent(chucvu))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel calam;
    private JLabel chucvu;
    private JLabel id;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JLabel name;
    private JLabel songay;
    private JTable table;
    // End of variables declaration//GEN-END:variables
}
