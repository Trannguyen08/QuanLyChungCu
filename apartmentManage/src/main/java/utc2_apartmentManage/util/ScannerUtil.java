package main.java.utc2_apartmentManage.util;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
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
            Double.valueOf(input.trim());
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

    public static boolean validateEmail(String email) {
        boolean isValid = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ: " + email, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    public static boolean validateDateRange(Date fromDate, Date toDate, String fieldName) {
        boolean isValid = !fromDate.after(toDate);
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Ngày bắt đầu không thể sau ngày kết thúc.", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        boolean isValid = phoneNumber.matches("^0[0-9]{9}$");
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ: " + phoneNumber, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }

    public static boolean isValidCCCD(String cccd) {
        boolean isValid = cccd.matches("\\d{12}"); 
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "CCCD không hợp lệ: " + cccd, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }
    
    public static boolean isValidFullName(String fullName) {
         boolean isValid = fullName.matches("^[A-Za-zÀ-Ỹà-ỹ]+(\\s[A-Za-zÀ-Ỹà-ỹ]+)+$");
        if (!isValid) {
            JOptionPane.showMessageDialog(null, "Họ và tên không hợp lệ", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
        }
        return isValid;
    }
    
    public static boolean isValidAge(Date selectedDate) {
        LocalDate birthDate = selectedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now(); 
        
        // Tính tuổi
        int age = Period.between(birthDate, currentDate).getYears();
        
        return age >= 18;
    }
    
    public static String convertJDateChooserToString(JDateChooser dateChooser) {
        Date date = dateChooser.getDate();
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    
    public static String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return (date != null) ? sdf.format(date) : null;    
    }

}
