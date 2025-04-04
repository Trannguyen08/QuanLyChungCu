package main.java.utc2_apartmentManage.controller.UserControl.HomeController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import main.java.utc2_apartmentManage.view.Login.Login;
import main.java.utc2_apartmentManage.view.UserUI.Pages.Bill;
import main.java.utc2_apartmentManage.view.UserUI.Pages.HomePageUser;



public class HomeHandler {
    private JPanel logoutPanel, billPanel, infoPanel, mainPanel, mainPan;
    private HomePageUser homePage;

    private JPanel previousPanel; // Lưu panel trước đó
    private final Color DEFAULT_COLOR = new Color(41,101,142); 
    private final Color ACTIVE_COLOR = new Color(13,51,91);

    public HomeHandler(JPanel mainPan, JPanel logoutPanel, JPanel billPanel,JPanel infoPanel, JPanel mainPanel, HomePageUser homePage) {
        this.homePage = homePage;
        this.logoutPanel = logoutPanel;
        this.mainPanel = mainPan;
        this.previousPanel = mainPanel;
        this.billPanel = billPanel;
        this.infoPanel = infoPanel;
        this.mainPan = mainPan;

        addClickEvent(infoPanel);
        addClickEvent(billPanel);
        addClickEvent(mainPanel);
        

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
            Bill report = new Bill();
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
