package java.utc2.apartmentManage.service.implement.login;

import utc2.apartmentManage.model.Account;
import utc2.apartmentManage.repository.login.loginRepository;

import javax.swing.*;
import java.util.List;


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
    
    public Account getAccountByUserName(String username) {
        return lr.getAccountByUsername(username);
    }
}
