package main.java.utc2.apartmentManage.view.UserUI.Pages;

import javax.swing.*;
import main.java.utc2.apartmentManage.controller.UserControl.InfomationControl.InfomationResidentHandle;
import main.java.utc2.apartmentManage.model.Account;

public class InfomationUserUI extends JPanel {
   
    public InfomationUserUI(Account acc) {
        initComponents();
        new InfomationResidentHandle(acc, editBtn, detailContractBtn, 
                            apartment_id, area, contract_id, 
                            contract_name, dob, endDate, gender, 
                            idcard, index, interior, mail, 
                            name, phone, resident_id, 
                            roomNum, startDate, type, wcNum);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personalInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        resident_id = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        gender = new javax.swing.JLabel();
        dob = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        mail = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        idcard = new javax.swing.JLabel();
        apartmentInfoPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        index = new javax.swing.JLabel();
        apartment_id = new javax.swing.JLabel();
        area = new javax.swing.JLabel();
        interior = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        roomNum = new javax.swing.JLabel();
        wcNum = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        n = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        s = new javax.swing.JLabel();
        a = new javax.swing.JLabel();
        d = new javax.swing.JLabel();
        detailContractBtn = new javax.swing.JButton();
        contract_id = new javax.swing.JLabel();
        contract_name = new javax.swing.JLabel();
        startDate = new javax.swing.JLabel();
        endDate = new javax.swing.JLabel();
        type = new javax.swing.JLabel();

        personalInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin cá nhân", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 15))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel1.setText("Họ và tên");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel2.setText("Số đện thoại");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel3.setText("Email");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel7.setText("ID cư dân");

        resident_id.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        resident_id.setText("dfg");

        name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        name.setText("jLabel12");

        gender.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        gender.setText("jLabel13");

        dob.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        dob.setText("jLabel14");

        phone.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        phone.setText("jLabel15");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel10.setText("Giới tính");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel12.setText("Ngày sinh");

        mail.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        mail.setText("jLabel17");

        editBtn.setBackground(new java.awt.Color(50, 65, 94));
        editBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("Sửa thông tin");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel22.setText("Số căn cước:");

        idcard.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        idcard.setText("jLabel17");

        javax.swing.GroupLayout personalInfoPanelLayout = new javax.swing.GroupLayout(personalInfoPanel);
        personalInfoPanel.setLayout(personalInfoPanelLayout);
        personalInfoPanelLayout.setHorizontalGroup(
            personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(resident_id, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dob, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phone, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idcard, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        personalInfoPanelLayout.setVerticalGroup(
            personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(resident_id, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(27, 27, 27)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(gender))
                .addGap(45, 45, 45)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(dob))
                .addGap(48, 48, 48)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(phone))
                .addGap(47, 47, 47)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mail))
                .addGap(44, 44, 44)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(idcard))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(editBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        apartmentInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin căn hộ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel4.setText("Vị trí:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel5.setText("Diện tích:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel6.setText("Nội thất:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel9.setText("ID:");

        index.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        index.setText("jLabel19");

        apartment_id.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        apartment_id.setText("jLabel15");

        area.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        area.setText("A");

        interior.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        interior.setText("jLabel15");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel20.setText("Số phòng ngủ:");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel21.setText("Số phòng WC:");

        roomNum.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        roomNum.setText("jLabel15");

        wcNum.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        wcNum.setText("jLabel15");

        javax.swing.GroupLayout apartmentInfoPanelLayout = new javax.swing.GroupLayout(apartmentInfoPanel);
        apartmentInfoPanel.setLayout(apartmentInfoPanelLayout);
        apartmentInfoPanelLayout.setHorizontalGroup(
            apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(apartmentInfoPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(apartment_id)
                    .addComponent(index)
                    .addComponent(interior, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(area))
                .addGap(64, 64, 64)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wcNum, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomNum, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        apartmentInfoPanelLayout.setVerticalGroup(
            apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(apartmentInfoPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(apartment_id)
                    .addComponent(jLabel20)
                    .addComponent(roomNum))
                .addGap(27, 27, 27)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(index)
                    .addComponent(jLabel21)
                    .addComponent(wcNum))
                .addGap(27, 27, 27)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(area))
                .addGap(26, 26, 26)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(interior))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Thông tin dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15))); // NOI18N

        n.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        n.setText("ID hợp đồng:");

        c.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        c.setText("Đại diện:");

        s.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        s.setText("Ngày bắt đầu:");

        a.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        a.setText("Ngày kết thúc:");

        d.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        d.setText("Loại hợp đồng:");

        detailContractBtn.setBackground(new java.awt.Color(50, 65, 94));
        detailContractBtn.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        detailContractBtn.setForeground(new java.awt.Color(255, 255, 255));
        detailContractBtn.setText("Xem chi tiết");
        detailContractBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailContractBtnActionPerformed(evt);
            }
        });

        contract_id.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        contract_id.setText("jLabel15");

        contract_name.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        contract_name.setText("jLabel15");

        startDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        startDate.setText("jLabel15");

        endDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        endDate.setText("jLabel15");

        type.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        type.setText("jLabel15");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c)
                            .addComponent(n)
                            .addComponent(s)
                            .addComponent(a)
                            .addComponent(d))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(contract_id, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(contract_name, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(type, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(endDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(detailContractBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n)
                    .addComponent(contract_id))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c)
                    .addComponent(contract_name))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(s)
                    .addComponent(startDate))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(a)
                    .addComponent(endDate))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(d)
                    .addComponent(type))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(detailContractBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(apartmentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(apartmentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Hợp đồng");
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        
    }//GEN-LAST:event_editBtnActionPerformed

    private void detailContractBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailContractBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_detailContractBtnActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel a;
    private javax.swing.JPanel apartmentInfoPanel;
    private javax.swing.JLabel apartment_id;
    private javax.swing.JLabel area;
    private javax.swing.JLabel c;
    private javax.swing.JLabel contract_id;
    private javax.swing.JLabel contract_name;
    private javax.swing.JLabel d;
    private javax.swing.JButton detailContractBtn;
    private javax.swing.JLabel dob;
    private javax.swing.JButton editBtn;
    private javax.swing.JLabel endDate;
    private javax.swing.JLabel gender;
    private javax.swing.JLabel idcard;
    private javax.swing.JLabel index;
    private javax.swing.JLabel interior;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel mail;
    private javax.swing.JLabel n;
    private javax.swing.JLabel name;
    private javax.swing.JPanel personalInfoPanel;
    private javax.swing.JLabel phone;
    private javax.swing.JLabel resident_id;
    private javax.swing.JLabel roomNum;
    private javax.swing.JLabel s;
    private javax.swing.JLabel startDate;
    private javax.swing.JLabel type;
    private javax.swing.JLabel wcNum;
    // End of variables declaration//GEN-END:variables
}
