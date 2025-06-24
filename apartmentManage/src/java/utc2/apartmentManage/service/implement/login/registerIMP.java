package java.utc2.apartmentManage.service.implement.login;

import utc2.apartmentManage.model.Account;
import utc2.apartmentManage.repository.login.registerRepository;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;


public class registerIMP {
    private registerRepository registerRepo = new registerRepository();

    public boolean registerUser(String username, String password, String repeatPassword, String email, String phone, JFrame frame) {
        if (!validateInput(username, password, repeatPassword, email, phone, frame)) {
            return false;
        }
        int id = registerRepo.getIDMinNotExist();
        Account acc = new Account(id, username, password, email, phone, "customer");

        int checkExist = registerRepository.isUserExists(acc);

        switch (checkExist) {
            case 1 -> {
                JOptionPane.showMessageDialog(frame, "Tên người dùng đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 2 -> {
                JOptionPane.showMessageDialog(frame, "Email đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            case 3 -> {
                JOptionPane.showMessageDialog(frame, "Số điện thoại đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            default -> {
            }
        }

        boolean isRegistered = registerRepo.insertUser(acc);

        if (isRegistered) {
            JOptionPane.showMessageDialog(frame, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(frame, "Đã xảy ra lỗi trong quá trình đăng ký!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean validateInput(String username, String password, String repeatPassword, String email, String phone, JFrame frame) {
        if (username.isEmpty() || password.isEmpty() || repeatPassword.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!ScannerUtil.isValidUsername(username)) {
            return false;
        }
        if (!ScannerUtil.isValidPassword(password)) {
            return false;
        }
        if (!password.equals(repeatPassword)) {
            JOptionPane.showMessageDialog(frame, "Bạn đã nhập lại mật khẩu sai. Vui lòng nhập lại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!ScannerUtil.validateEmail(email)) {          
            return false;
        }
        return ScannerUtil.validatePhoneNumber(phone);
    }
    
    public int getNewID() {
        return registerRepo.getIDMinNotExist();
    }
    
    public boolean addAccount(Account acc) {
        return registerRepo.insertUser(acc);
    }

}
