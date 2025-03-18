package Controller.LoginControl;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    public static void main(String args[]) {
         // Danh sách mật khẩu gốc
        String password1 = "admin1pass";  
        String password2 = "admin2pass"; 
        String password3 = "admin3pass"; 

        // Mã hóa mật khẩu bằng BCrypt
        String hashedPassword1 = BCrypt.hashpw(password1, BCrypt.gensalt(12));
        String hashedPassword2 = BCrypt.hashpw(password2, BCrypt.gensalt(12));
        String hashedPassword3 = BCrypt.hashpw(password3, BCrypt.gensalt(12));

        // In ra mật khẩu đã mã hóa
        System.out.println("Admin1: " + hashedPassword1);
        System.out.println("Admin2: " + hashedPassword2);
        System.out.println("Admin3: " + hashedPassword3);
    }
}
