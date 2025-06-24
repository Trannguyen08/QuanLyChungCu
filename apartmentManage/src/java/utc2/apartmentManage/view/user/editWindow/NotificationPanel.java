package java.utc2.apartmentManage.view.user.editWindow;

import javax.swing.*;
import java.awt.*;

public class NotificationPanel extends JPanel {

    public NotificationPanel(String type, String title, String content, String sentDate) {
        initComponents();

        this.type.setText("Thông báo: " + type);
        this.title.setText("<html><body style='width:250px'>Tiêu đề: " + title + "</body></html>");
        this.sentDate.setText(sentDate);
        this.content.setLineWrap(true);
        this.content.setWrapStyleWord(true);
        this.content.setEditable(false);
        this.content.setFocusable(false);
        this.content.setOpaque(false);
        this.content.setText(content);

        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.setPreferredSize(new Dimension(320, 372));

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sentDate = new JLabel();
        title = new JLabel();
        jScrollPane1 = new JScrollPane();
        content = new JTextArea();
        type = new JLabel();

        setBackground(new Color(34, 69, 130));

        sentDate.setFont(new Font("Arial", 0, 15)); // NOI18N
        sentDate.setForeground(new Color(255, 255, 255));
        sentDate.setText("Ngày gửi:");

        title.setFont(new Font("Arial", 0, 15)); // NOI18N
        title.setForeground(new Color(255, 255, 255));
        title.setText("Tiêu đề: ");

        content.setColumns(20);
        content.setFont(new Font("Arial", 0, 15)); // NOI18N
        content.setRows(5);
        jScrollPane1.setViewportView(content);

        type.setFont(new Font("Arial", 0, 15)); // NOI18N
        type.setForeground(new Color(255, 255, 255));
        type.setText("Thông báo: Chung");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(type, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sentDate, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sentDate)
                    .addComponent(type))
                .addGap(18, 18, 18)
                .addComponent(title)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextArea content;
    private JScrollPane jScrollPane1;
    private JLabel sentDate;
    private JLabel title;
    private JLabel type;
    // End of variables declaration//GEN-END:variables
}
