package Controller;

import DatabaseConnect.ConnectDB;
import GUI.RegisterForm;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.sql.*;
import java.util.Map;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;

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

        // Gán sự kiện cho các thành phần giao diện
        this.checkBox.addActionListener(e -> clickCheckBox());
        this.loginButton.addActionListener(e -> handleLogin());
        this.registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                openRegisterForm();
            }
        });
        
        // xử lí sự kiện gạch dưới khi hover
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

        if (thisUser.isEmpty() || thisPass.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, thisUser);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                String hashedPassword = res.getString("password");

                // Kiểm tra mật khẩu với BCrypt
                if (BCrypt.checkpw(thisPass, hashedPassword)) {
                    JOptionPane.showMessageDialog(loginFrame, "Đăng nhập thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loginFrame.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void openRegisterForm() {
        loginFrame.setVisible(false);
        new RegisterForm().setVisible(true);
    }
}

