package Controller.LoginControl;

import Model.LoginDAO.RegisterSQL;
import View.Login.LoginForm_;
import Util.ScannerUtil;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class RegisterHandler {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JFrame registerFrame;
    private JLabel loginLabel;
    
    public RegisterHandler(JTextField usernameField, JPasswordField passwordField, JPasswordField repeatPasswordField,
                            JTextField emailField, JButton registerButton, JLabel loginLabel, JFrame registerFrame) {
        
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.repeatPasswordField = repeatPasswordField;
        this.registerButton = registerButton;
        this.emailField = emailField;
        this.loginLabel = loginLabel;
        this.registerFrame = registerFrame;
        
        this.registerButton.addActionListener(e -> registerBtnClick());
        this.loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLoginClick();
            }
        });
        
        // Xử lý sự kiện gạch dưới khi hover
        loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
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
    
    public boolean isValidEmail(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    private boolean validateInput(String username, String password, String repeatPassword, String email) {
        if( username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || email.isEmpty( )) {
            JOptionPane.showMessageDialog(registerFrame, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if( !ScannerUtil.isValidUsername(username) ) {
            return false;
        }
        if( !ScannerUtil.isValidPassword(password) ) {
            return false;
        }
        if( !password.equals(repeatPassword) ) {
            JOptionPane.showMessageDialog(registerFrame, "Bạn đã nhập lại mật khẩu sai. Vui lòng nhập lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if( !isValidEmail(email) ) {
            JOptionPane.showMessageDialog(registerFrame, "Bạn đã nhập sai định dạng email. Vui lòng nhập lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public void registerBtnClick() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String repeatPassword = new String(repeatPasswordField.getPassword());
        String email = emailField.getText();
        int checkExist = RegisterSQL.isUserExists(username, email);

        if( !validateInput(username, password, repeatPassword, email) ) {
            return;
        }

        if( checkExist == 1 ) {
            JOptionPane.showMessageDialog(registerFrame, "Tên người dùng đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } else if( checkExist == 2 ){
            JOptionPane.showMessageDialog(registerFrame, "Email đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean isRegistered = RegisterSQL.insertUser(username, password, email);
        if (isRegistered) {
            JOptionPane.showMessageDialog(registerFrame, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            registerFrame.setVisible(false);
            new LoginForm_().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(registerFrame, "Đã xảy ra lỗi trong quá trình đăng ký!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }                     

    public void labelLoginClick() {                                         
        new LoginForm_().setVisible(true);
        registerFrame.setVisible(false);
    } 
}
