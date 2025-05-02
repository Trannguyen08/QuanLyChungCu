package main.java.utc2.apartmentManage.controller.LoginControl;


import main.java.utc2.apartmentManage.view.Login.Register;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.service.loginService.loginIMP;
import main.java.utc2.apartmentManage.view.EmployeesUI.HomePageEmployee;
import main.java.utc2.apartmentManage.view.ManagerUI.Pages.HomePage;
import main.java.utc2.apartmentManage.view.UserUI.Pages.HomePageUser;


public class LoginHandler {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox checkBox;
    private JButton loginButton;
    private JFrame loginFrame;
    private JLabel registerLabel;
    private loginIMP loginService = new loginIMP();

    public LoginHandler(JTextField usernameField, JPasswordField passwordField, JCheckBox checkBox, JButton loginButton, JLabel registerLabel, JFrame loginFrame) {
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.checkBox = checkBox;
        this.loginButton = loginButton;
        this.registerLabel = registerLabel;
        this.loginFrame = loginFrame;
        this.checkBox.addActionListener(e -> clickCheckBox());
        this.loginButton.addActionListener(e -> handleLogin());
        this.registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openRegisterForm();
            }
        });

        registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Font font = registerLabel.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                registerLabel.setFont(font.deriveFont(attributes));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });
    }

    private void clickCheckBox() {
        loginService.togglePasswordVisibility(passwordField, checkBox.isSelected());
    }

    private void handleLogin() {
        String thisUser = usernameField.getText();
        String thisPass = new String(passwordField.getPassword());
        if (thisUser.isEmpty() || thisPass.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (loginService.validateLogin(thisUser, thisPass)) {
            JOptionPane.showMessageDialog(loginFrame, "Đăng nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loginFrame.setVisible(false);
            Account acc = loginService.getAccountByUserName(thisUser);
            if( acc.getRole().equals("manager") ) {
                new HomePage(acc).setVisible(true);
            } else if( acc.getRole().equals("user") ) {
                new HomePageUser(acc).setVisible(true);
            } else if( acc.getRole().equals("employee") ) {
                new HomePageEmployee(acc).setVisible(true);
            }
            
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegisterForm() {
        loginFrame.setVisible(false);
        new Register().setVisible(true);
    }
}
