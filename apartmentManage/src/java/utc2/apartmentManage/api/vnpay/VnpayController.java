package java.utc2.apartmentManage.api.vnpay;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/vnpay_jsp/vnpay_return.jsp")
public class VnpayController {

    @GetMapping
    public ResponseEntity<String> handlePaymentReturn(HttpServletRequest request, @RequestParam("billId") String billId) {
        Map<String, String> response = new HashMap<>();

        // Retrieve all parameters from VNPay response
        Map<String, String> vnpParams = new HashMap<>();
        for (String paramName : request.getParameterMap().keySet()) {
            String paramValue = request.getParameter(paramName);
            if (paramValue != null && !paramValue.isEmpty()) {
                vnpParams.put(paramName, paramValue);
            }
        }

        // Extract secure hash from response
        String vnpSecureHash = vnpParams.get("vnp_SecureHash");
        vnpParams.remove("vnp_SecureHash");

        // Generate hash data for verification
        StringBuilder hashData = new StringBuilder();
        vnpParams.keySet().stream()
                .sorted()
                .forEach(key -> {
                    String value = vnpParams.get(key);
                    if (value != null && !value.isEmpty()) {
                        try {
                            hashData.append(key)
                                    .append('=')
                                    .append(java.net.URLEncoder.encode(value, StandardCharsets.UTF_8.toString()));
                            hashData.append('&');
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Remove trailing '&' if present
        if (hashData.length() > 0 && hashData.charAt(hashData.length() - 1) == '&') {
            hashData.deleteCharAt(hashData.length() - 1);
        }

        // Verify secure hash
        String calculatedHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        boolean isValidHash = calculatedHash.equals(vnpSecureHash);

        // Prepare response
        if (isValidHash) {
            String responseCode = vnpParams.get("vnp_ResponseCode");
            if ("00".equals(responseCode)) {
                response.put("status", "success");
                response.put("message", "Payment successful for bill ID: " + billId);
                response.put("transactionId", vnpParams.get("vnp_TxnRef"));
                response.put("amount", vnpParams.get("vnp_Amount"));
                response.put("billId", billId);
            } else {
                response.put("status", "failed");
                response.put("message", "Payment failed with response code: " + responseCode);
                response.put("billId", billId);
            }
        } else {
            response.put("status", "error");
            response.put("message", "Invalid secure hash. Possible tampering detected.");
            response.put("billId", billId);
        }

        return ResponseEntity.ok("Success");
    }
}