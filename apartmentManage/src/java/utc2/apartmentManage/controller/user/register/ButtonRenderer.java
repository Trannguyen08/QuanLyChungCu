package java.utc2.apartmentManage.controller.user.register;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        setOpaque(true);
        setFocusPainted(false);

        // Tăng padding để nút trông lớn hơn
        setMargin(new Insets(8, 20, 8, 20)); // top, left, bottom, right

        // Border vô hình để tạo khoảng cách với ô bảng
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Tuỳ chọn: tăng kích thước tối thiểu (nếu muốn bắt buộc)
        setPreferredSize(new Dimension(120, 35)); // Width x Height
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        setText((value == null) ? "Đăng ký" : value.toString());

        setEnabled(!"Đã đăng ký".equals(value));

        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(UIManager.getColor("Button.background"));
        }

        return this;
    }
}
