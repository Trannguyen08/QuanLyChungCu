package java.utc2.apartmentManage.view.employee;

import utc2.apartmentManage.controller.employee.employeeInfoHandle;
import utc2.apartmentManage.model.Account;

import javax.swing.*;

public class InfomationEmployeeUI extends JPanel {
   
    public InfomationEmployeeUI(Account acc) {
        initComponents();
        new employeeInfoHandle(dob, employee_id, gender, idcard, mail, 
                            name, phone, editBtn, acc);
        
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personalInfoPanel = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel7 = new JLabel();
        employee_id = new JLabel();
        jLabel11 = new JLabel();
        name = new JLabel();
        gender = new JLabel();
        dob = new JLabel();
        phone = new JLabel();
        jLabel10 = new JLabel();
        jLabel12 = new JLabel();
        mail = new JLabel();
        editBtn = new JButton();
        jLabel8 = new JLabel();
        idcard = new JLabel();

        personalInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông tin cá nhân", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 16), new java.awt.Color(255, 255, 255))); // NOI18N
        personalInfoPanel.setForeground(java.awt.Color.white);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel1.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel2.setText("Số đện thoại");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel3.setText("Email");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel7.setText("ID nhân viên");

        employee_id.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        employee_id.setText("dfg");

        name.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        name.setText("jLabel12");

        gender.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        gender.setText("jLabel13");

        dob.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        dob.setText("jLabel14");

        phone.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        phone.setText("jLabel15");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel10.setText("Giới tính");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel12.setText("Ngày sinh");

        mail.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        mail.setText("jLabel17");

        editBtn.setBackground(new java.awt.Color(35, 62, 125));
        editBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Sửa thông tin");


        jLabel8.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel8.setText("Số căn cước");

        idcard.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        idcard.setText("jLabel17");

        GroupLayout personalInfoPanelLayout = new GroupLayout(personalInfoPanel);
        personalInfoPanel.setLayout(personalInfoPanelLayout);
        personalInfoPanelLayout.setHorizontalGroup(
            personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(idcard, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
                            .addGap(23, 23, 23))
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(20, 368, Short.MAX_VALUE))
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addComponent(jLabel10, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
                            .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(personalInfoPanelLayout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(employee_id, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(name, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gender, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dob, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(phone, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mail, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                        .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149))))
        );
        personalInfoPanelLayout.setVerticalGroup(
            personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(employee_id, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(27, 27, 27)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(gender))
                .addGap(45, 45, 45)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(dob))
                .addGap(48, 48, 48)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(phone))
                .addGap(36, 36, 36)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mail))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(idcard))
                .addGap(39, 39, 39)
                .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(personalInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(personalInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel dob;
    private JButton editBtn;
    private JLabel employee_id;
    private JLabel gender;
    private JLabel idcard;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel mail;
    private JLabel name;
    private JPanel personalInfoPanel;
    private JLabel phone;
    // End of variables declaration//GEN-END:variables
}
