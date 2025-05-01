package main.java.utc2.apartmentManage.view.UserUI.Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

public class VNPAYQRCodeFrame extends JFrame {

    public VNPAYQRCodeFrame(String payUrl) throws Exception {
        setTitle("Mã QR MoMo");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        BufferedImage qrImage = MoMoPayment.generateQR(payUrl, 250, 250);
        JLabel qrLabel = new JLabel(new ImageIcon(qrImage));
        qrLabel.setHorizontalAlignment(JLabel.CENTER);

        add(qrLabel, BorderLayout.CENTER);


        JLabel infoLabel = new JLabel("Quét mã để thanh toán");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        infoLabel.setHorizontalAlignment(JLabel.CENTER);

        // Tạo JPanel chứa label và thêm padding
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(new EmptyBorder(0, 0, 20, 0)); 
        bottomPanel.add(infoLabel, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

    }

}

