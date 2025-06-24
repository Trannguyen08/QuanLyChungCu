package java.utc2.apartmentManage.controller.manager.report;

import utc2.apartmentManage.model.Amount;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PieChartPanel extends JPanel {
    private List<Amount> data;

    public PieChartPanel(List<Amount> data) {
        this.data = data;
        setPreferredSize(new Dimension(600, 300));
    }

    public void setData(List<Amount> data) {
        this.data = data;
        repaint();  
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (data == null || data.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth(), height = getHeight();

        int legendWidth = 120; // Khu vực dành cho chú thích bên phải
        int spacing = 20;

        // Đường kính hình tròn nhỏ hơn một chút để nhìn thoáng
        int diameter = Math.min(width - legendWidth - spacing * 2, height - spacing * 2);
        int x = spacing;
        int y = (height - diameter) / 2;

        double startAngle = 0;

        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.PINK};

        // Vẽ các phần của biểu đồ tròn
        for (int i = 0; i < data.size(); i++) {
            Amount a = data.get(i);
            double angle = a.getPercent() * 360.0 / 100;
            g2.setColor(colors[i % colors.length]);
            g2.fillArc(x, y, diameter, diameter, (int) startAngle, (int) angle);
            startAngle += angle;
        }

        // Vẽ chú thích bên phải
        int legendX = x + diameter + spacing;
        int legendY = spacing;
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        for (int i = 0; i < data.size(); i++) {
            g2.setColor(colors[i % colors.length]);
            g2.fillRect(legendX, legendY, 12, 12);
            g2.setColor(Color.BLACK);
            g2.drawString(data.get(i).getName() + " (" + String.format("%.1f", data.get(i).getPercent()) + "%)", legendX + 18, legendY + 12);
            legendY += 20;
        }
    }


}

