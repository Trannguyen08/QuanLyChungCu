package java.utc2.apartmentManage.view.user.pages;

import utc2.apartmentManage.controller.user.infomation.infomationHandle;
import utc2.apartmentManage.model.Account;

import javax.swing.*;

public class InfomationUserUI extends JPanel {
   
    public InfomationUserUI(Account acc) {
        initComponents();
        new infomationHandle(acc, editBtn, detailContractBtn,
                            apartment_id, area, contract_id, 
                            contract_name, dob, endDate, gender, 
                            idcard, index, interior, mail, 
                            name, phone, resident_id, 
                            roomNum, startDate, type, wcNum);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        personalInfoPanel = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel7 = new JLabel();
        resident_id = new JLabel();
        jLabel11 = new JLabel();
        name = new JLabel();
        gender = new JLabel();
        dob = new JLabel();
        phone = new JLabel();
        jLabel10 = new JLabel();
        jLabel12 = new JLabel();
        mail = new JLabel();
        editBtn = new JButton();
        jLabel22 = new JLabel();
        idcard = new JLabel();
        apartmentInfoPanel = new JPanel();
        jLabel4 = new JLabel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel9 = new JLabel();
        index = new JLabel();
        apartment_id = new JLabel();
        area = new JLabel();
        interior = new JLabel();
        jLabel20 = new JLabel();
        jLabel21 = new JLabel();
        roomNum = new JLabel();
        wcNum = new JLabel();
        jPanel1 = new JPanel();
        n = new JLabel();
        c = new JLabel();
        s = new JLabel();
        a = new JLabel();
        d = new JLabel();
        detailContractBtn = new JButton();
        contract_id = new JLabel();
        contract_name = new JLabel();
        startDate = new JLabel();
        endDate = new JLabel();
        type = new JLabel();

        personalInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        personalInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông tin cá nhân", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 0, 15))); // NOI18N

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


        jLabel22.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        jLabel22.setText("Số căn cước:");

        idcard.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        idcard.setText("jLabel17");

        GroupLayout personalInfoPanelLayout = new GroupLayout(personalInfoPanel);
        personalInfoPanel.setLayout(personalInfoPanelLayout);
        personalInfoPanelLayout.setHorizontalGroup(
            personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(87, Short.MAX_VALUE)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addGroup(personalInfoPanelLayout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(gender, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                            .addComponent(resident_id, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                            .addComponent(dob, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addComponent(name, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
                            .addComponent(phone, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
                            .addComponent(mail, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
                            .addComponent(idcard, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))))
                .addGap(24, 24, 24))
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        personalInfoPanelLayout.setVerticalGroup(
            personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(resident_id, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE))
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
                .addGap(47, 47, 47)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(mail))
                .addGap(44, 44, 44)
                .addGroup(personalInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(idcard))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editBtn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        apartmentInfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        apartmentInfoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông tin căn hộ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15))); // NOI18N

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

        GroupLayout apartmentInfoPanelLayout = new GroupLayout(apartmentInfoPanel);
        apartmentInfoPanel.setLayout(apartmentInfoPanelLayout);
        apartmentInfoPanelLayout.setHorizontalGroup(
            apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(apartmentInfoPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(apartment_id)
                    .addComponent(index)
                    .addComponent(interior, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                    .addComponent(area))
                .addGap(64, 64, 64)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel20, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(wcNum, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomNum, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)))
        );
        apartmentInfoPanelLayout.setVerticalGroup(
            apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(apartmentInfoPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(apartment_id)
                    .addComponent(jLabel20)
                    .addComponent(roomNum))
                .addGap(27, 27, 27)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(index)
                    .addComponent(jLabel21)
                    .addComponent(wcNum))
                .addGap(27, 27, 27)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(area))
                .addGap(26, 26, 26)
                .addGroup(apartmentInfoPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(interior))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Thông tin dịch vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 15))); // NOI18N

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

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(c)
                            .addComponent(n)
                            .addComponent(s)
                            .addComponent(a)
                            .addComponent(d))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(contract_id, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
                            .addComponent(contract_name, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(type, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addComponent(endDate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startDate, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(detailContractBtn, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(n)
                    .addComponent(contract_id))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(c)
                    .addComponent(contract_name))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(s)
                    .addComponent(startDate))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(a)
                    .addComponent(endDate))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(d)
                    .addComponent(type))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(detailContractBtn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(personalInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(apartmentInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(personalInfoPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(apartmentInfoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Hợp đồng");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel a;
    private JPanel apartmentInfoPanel;
    private JLabel apartment_id;
    private JLabel area;
    private JLabel c;
    private JLabel contract_id;
    private JLabel contract_name;
    private JLabel d;
    private JButton detailContractBtn;
    private JLabel dob;
    private JButton editBtn;
    private JLabel endDate;
    private JLabel gender;
    private JLabel idcard;
    private JLabel index;
    private JLabel interior;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel2;
    private JLabel jLabel20;
    private JLabel jLabel21;
    private JLabel jLabel22;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel9;
    private JPanel jPanel1;
    private JLabel mail;
    private JLabel n;
    private JLabel name;
    private JPanel personalInfoPanel;
    private JLabel phone;
    private JLabel resident_id;
    private JLabel roomNum;
    private JLabel s;
    private JLabel startDate;
    private JLabel type;
    private JLabel wcNum;
    // End of variables declaration//GEN-END:variables
}
