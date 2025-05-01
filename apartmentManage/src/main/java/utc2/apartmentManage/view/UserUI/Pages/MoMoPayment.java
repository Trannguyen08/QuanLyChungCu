package main.java.utc2.apartmentManage.view.UserUI.Pages;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MoMoPayment {

    public static String createPaymentAndGetQRCodeUrl() throws Exception {
        String endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
        String partnerCode = "MOMO";
        String accessKey = "F8BBA842ECF85";
        String secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";

        String requestId = UUID.randomUUID().toString();
        String orderId = UUID.randomUUID().toString();
        String orderInfo = "Thanh toán đơn hàng XYZ";
        String redirectUrl = "https://dummy-redirect.com";
        String ipnUrl = "https://dummy-ipn.com";
        String amount = "10000";

        JSONObject body = new JSONObject();
        body.put("partnerCode", partnerCode);
        body.put("accessKey", accessKey);
        body.put("requestId", requestId);
        body.put("amount", amount);
        body.put("orderId", orderId);
        body.put("orderInfo", orderInfo);
        body.put("redirectUrl", redirectUrl);
        body.put("ipnUrl", ipnUrl);
        body.put("requestType", "captureWallet");
        body.put("extraData", "");
        body.put("lang", "vi");

        String rawHash = "accessKey=" + accessKey +
                "&amount=" + amount +
                "&extraData=" +
                "&ipnUrl=" + ipnUrl +
                "&orderId=" + orderId +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + partnerCode +
                "&redirectUrl=" + redirectUrl +
                "&requestId=" + requestId +
                "&requestType=captureWallet";

        String signature = hmacSHA256(rawHash, secretKey);
        body.put("signature", signature);

        URL url = new URL(endpoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        OutputStream os = conn.getOutputStream();
        os.write(body.toString().getBytes());
        os.flush();
        os.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            response.append(line);
        }
        br.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        return jsonResponse.getString("payUrl");
    }

    public static BufferedImage generateQR(String text, int width, int height) throws Exception {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public static String hmacSHA256(String data, String secretKey) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);
        byte[] hash = sha256_HMAC.doFinal(data.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}
