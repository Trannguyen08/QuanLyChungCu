package Util;

import javax.swing.JOptionPane;

public class ScannerUtil {
    
    public static boolean isValidUsername(String username) {
        if( username.length() < 8 || username.length() > 25 ) {
            JOptionPane.showMessageDialog(null, "Tên người dùng phải từ 8 đến 25 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean isValidPassword(String password) {
        if( password.length() < 8 || password.length() > 25 ) {
            JOptionPane.showMessageDialog(null, "Mật khẩu phải từ 8 đến 25 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validateRange(String from, String to, String fieldName) {
        if( from == null || to == null ) {
            return true;
        }
        from = from.trim();
        to = to.trim();
        if( from.isEmpty() && !to.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị từ cho " + fieldName, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if( !from.isEmpty() && to.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị đến cho " + fieldName, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if( !from.isEmpty() && !to.isEmpty() ) {
            try {
                double fromValue = Double.parseDouble(from);
                double toValue = Double.parseDouble(to);

                if (fromValue > toValue) {
                    JOptionPane.showMessageDialog(null, fieldName + " 'Từ' không được lớn hơn 'Đến'", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Giá trị nhập vào không hợp lệ cho " + fieldName, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }

    public static boolean validateInteger(String input, String fieldName) {
        if( input == null || input.trim().isEmpty() ) {
            return true;
        }

        try {
            Integer.parseInt(input.trim());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên hợp lệ cho " + fieldName, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean validateDouble(String input, String fieldName) {
        if( input == null || input.trim().isEmpty() ) {
            return true;
        }

        try {
            Double.parseDouble(input.trim());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " phải là số thực hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean spaceDouble(String input, double min, double max, String fieldName) {
        try {
            double value = Double.parseDouble(input);
            if (value < min || value > max) {
                JOptionPane.showMessageDialog(null,
                        fieldName + " phải nằm trong khoảng từ " + min + " đến " + max + "!",
                        "Lỗi nhập liệu",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    fieldName + " phải là một số hợp lệ!",
                    "Lỗi nhập liệu",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }



}
