package java.utc2.apartmentManage.api.mail;


import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Date;
import java.util.Properties;

public class SendMail {
    // Email: tungletest1.email@gmail.com
    // Password: nebeekfipcstxcox
    static final String from = "trannguyen08py@gmail.com";
    static final String password = "vqgromucizuigbsi";

    public static boolean sendEmail(String to, String tieuDe, String noiDung) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject(tieuDe);

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung
            msg.setContent(noiDung, "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    public void send() {
        String subject = "Thông báo quá hạn thanh toán hóa đơn";
        String content = "<html><body style='font-family: Arial;'>"
                + "<p>Quý cư dân thân mến,</p>"
                + "<p>Hóa đơn tháng này của quý cư dân đã quá hạn thanh toán vào ngày 20/06/2025.</p>"
                + "<p>Vui lòng hoàn tất thanh toán trong thời gian sớm nhất để tránh gián đoạn dịch vụ.</p>"
                + "<p>Trân trọng,<br>Ban quản lý chung cư</p>"
                + "</body></html>";

        boolean result = SendMail.sendEmail("6451071052@st.utc2.edu.vn", subject, content);

        if (result) {
            System.out.println("Đã gửi thành công.");
        } else {
            System.out.println("Gửi thất bại.");
        }
    }

}
