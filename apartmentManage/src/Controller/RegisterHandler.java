package Controller;

import DatabaseConnect.ConnectDB;
import GUI.LoginForm_;
import GUI.RegisterForm;
import Util.ScannerUtil;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.sql.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;


public class RegisterHandler {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField repeatPasswordField;
    private JTextField emailField;
    private JButton registerButton;
    private JFrame registerFrame;
    private JLabel loginLabel;
    
    public RegisterHandler(JTextField usernameField, JPasswordField passwordField, JPasswordField repeatPasswordField,
                            JTextField emailField, JButton registerButton, JLabel loginLabel,JFrame registerFrame ){
        
        this.usernameField = usernameField;
        this.passwordField = passwordField;
        this.repeatPasswordField = repeatPasswordField;
        this.registerButton = registerButton;
        this.emailField = emailField;
        this.loginLabel = loginLabel;
        this.registerFrame = registerFrame;
        
        this.registerButton.addActionListener(e -> registerBtnClick());
        this.loginLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelLoginClick();
            }
        });
        
        // xử lí sự kiện gạch dưới khi hover
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
        if( username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || email.isEmpty() ) {
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
    
    // check user và email
    private boolean isUserExists(Connection con, String username, String email) throws SQLException {
        // check user
        String checkUsernameQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
        try (PreparedStatement stmt = con.prepareStatement(checkUsernameQuery)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if( rs.next() && rs.getInt(1) > 0 ) {
                    JOptionPane.showMessageDialog(registerFrame, "Tên người dùng đã tồn tại. Vui lòng chọn tên khác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return true;
                }
            }
        }

        // check email
        String checkEmailQuery = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement stmt = con.prepareStatement(checkEmailQuery)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if( rs.next() && rs.getInt(1) > 0 ) {
                    JOptionPane.showMessageDialog(registerFrame, "Email đã được sử dụng. Vui lòng chọn email khác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return true;
                }
            }
        }
        return false; 
    }
    
    // insert account
    private void insertUser(Connection con, String username, String hashedPassword, String email) throws SQLException {
        String query = "INSERT INTO users (username, password, email, role) VALUES(?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, email);
            stmt.setString(4, "user");
            stmt.executeUpdate();
        }
    }



    public void registerBtnClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String repeatPassword = repeatPasswordField.getText();
        String email = emailField.getText();

        if (!validateInput(username, password, repeatPassword, email)) {
            return;
        }

        try (Connection con = ConnectDB.getConnection()) {
            if (isUserExists(con, username, email)) {
                return; 
            }

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12)); // Mã hóa mật khẩu

            insertUser(con, username, hashedPassword, email);

            JOptionPane.showMessageDialog(registerFrame, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            registerFrame.setVisible(false);
            new LoginForm_().setVisible(true);

        } catch (SQLException ex) {
            Logger.getLogger(RegisterForm.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(registerFrame, "Đã xảy ra lỗi trong quá trình đăng ký!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

                               
                               

    public void labelLoginClick() {                                         
        new LoginForm_().setVisible(true);
        registerFrame.setVisible(false);
    } 
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
