package java.utc2.apartmentManage.view.manager.addWindow;

import utc2.apartmentManage.controller.manager.employee.addButtonHandle;

import javax.swing.*;

public class addEmployee extends JFrame {
    public addEmployee(JTable table) {
        initComponents();
        addButtonHandle add = new addButtonHandle(shift, addBtn, fullName, gender, date, phoneNumber,
                                        email, idcard, position, salary, username, password, table, this);
        this.setLocationRelativeTo(null);
        this.setTitle("Thêm nhân viên");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        salary = new JTextField();
        username = new JTextField();
        jLabel21 = new JLabel();
        jLabel12 = new JLabel();
        gender = new JComboBox<>();
        jLabel22 = new JLabel();
        position = new JComboBox<>();
        phoneNumber = new JTextField();
        jLabel23 = new JLabel();
        jLabel24 = new JLabel();
        email = new JTextField();
        jLabel26 = new JLabel();
        addBtn = new JButton();
        jLabel27 = new JLabel();
        jLabel28 = new JLabel();
        jLabel29 = new JLabel();
        fullName = new JTextField();
        jLabel30 = new JLabel();
        password = new JPasswordField();
        jLabel13 = new JLabel();
        date = new com.toedter.calendar.JDateChooser();
        jLabel25 = new JLabel();
        idcard = new JTextField();
        jLabel31 = new JLabel();
        shift = new JComboBox<>();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(BorderFactory.createTitledBorder(""));

        salary.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        username.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N


        jLabel21.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel21.setText("Email");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel12.setText("Giới tính");

        gender.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gender.setModel(new DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel22.setText("Chức vụ");

        position.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        position.setModel(new DefaultComboBoxModel<>(new String[] { "Bảo vệ", "Lễ tân", "Dọn dẹp" }));

        phoneNumber.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel23.setText("Lương");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel24.setText("Họ tên");

        email.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel26.setText("Số điện thoại");

        addBtn.setBackground(new java.awt.Color(50, 65, 94));
        addBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Thêm ");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel27.setText("Thêm tài khoản");

        jLabel28.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel28.setText("Thêm thông tin nhân viên");

        jLabel29.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel29.setText("Tài khoản");

        fullName.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N


        jLabel30.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel30.setText("Mật khẩu");

        password.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel13.setText("Ngày sinh");

        date.setDateFormatString("dd/MM/yyyy");
        date.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel25.setText("Số căn cước");

        idcard.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel31.setText("Ca làm");

        shift.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        shift.setModel(new DefaultComboBoxModel<>(new String[] { "Ca sáng", "Ca tối" }));

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel27)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(62, 62, 62))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                    .addComponent(username, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                    .addComponent(password))
                                .addGap(89, 89, 89)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel24, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                            .addGap(15, 15, 15))
                                        .addComponent(jLabel26)
                                        .addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel21, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel22, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                            .addGap(59, 59, 59)
                                            .addComponent(jLabel23, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(email)
                                        .addComponent(fullName))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(gender, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(date, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(idcard, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(position, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(salary, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel31, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                    .addComponent(shift, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel24))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(username, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(fullName, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(date, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(password, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(gender))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phoneNumber, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel21)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel25)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idcard, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(position, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                    .addComponent(salary, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel31)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shift, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton addBtn;
    private com.toedter.calendar.JDateChooser date;
    private JTextField email;
    private JTextField fullName;
    private JComboBox<String> gender;
    private JTextField idcard;
    private JLabel jLabel12;
    private JLabel jLabel13;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel23;
    private JLabel jLabel24;
    private JLabel jLabel25;
    private JLabel jLabel26;
    private JLabel jLabel27;
    private JLabel jLabel28;
    private JLabel jLabel29;
    private JLabel jLabel30;
    private JLabel jLabel31;
    private JPanel jPanel1;
    private JPasswordField password;
    private JTextField phoneNumber;
    private JComboBox<String> position;
    private JTextField salary;
    private JComboBox<String> shift;
    private JTextField username;
    // End of variables declaration//GEN-END:variables
}
