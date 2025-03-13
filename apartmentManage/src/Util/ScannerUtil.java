package Util;

import javax.swing.JOptionPane;

public class ScannerUtil {
    
   public static boolean isValidUsername(String username) {
        if (username.length() < 8 || username.length() > 25) {
            JOptionPane.showMessageDialog(null, "Tên người dùng phải từ 8 đến 25 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 25) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải từ 8 đến 25 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
}
