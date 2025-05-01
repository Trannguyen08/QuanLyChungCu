package main.java.utc2.apartmentManage.controller.UserControl.HomeController;


import main.java.utc2.apartmentManage.view.Login.Login;
import main.java.utc2.apartmentManage.view.UserUI.Pages.Bill;
import main.java.utc2.apartmentManage.view.UserUI.Pages.HomePageUser;
import main.java.utc2.apartmentManage.view.UserUI.Pages.InfomationUserUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.view.UserUI.Pages.NotificationUserUI;


public class HomeHandler {
    private JPanel logoutPanel, billPanel, infoPanel, mainPanel, notiPanel;
    private HomePageUser homePage;
    private Account acc;
    private JPanel previousPanel; // Lưu panel trước đó
    private final Color DEFAULT_COLOR = new Color(41,101,142);
    private final Color ACTIVE_COLOR = new Color(13,51,91);

    public HomeHandler(JPanel infoPanel, JPanel billPanel, JPanel notiPanel, JPanel logoutPanel, 
                        JPanel mainPanel, HomePageUser homePage, Account acc) {
        this.homePage = homePage;
        this.logoutPanel = logoutPanel;
        this.mainPanel = mainPanel;
        this.previousPanel = infoPanel;
        this.billPanel = billPanel;
        this.infoPanel = infoPanel;
        this.notiPanel = notiPanel;
        this.acc = acc;

        addClickEvent(infoPanel);
        addClickEvent(billPanel);
        addClickEvent(notiPanel);
        

        logoutPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoutLabelClick();
            }
        });
    }


    private void addClickEvent(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(panel);
            }
        });
    }

    private void changePanel(JPanel newPanel) {
        if( previousPanel != null ) {
            previousPanel.setBackground(DEFAULT_COLOR);
        }
        newPanel.setBackground(ACTIVE_COLOR);
        previousPanel = newPanel;
        mainPanel.removeAll();
        mainPanel.setLayout(new BorderLayout());
        if( newPanel.equals(billPanel) ) {
            Bill report = new Bill(acc);
            mainPanel.add(report, BorderLayout.CENTER);
        } 
        if (newPanel.equals(infoPanel)) {
            InfomationUserUI report = new InfomationUserUI(acc);
            mainPanel.add(report, BorderLayout.CENTER);
        }
        if (newPanel.equals(notiPanel)) {
            NotificationUserUI report = new NotificationUserUI();
            mainPanel.add(report, BorderLayout.CENTER);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void logoutLabelClick() {
        homePage.setVisible(false);
        new Login().setVisible(true);
    }
}
