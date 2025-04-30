package main.java.utc2.apartmentManage.controller.employeesControl;


import main.java.utc2.apartmentManage.view.Login.Login;
import main.java.utc2.apartmentManage.view.UserUI.Pages.Infomation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.java.utc2.apartmentManage.view.EmployeesUI.HomePageEmployee;
import main.java.utc2.apartmentManage.view.EmployeesUI.chamcongUI;


public class HomeHandler {
    private JPanel logoutPanel, chamcong, thongbao, mainPanel, thongtin;
    private HomePageEmployee homePage;
    private int employeeId;

    private JPanel previousPanel; // Lưu panel trước đó
    private final Color DEFAULT_COLOR = new Color(41,101,142);
    private final Color ACTIVE_COLOR = new Color(13,51,91);

    public HomeHandler(int employeeId, JPanel mainPan, JPanel logoutPanel, JPanel billPanel,JPanel infoPanel, JPanel mainPanel, HomePageEmployee homePage) {
        this.employeeId = employeeId;
        this.homePage = homePage;
        this.logoutPanel = logoutPanel;
        this.mainPanel = mainPanel;
        this.previousPanel = mainPan;
        this.chamcong = billPanel;
        this.thongbao = infoPanel;
        this.thongtin = mainPan;

        addClickEvent(infoPanel);
        addClickEvent(billPanel);
        addClickEvent(mainPan);
        

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
        if( newPanel.equals(chamcong) ) {
            chamcongUI report = new chamcongUI(employeeId);
            mainPanel.add(report, BorderLayout.CENTER);
        } 
        if (newPanel.equals(thongbao)) {
            Infomation report = new Infomation();
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
