package java.utc2.apartmentManage.controller.login;

import utc2.apartmentManage.model.Account;
import utc2.apartmentManage.service.implement.login.loginIMP;
import utc2.apartmentManage.view.employee.HomePageEmployeeUI;
import utc2.apartmentManage.view.login.RegisterUI;
import utc2.apartmentManage.view.manager.pages.*;
import utc2.apartmentManage.view.user.pages.HomePageUser;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class loginHandle {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox checkBox;
    private JButton loginButton;
    private JFrame loginFrame;
    private JLabel registerLabel;
    private loginIMP loginService = new loginIMP();

    public loginHandle(JTextField usernameField, JPasswordField passwordField, JCheckBox checkBox, JButton loginButton, JLabel registerLabel, JFrame loginFrame) {
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
                new HomePageEmployeeUI(acc).setVisible(true);
            }
            
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegisterForm() {
        loginFrame.setVisible(false);
        new RegisterUI().setVisible(true);
    }
}
