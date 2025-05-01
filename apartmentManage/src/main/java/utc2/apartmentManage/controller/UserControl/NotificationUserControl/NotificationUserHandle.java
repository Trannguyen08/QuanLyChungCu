package main.java.utc2.apartmentManage.controller.UserControl.NotificationUserControl;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import main.java.utc2.apartmentManage.service.userService.notificationUserIMP;

public class NotificationUserHandle {
    private final notificationUserIMP nt = new notificationUserIMP();
    private JScrollPane scrollPane;
    private JTextField searchField;

    public NotificationUserHandle(JScrollPane scrollPane, JTextField searchField) {
        this.scrollPane = scrollPane;
        this.searchField = searchField;

        // Khởi tạo wrapperPanel và set layout cho nó
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS)); // Stack các hàng theo chiều dọc

        // Gọi hàm thêm các thông báo vào wrapperPanel
        nt.setUpPanel(wrapperPanel);

        // Tạo JScrollPane mới cho wrapperPanel nếu chưa có
        scrollPane.setViewportView(wrapperPanel); // Đảm bảo scrollPane chứa wrapperPanel

        // Tùy chọn thêm scroll bar
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  
        
        placeHolder();
        setupSearchListener(searchField, scrollPane);

    }
    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập tiêu đề thông báo...");
        searchField.addFocusListener(new java.awt.event.FocusListener() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Nhập tiêu đề thông báo...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập tiêu đề thông báo...");
                }
            }
        });
    }
    
    private void setupSearchListener(JTextField searchField, JScrollPane scrollPane) {
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                searchAndUpdate();
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                searchAndUpdate();
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                searchAndUpdate();
            }

            private void searchAndUpdate() {
                String keyword = searchField.getText().trim();
                JPanel wrapperPanel = new JPanel();
                wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));

                if (!keyword.isEmpty() && !keyword.equals("Nhập tiêu đề thông báo...")) {
                    nt.search(wrapperPanel, keyword); 
                } else {
                    nt.setUpPanel(wrapperPanel);
                }

                scrollPane.setViewportView(wrapperPanel);
                scrollPane.revalidate();
                scrollPane.repaint();
            }
        });
    }


}


