package main.java.utc2.apartmentManage.controller.employeesControl;


import main.java.utc2.apartmentManage.view.Login.Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.view.EmployeesUI.HomePageEmployee;
import main.java.utc2.apartmentManage.view.EmployeesUI.InfomationEmployeeUI;
import main.java.utc2.apartmentManage.view.EmployeesUI.chamcongUI;
import main.java.utc2.apartmentManage.view.UserUI.Pages.NotificationUserUI;


public class HomeHandler {
    private JPanel logoutPanel, chamcong, thongbao, mainPanel, thongtin;
    private HomePageEmployee homePage;
    private int employeeId;
    private Account acc;
    private JPanel previousPanel; // Lưu panel trước đó
    private final Color DEFAULT_COLOR = new Color(41,101,142);
    private final Color ACTIVE_COLOR = new Color(13,51,91);

    public HomeHandler(JPanel thongtin, JPanel logoutPanel, JPanel chamcong, JPanel thongbao, 
                        JPanel mainPanel, HomePageEmployee homePage, Account acc) {
        
        this.homePage = homePage;
        this.logoutPanel = logoutPanel;
        this.mainPanel = mainPanel;
        this.previousPanel = thongtin;
        this.chamcong = chamcong;
        this.thongbao = thongbao;
        this.thongtin = thongtin;
        this.acc = acc;

        addClickEvent(this.thongtin);
        addClickEvent(this.thongbao);
        addClickEvent(this.chamcong);
        

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
            chamcongUI report = new chamcongUI(acc);
            mainPanel.add(report, BorderLayout.CENTER);
        } else if( newPanel.equals(thongtin) ) {
            InfomationEmployeeUI report = new InfomationEmployeeUI(acc);
            mainPanel.add(report, BorderLayout.CENTER);
        } else if( newPanel.equals(thongbao) ) {
            NotificationUserUI report = new NotificationUserUI("Nhân viên");
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
