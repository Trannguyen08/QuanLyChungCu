package main.java.utc2.apartmentManage.controller.LoginControl;

import org.mindrot.jbcrypt.BCrypt;

public class HashPassword {
    public static void main(String args[]) {
         

        // Mã hóa mật khẩu bằng BCrypt
        String hashedPassword1 = BCrypt.hashpw("123456", BCrypt.gensalt(12));
        
        System.out.println("Admin1: " + hashedPassword1);
        
    }
}
