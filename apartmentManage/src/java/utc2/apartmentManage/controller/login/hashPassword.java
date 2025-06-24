package java.utc2.apartmentManage.controller.login;

import org.mindrot.jbcrypt.BCrypt;

public class hashPassword {
    public static void main(String args[]) {
        String hashedPassword1 = BCrypt.hashpw("123456", BCrypt.gensalt(12));
        System.out.println("Admin1: " + hashedPassword1);
    }
}
