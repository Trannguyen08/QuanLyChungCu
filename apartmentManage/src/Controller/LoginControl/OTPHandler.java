package Controller.LoginControl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;
import javax.swing.text.*;


public class OTPHandler {
    private JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6;
    private JButton accept;
    private String otp;
    private AtomicBoolean checkOtp;

    public OTPHandler(JTextField jTextField1, JTextField jTextField2, JTextField jTextField3, JTextField jTextField4,
                      JTextField jTextField5, JTextField jTextField6, JButton accept, String otp, AtomicBoolean checkOtp) {
        this.jTextField1 = jTextField1;
        this.jTextField2 = jTextField2;
        this.jTextField3 = jTextField3;
        this.jTextField4 = jTextField4;
        this.jTextField5 = jTextField5;
        this.jTextField6 = jTextField6;
        this.accept = accept;
        this.otp = otp;
        this.checkOtp = checkOtp;

        this.accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                accepBtnClick();
            }
        });
    }


    public void setupOTPFields() {
        JTextField[] otpFields = {jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6};

        for( int i = 0 ; i < otpFields.length ; i++ ) {
            JTextField field = otpFields[i];
            field.setHorizontalAlignment(JTextField.CENTER);

            ((AbstractDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                        throws BadLocationException {
                    if (text.length() == 1 && fb.getDocument().getLength() == 0) {
                        super.replace(fb, offset, length, text, attrs);
                        // Chỉ di chuyển khi nhập thành công một ký tự và không có văn bản dư
                        SwingUtilities.invokeLater(() -> {
                            if (field.getText().length() == 1) {
                                moveToNextField(field, otpFields);
                            }
                        });
                    }
                }
            });

            // xử lý chỉ cho nhập số
            field.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar())) {
                        e.consume();
                    }
                }
            });
        }
    }

    // Hàm di chuyển focus sau khi nhập xong
    private void moveToNextField(JTextField currentField, JTextField[] otpFields) {
        for (int i = 0; i < otpFields.length - 1; i++) {
            if (otpFields[i] == currentField) {
                otpFields[i + 1].requestFocus();
                return;
            }
        }
    }
    
    // lấy otp đã nhập
    private String getOTPValue() {
        JTextField[] otpFields = {jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6};
        StringBuilder newotp = new StringBuilder();

        for (JTextField field : otpFields) {
            String value = field.getText().trim();
            if( value.isEmpty() ) {
                return null; 
            }
            newotp.append(value);
        }
        return newotp.toString(); 
    }

    private void accepBtnClick() {
        String inputOTP = getOTPValue();
        if( inputOTP == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ mã OTP!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // kiểm tra mã OTP
        if( inputOTP.equals(this.otp) ) {
            JOptionPane.showMessageDialog(null, "Xác thực thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            checkOtp.set(true);
        } else {
            JOptionPane.showMessageDialog(null, "Mã OTP không chính xác!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

}
