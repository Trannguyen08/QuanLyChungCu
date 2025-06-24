package java.utc2.apartmentManage.controller.user.home;

import utc2.apartmentManage.model.Account;
import utc2.apartmentManage.view.login.LoginUI;
import utc2.apartmentManage.view.user.pages.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class homeHandle {
    private JPanel logoutPanel, billPanel, infoPanel, mainPanel, notiPanel, registerPanel;
    private HomePageUser homePage;
    private Account acc;
    private JPanel previousPanel; // Lưu panel trước đó
    private final Color DEFAULT_COLOR = new Color(41,101,142);
    private final Color ACTIVE_COLOR = new Color(13,51,91);

    public homeHandle(JPanel infoPanel, JPanel billPanel, JPanel notiPanel, JPanel logoutPanel,
                      JPanel mainPanel, JPanel registerPanel, HomePageUser homePage, Account acc) {
        this.homePage = homePage;
        this.logoutPanel = logoutPanel;
        this.mainPanel = mainPanel;
        this.previousPanel = infoPanel;
        this.billPanel = billPanel;
        this.infoPanel = infoPanel;
        this.notiPanel = notiPanel;
        this.registerPanel = registerPanel;
        this.acc = acc;

        addClickEvent(infoPanel);
        addClickEvent(billPanel);
        addClickEvent(notiPanel);
        addClickEvent(registerPanel);
        

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
            BillUserUI report = new BillUserUI(acc);
            mainPanel.add(report, BorderLayout.CENTER);
        } 
        if (newPanel.equals(infoPanel)) {
            InfomationUserUI report = new InfomationUserUI(acc);
            mainPanel.add(report, BorderLayout.CENTER);
        }
        if (newPanel.equals(notiPanel)) {
            NotificationUserUI report = new NotificationUserUI("Cư dân");
            mainPanel.add(report, BorderLayout.CENTER);
        }
        if (newPanel.equals(registerPanel)) {
            ServiceUserUI report = new ServiceUserUI(acc);
            mainPanel.add(report, BorderLayout.CENTER);
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void logoutLabelClick() {
        homePage.setVisible(false);
        new LoginUI().setVisible(true);
    }
}
