package main.java.com.utc2.apartmentManage.controller.ManagerControl.HomeController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import main.java.com.utc2.apartmentManage.view.Login.Login;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.ApartmentUI;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.ContractUI;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.Employee;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.HomePage;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.NotificationForm;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.Report;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.Resident;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.ServicesForm;


public class HomeHandler {
    private JPanel logoutPanel, notificationPanel, reportPanel, residentsPanel, servicePanel, apartmentPanel, contractsPanel, employeesPanel, mainPanel;
    private HomePage homePage;

    private JPanel previousPanel; // Lưu panel trước đó
    private final Color DEFAULT_COLOR = new Color(41,101,142);  // Màu mặc định
    private final Color ACTIVE_COLOR = new Color(13,51,91);

    public HomeHandler(JPanel apartmentPanel, JPanel contractsPanel,JPanel employeesPanel, 
                      JPanel logoutPanel,JPanel notificationPanel, JPanel reportPanel,
                      JPanel residentsPanel, JPanel servicePanel, JPanel mainPanel, HomePage homePage) {
        this.apartmentPanel = apartmentPanel;
        this.contractsPanel = contractsPanel;
        this.employeesPanel = employeesPanel;
        this.homePage = homePage;
        this.logoutPanel = logoutPanel;
        this.notificationPanel = notificationPanel;
        this.reportPanel = reportPanel;
        this.residentsPanel = residentsPanel;
        this.servicePanel = servicePanel;
        this.previousPanel = apartmentPanel;
        this.mainPanel = mainPanel;

        addClickEvent(apartmentPanel);
        addClickEvent(contractsPanel);
        addClickEvent(employeesPanel);
        addClickEvent(notificationPanel);
        addClickEvent(reportPanel);
        addClickEvent(residentsPanel);
        addClickEvent(servicePanel);

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
        if( newPanel.equals(reportPanel) ) {
            Report report = new Report();
            mainPanel.add(report, BorderLayout.CENTER);
        } else if( newPanel.equals(apartmentPanel) ) {
            ApartmentUI apartment = new ApartmentUI();
            mainPanel.add(apartment, BorderLayout.CENTER);
        } else if( newPanel.equals(residentsPanel) ) {
            Resident resident = new Resident();
            mainPanel.add(resident, BorderLayout.CENTER);
        } else if( newPanel.equals(employeesPanel) ) {
            Employee employee = new Employee();
            mainPanel.add(employee, BorderLayout.CENTER);
        } else if( newPanel.equals(contractsPanel) ) {
            ContractUI contract = new ContractUI();
            mainPanel.add(contract, BorderLayout.CENTER);
        } else if( newPanel.equals(notificationPanel) ) {
            NotificationForm noti = new NotificationForm();
            mainPanel.add(noti, BorderLayout.CENTER);
        } else if( newPanel.equals(servicePanel) ) {
            ServicesForm noti = new ServicesForm();
            mainPanel.add(noti, BorderLayout.CENTER);
        } 
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void logoutLabelClick() {
        homePage.setVisible(false);
        new Login().setVisible(true);
    }
}
