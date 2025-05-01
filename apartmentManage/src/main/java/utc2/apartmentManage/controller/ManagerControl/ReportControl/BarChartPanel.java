package main.java.utc2.apartmentManage.controller.ManagerControl.ReportControl;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import main.java.utc2.apartmentManage.model.Amount;

public class BarChartPanel extends JPanel {
    private List<Amount> data;

    public BarChartPanel(List<Amount> data) {
        this.data = data;
        setPreferredSize(new Dimension(650, 300));  // Giảm chiều cao của chartPanel từ 400 xuống 300
    }

    public void setData(List<Amount> data) {
        this.data = data;
        repaint();  // Gọi lại phương thức vẽ lại
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (data == null || data.isEmpty()) {
            System.out.println("No data to display.");
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int padding = 70;  // Tăng khoảng cách bên trái của trục Y
        int barWidth = (width - 2 * padding) / (data.size() * 2);  // Giảm chiều rộng của cột, làm cho khoảng cách nhỏ lại

        // Tìm giá trị lớn nhất để xác định tỷ lệ cho chiều cao cột
        double maxValue = data.stream().mapToDouble(Amount::getTotal).max().orElse(1);

        // Vẽ các cột
        for (int i = 0; i < data.size(); i++) {
            Amount a = data.get(i);
            int barHeight = (int) ((a.getTotal() / maxValue) * (height - 2 * padding));  // Tính chiều cao cột
            int x = padding + i * barWidth * 2;  // Dịch sang phải để giảm khoảng cách giữa các cột
            int y = height - padding - barHeight;

            g2.setColor(new Color(100, 150, 255));  // Màu của cột
            g2.fillRect(x + 10, y, barWidth - 5, barHeight);  // Vẽ cột, cách trục Y một chút

            // Vẽ nhãn giá trị bên trên cột (Căn chỉnh chính giữa cột)
            g2.setFont(new Font("Arial", Font.PLAIN, 14));
            g2.setColor(Color.BLACK);
            String valueText = String.format("%d", (int) a.getTotal());
            int valueTextWidth = g2.getFontMetrics().stringWidth(valueText);
            g2.drawString(valueText, x + (barWidth - valueTextWidth) / 2, y - 15);// Căn giữa số tiền

            // Vẽ nhãn dịch vụ dưới mỗi cột (Căn chỉnh chính giữa cột)
            String serviceName = a.getName();
            int serviceNameWidth = g2.getFontMetrics().stringWidth(serviceName);
            g2.setFont(new Font("Arial", Font.PLAIN, 14));
            g2.drawString(serviceName, x + (barWidth - serviceNameWidth) / 2, height - 10);// Căn giữa tên dịch vụ

        }

        // Vẽ trục X và trục Y
        g2.setColor(Color.BLACK);
        g2.drawLine(padding, height - padding, width - padding, height - padding);  // Trục X
        g2.drawLine(padding, padding, padding, height - padding);  // Trục Y

        // Vẽ mức giá trị cho trục Y (trục đứng) mà không bị đè lên cột
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        int step = (int) (maxValue / 5);  // Chia trục Y thành 5 mức giá trị

        // Vẽ mức giá cho trục Y
        for (int i = 0; i <= 5; i++) {
            // Tính vị trí của mức giá trên trục Y
            int yPosition = height - padding - (int) ((i * step / maxValue) * (height - 2 * padding));

            // Vẽ đường ngang cho trục Y
            g2.drawLine(padding - 10, yPosition, padding, yPosition);  // Vẽ các mức giá trị ngang

            // Vẽ mức giá trị với khoảng cách để không bị đè lên cột
            String valueText = String.format("%d", i * step);
            int valueTextWidth = g2.getFontMetrics().stringWidth(valueText);  // Lấy chiều rộng của mức giá
            g2.drawString(valueText, padding - valueTextWidth - 10, yPosition + 5);  // Hiển thị mức giá ở phía trái của trục Y, tránh bị đè lên cột
        }
    }
}
