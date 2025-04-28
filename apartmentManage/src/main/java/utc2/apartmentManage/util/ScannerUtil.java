package main.java.utc2.apartmentManage.util;

import com.toedter.calendar.JDateChooser;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
        from = from.replace(",", ".");
        to = to.trim();
        to = to.replace(",", ".");

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
            if(Integer.parseInt(input.trim()) < 0 ) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương hợp lệ cho " + fieldName, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên hợp lệ cho " + fieldName, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static double parseToDouble(String s) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault()); 
        Number number = null;
        try {
            number = format.parse(s);
        } catch (ParseException ex) {
            Logger.getLogger(ScannerUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return number.doubleValue();
    }

    public static boolean validateDouble(String input, String fieldName) {
        if( input == null || input.trim().isEmpty() ) {
            return true;
        }

        input = input.replace(",", ".");
        try {
            if(Double.parseDouble(input.trim()) < 0 ) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số thực dương hợp lệ cho " + fieldName, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " phải là số thực hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean spaceDouble(String input, double min, double max, String fieldName) {
        input = input.replace(",", ".");
        try {
            double value = Double.parseDouble(input);
            if (value < min || value > max) {
                JOptionPane.showMessageDialog(null,
                        fieldName + " phải nằm trong khoảng từ " + min + " đến " + max + "!",
                        "Lỗi nhập liệu",
                        JOptionPane.ERROR_MESSAGE);
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    fieldName + " phải là một số hợp lệ!",
                    "Lỗi nhập liệu",
                    JOptionPane.ERROR_MESSAGE);
            return true;
        }
    }

    public static boolean validateEmail(String email) {
        boolean isValid = email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
        if (!isValid) {
            JOptionPane.showMessageDialog(null,
                    "Email không hợp lệ: " + email,
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE
            );
        }
        return isValid;
    }

    public static boolean validateDateRange(Date fromDate, Date toDate, String fieldName) {
        boolean isValid = !fromDate.after(toDate);
        if (!isValid) {
            JOptionPane.showMessageDialog(null,
                    "Ngày bắt đầu không thể sau ngày kết thúc.",
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE
            );
        }
        return isValid;
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        boolean isValid = phoneNumber.matches("^0[0-9]{9}$");
        if (!isValid) {
            JOptionPane.showMessageDialog(null,
                    "Số điện thoại không hợp lệ: " + phoneNumber,
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE
            );
        }
        return isValid;
    }

    public static boolean isValidCCCD(String cccd) {
        boolean isValid = cccd.matches("\\d{12}"); 
        if (!isValid) {
            JOptionPane.showMessageDialog(null,
                    "CCCD không hợp lệ: " + cccd,
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE
            );
        }
        return isValid;
    }
    
    public static boolean isValidFullName(String fullName) {
         boolean isValid = fullName.matches("^[A-Za-zÀ-Ỹà-ỹ]+(\\s[A-Za-zÀ-Ỹà-ỹ]+)+$");
        if (!isValid) {
            JOptionPane.showMessageDialog(null,
                    "Họ và tên không hợp lệ",
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE
            );
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                return sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    
    public static String convertDateFormat1(String inputDate) {
        if (inputDate == null || inputDate.trim().isEmpty()) {
            return null; 
        }

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date date = inputFormat.parse(inputDate); 
            return outputFormat.format(date); 

        } catch (ParseException e) {
            return null;
        }
    }
    
    public static String convertDateFormat2(String inputDate) {
        if (inputDate == null || inputDate.trim().isEmpty()) {
            return null; 
        }

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = inputFormat.parse(inputDate); 
            return outputFormat.format(date); 

        } catch (ParseException e) {
            return null;
        }
    }
    
    public static void setDateChooserFromString(JDateChooser dateChooser, String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
        try {
            Date date = dateFormat.parse(dateStr); 
            dateChooser.setDate(date); 
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Lỗi: Không thể chuyển đổi chuỗi thành ngày tháng.");
        }
    }
    
    public static boolean comfirmWindow(String s) {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa " + s + " này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }
    
    public static double replaceDouble(JTextField value) {
        String valStr = value.getText().trim();
        valStr = valStr.replace(",", ".");
        return Double.parseDouble(valStr);
    }
    
    public static String convertDateToString(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }


}
