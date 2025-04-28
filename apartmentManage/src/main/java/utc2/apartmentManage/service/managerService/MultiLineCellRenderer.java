
package main.java.utc2.apartmentManage.service.managerService;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextArea;


public class MultiLineCellRenderer extends JTextArea implements javax.swing.table.TableCellRenderer {
        public MultiLineCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setFont(new Font("Arial", Font.PLAIN, 14)); 
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText(value == null ? "" : value.toString());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }

            setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
            int preferredHeight = getPreferredSize().height;

            // 👉 Reset chiều cao về mặc định trước, rồi set lại nếu cần
            int defaultHeight = table.getRowHeight(); // lấy chiều cao dòng mặc định
            table.setRowHeight(row, defaultHeight); // reset về mặc định

            if (preferredHeight > defaultHeight) {
                table.setRowHeight(row, preferredHeight); // set lại nếu cao hơn
            }
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            return this;
        }
}

    
