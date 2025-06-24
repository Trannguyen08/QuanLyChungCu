package java.utc2.apartmentManage.controller.login;

import utc2.apartmentManage.service.implement.login.registerIMP;
import utc2.apartmentManage.view.login.LoginUI;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class registerHandle {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JTextField emailField;
    private JTextField phoneNum;
    private JButton registerButton;
    private JLabel loginLabel;
    private JFrame registerFrame;
    private registerIMP registerService = new registerIMP();

    public registerHandle(JTextField usernameField, JPasswordField passwordField,
                          JPasswordField repeatPasswordField, JTextField emailField,
                          JTextField phoneNum, JButton registerButton, JLabel loginLabel, JFrame registerFrame) {

        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.repeatPasswordField = repeatPasswordField;
        this.emailField = emailField;
        this.phoneNum = phoneNum;
        this.registerButton = registerButton;
        this.loginLabel = loginLabel;
        this.registerFrame = registerFrame;
        this.registerButton.addActionListener(e -> registerBtnClick());
        this.loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLoginClick();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Font font = loginLabel.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                loginLabel.setFont(font.deriveFont(attributes));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginLabel.setFont(new Font("Arial", Font.PLAIN, 15));
            }
        });
    }

    private void registerBtnClick() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String repeatPassword = new String(repeatPasswordField.getPassword());
        String email = emailField.getText();
        String phone = phoneNum.getText();

        boolean success = registerService.registerUser(username, password, repeatPassword, email, phone, registerFrame);
        if (success) {
            registerFrame.setVisible(false);
            new LoginUI().setVisible(true);
        }
    }

    private void labelLoginClick() {
        new LoginUI().setVisible(true);
        registerFrame.setVisible(false);
    }
}
