package main.java.utc2.apartmentManage.controller.ManagerControl.ReportControl;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.EmployeeReportUI;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.FinanceUI;

public class reportHandle {
    private JPanel finance, employee, mainPanel;

    public reportHandle(JPanel finance, JPanel employee, JPanel mainPanel) {
        this.finance = finance;
        this.employee = employee;
        this.mainPanel = mainPanel;
        
        
        this.finance.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.removeAll();
                mainPanel.setLayout(new BorderLayout());
                FinanceUI fi = new FinanceUI(mainPanel);
                mainPanel.add(fi, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        
        this.employee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.removeAll();
                mainPanel.setLayout(new BorderLayout());
                EmployeeReportUI emp = new EmployeeReportUI(mainPanel);
                mainPanel.add(emp, BorderLayout.CENTER);
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
    
    }
    
    
}
