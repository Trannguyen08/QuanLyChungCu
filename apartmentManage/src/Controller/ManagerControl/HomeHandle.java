package Controller.ManagerControl;

import View.Login.LoginForm_;
import View.ManagerUI.Apartment;
import View.ManagerUI.HomePage;
import View.ManagerUI.Report;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class HomeHandle {
    private JLabel logout, notification, report, residents, service, apartment, contracts, employees;
    private JPanel logoutPanel, notificationPanel, reportPanel, residentsPanel, servicePanel, apartmentPanel, contractsPanel, employeesPanel, mainPanel;
    private HomePage homePage;

    private JPanel previousPanel; // Lưu panel trước đó
    private final Color DEFAULT_COLOR = new Color(37,102,169);  // Màu mặc định
    private final Color ACTIVE_COLOR = new Color(13,51,91);

    public HomeHandle(JLabel apartment, JPanel apartmentPanel, JLabel contracts, JPanel contractsPanel,
                      JLabel employees, JPanel employeesPanel, JLabel logout, JPanel logoutPanel,
                      JLabel notification, JPanel notificationPanel, JLabel report, JPanel reportPanel,
                      JLabel residents, JPanel residentsPanel, JLabel service, JPanel servicePanel, JPanel mainPanel, HomePage homePage) {
        this.apartment = apartment;
        this.apartmentPanel = apartmentPanel;
        this.contracts = contracts;
        this.contractsPanel = contractsPanel;
        this.employees = employees;
        this.employeesPanel = employeesPanel;
        this.homePage = homePage;
        this.logout = logout;
        this.logoutPanel = logoutPanel;
        this.notification = notification;
        this.notificationPanel = notificationPanel;
        this.report = report;
        this.reportPanel = reportPanel;
        this.residents = residents;
        this.residentsPanel = residentsPanel;
        this.service = service;
        this.servicePanel = servicePanel;
        this.previousPanel = apartmentPanel;
        this.mainPanel = mainPanel;

        addClickEvent(apartment, apartmentPanel);
        addClickEvent(contracts, contractsPanel);
        addClickEvent(employees, employeesPanel);
        addClickEvent(notification, notificationPanel);
        addClickEvent(report, reportPanel);
        addClickEvent(residents, residentsPanel);
        addClickEvent(service, servicePanel);

        logout.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoutLabelClick();
            }
        });
    }


    private void addClickEvent(JLabel label, JPanel panel) {
        label.addMouseListener(new MouseAdapter() {
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
            Apartment apartment = new Apartment();
            mainPanel.add(apartment, BorderLayout.CENTER);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }


    private void logoutLabelClick() {
        homePage.setVisible(false);
        new LoginForm_().setVisible(true);
    }
    

    public static void main(String args[]) {
        // TODO code application logic here
    }
}
