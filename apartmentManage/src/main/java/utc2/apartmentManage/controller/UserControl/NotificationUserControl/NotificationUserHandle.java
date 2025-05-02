package main.java.utc2.apartmentManage.controller.UserControl.NotificationUserControl;

import javax.swing.*;
import main.java.utc2.apartmentManage.service.userService.notificationUserIMP;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationUserHandle {
    private final notificationUserIMP nt = new notificationUserIMP();
    private JScrollPane scrollPane;
    private JTextField searchField;
    private String object;
    private JPanel wrapperPanel;
    private Timer autoRefreshTimer;

    public NotificationUserHandle(JScrollPane scrollPane, JTextField searchField, String object) {
        this.scrollPane = scrollPane;
        this.searchField = searchField;
        this.object = object;

        wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS)); 

        nt.setUpPanel(wrapperPanel, object);

        scrollPane.setViewportView(wrapperPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  

        placeHolder();
        setupSearchListener();
        startAutoRefresh();
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

    private void setupSearchListener() {
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

                wrapperPanel.removeAll();

                if (!keyword.isEmpty() && !keyword.equals("Nhập tiêu đề thông báo...")) {
                    nt.search(wrapperPanel, keyword);
                } else {
                    nt.setUpPanel(wrapperPanel, object);
                }

                wrapperPanel.revalidate();
                wrapperPanel.repaint();
            }
        });
    }

    private void startAutoRefresh() {
        autoRefreshTimer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (searchField.getText().trim().isEmpty() || searchField.getText().equals("Nhập tiêu đề thông báo...")) {
                    wrapperPanel.removeAll();
                    nt.setUpPanel(wrapperPanel, object);
                    wrapperPanel.revalidate();
                    wrapperPanel.repaint();
                }
            }
        });
        autoRefreshTimer.start();
    }
}
