
package main.java.utc2.apartmentManage.view.UserUI.editWindow;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;


public class NotificationPanel extends javax.swing.JPanel {

    
    public NotificationPanel(String type, String title, String content, String sentDate) {
        initComponents();
        this.sentDate.setText("<html>Thông báo: " + type + "</html>");
        this.title.setText("<html><body style='width:250px'>Tiêu đề: " + title + "</body></html>");
        this.sentDate.setText("Ngày gửi: " + sentDate);

        // JTextArea cấu hình xuống dòng
        this.content.setLineWrap(true);
        this.content.setWrapStyleWord(true);
        this.content.setEditable(false);
        this.content.setFocusable(false);
        this.content.setOpaque(false);
        this.content.setText(content);
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(330, 285)); 
        this.setMaximumSize(new Dimension(330, 285));
        this.setMinimumSize(new Dimension(330, 285));

                
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sentDate = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        content = new javax.swing.JTextArea();
        type = new javax.swing.JLabel();

        setBackground(new java.awt.Color(34, 69, 130));

        sentDate.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        sentDate.setForeground(new java.awt.Color(255, 255, 255));
        sentDate.setText("Ngày gửi:");

        title.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Tiêu đề: ");

        content.setColumns(20);
        content.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        content.setRows(5);
        jScrollPane1.setViewportView(content);

        type.setFont(new java.awt.Font("Arial", 0, 15)); // NOI18N
        type.setForeground(new java.awt.Color(255, 255, 255));
        type.setText("Thông báo: Chung");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(type)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sentDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea content;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel sentDate;
    private javax.swing.JLabel title;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables
}
