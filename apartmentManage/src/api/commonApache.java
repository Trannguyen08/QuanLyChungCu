package api;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class commonApache {
    public static void main(String[] args) {
        String recipientEmail = "trannguyen08py@gmail.com"; // Email người nhận
        String senderEmail = "6451071052@st.utc2.edu.vn"; // Email người gửi
        String senderPassword = "kbgbmnybsflpnsqr"; // App Password của Gmail
        String smtpHost = "smtp.gmail.com"; // SMTP Server của Gmail
        int smtpPort = 587; // Cổng SMTP

        String otp = generateOTP(); // Tạo OTP

        try {
            // Tạo đối tượng email
            SimpleEmail email = new SimpleEmail();
            email.setHostName(smtpHost); // Cấu hình SMTP Server
            email.setSmtpPort(smtpPort); // Cấu hình cổng SMTP
            email.setAuthentication(senderEmail, senderPassword); // Xác thực tài khoản
            email.setStartTLSRequired(true); // Bật TLS để bảo mật
            email.setFrom(senderEmail); // Thiết lập email người gửi
            email.setSubject("Your OTP Code"); // Chủ đề email
            email.setMsg("Your OTP is: " + otp); // Nội dung email
            email.addTo(recipientEmail); // Thêm email người nhận
            email.send(); // Gửi email

            System.out.println("OTP sent successfully to " + recipientEmail);
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    // Hàm tạo OTP ngẫu nhiên (6 chữ số)
    private static String generateOTP() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
