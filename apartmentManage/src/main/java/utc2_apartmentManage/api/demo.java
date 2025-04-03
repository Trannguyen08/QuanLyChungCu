
package main.java.utc2_apartmentManage.api;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class demo {
    public static void sendOTP(String recipient, String otp) {
        String host = "smtp.gmail.com";
        String from = "6451071052@st.utc2.edu.vn";
        String password = "kbgbmnybsflpnsqr"; // Dùng App Password

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Mã OTP của bạn");
            message.setText("Mã OTP của bạn là: " + otp);

            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendOTP("trannguyen08py@example.com", "123456");
    }
}
