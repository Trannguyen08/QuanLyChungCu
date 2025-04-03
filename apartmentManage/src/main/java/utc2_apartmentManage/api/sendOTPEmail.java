package main.java.utc2_apartmentManage.api;

import java.util.Random;
import okhttp3.*;

public class sendOTPEmail {
    private String FROM_EMAIL;
    private String API_KEY;
    private String DOMAIN;
    private String OTP;

    public sendOTPEmail(String FROM_EMAIL, String API_KEY, String DOMAIN) {
        this.FROM_EMAIL = FROM_EMAIL;
        this.API_KEY = API_KEY;
        this.DOMAIN = DOMAIN;
    }

    public String generateOTP() {
        Random rand = new Random();
        int otp = 100000 + rand.nextInt(900000);
        this.OTP = String.valueOf(otp);
        return this.OTP;
    }

    public boolean sendOtpEmail(String toEmail, String otpCode) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add("from", "OTP Service <" + FROM_EMAIL + ">")
                .add("to", toEmail)
                .add("subject", "Mã OTP đăng ký tài khoản")
                .add("text", "Mã OTP của bạn là: " + otpCode)
                .build();

        Request request = new Request.Builder()
                .url("https://api.mailgun.net/v3/" + DOMAIN + "/messages")
                .addHeader("Authorization", Credentials.basic("api", API_KEY))
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println("Response: " + response.body().string());
            return response.isSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getOTP() {
        return this.OTP;
    }

    
}
