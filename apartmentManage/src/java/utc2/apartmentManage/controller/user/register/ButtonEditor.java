package java.utc2.apartmentManage.controller.user.register;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private Consumer<Integer> onClick;
    private int currentRow;

    public ButtonEditor(JCheckBox checkBox, Consumer<Integer> onClick) {
        super(checkBox);
        this.onClick = onClick;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "Đăng ký" : value.toString();
        button.setText(label);
        isPushed = true;
        currentRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed && onClick != null) {
            onClick.accept(currentRow);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}
