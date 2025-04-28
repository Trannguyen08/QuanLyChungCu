package main.java.utc2.apartmentManage.service.loginService;

import java.util.List;
import javax.swing.JPasswordField;
import main.java.utc2.apartmentManage.model.Account;
import main.java.utc2.apartmentManage.repository.LoginRepository.loginRepository;

public class loginIMP {
    private loginRepository lr = new loginRepository();

    public boolean validateLogin(String username, String password) {
        return lr.validateLogin(username, password);
    }

    public void togglePasswordVisibility(JPasswordField passwordField, boolean isSelected) {
        if (isSelected) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('\u2022');
        }
    }
    
    public List<Account> gettAllAccount() {
        return lr.getAllAccount();
    }
}
