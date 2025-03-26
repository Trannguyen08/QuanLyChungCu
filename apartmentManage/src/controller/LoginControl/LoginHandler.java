package controller.LoginControl;

import dao.loginDAO.LoginDAO;
import view.Login.Register;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;

public class LoginHandler {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox checkBox;
    private JButton loginButton;
    private JFrame loginFrame;
    private JLabel registerLabel;

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
        if (checkBox.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('*');
        }
    }

    private void handleLogin() {
        String thisUser = usernameField.getText();
        String thisPass = new String(passwordField.getPassword());
        if( thisUser.isEmpty() || thisPass.isEmpty() ) {
            JOptionPane.showMessageDialog(loginFrame, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if( LoginDAO.validateLogin(thisUser, thisPass) ) {
            JOptionPane.showMessageDialog(loginFrame, "Đăng nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loginFrame.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openRegisterForm() {
        loginFrame.setVisible(false);
        new Register().setVisible(true);
    }
}
