package Controller.ManagerControl.ApartmentHandle;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class searchIconHandler {
    private JTextField apartmentID;
    private JComboBox<String> apartmentIndex;
    private JComboBox<String> building;
    private JComboBox<String> floor;
    private JComboBox<String> roomNum;
    private JComboBox<String> status;
    private JTextField area;
    private JTextField fromRentPrice;
    private JTextField toRentPrice;
    private JTextField fromBuyPrice;
    private JTextField toBuyPrice;
    private JButton searchBtn;
    private JTable table;
    private JFrame frame;

    public searchIconHandler(JTextField apartmentID, JComboBox<String> apartmentIndex, JComboBox<String> building,
                             JComboBox<String> floor, JComboBox<String> roomNum, JComboBox<String> status,
                             JTextField area, JTextField fromRentPrice, JTextField toRentPrice, JTextField fromBuyPrice,
                             JTextField toBuyPrice, JButton searchBtn, JTable table, JFrame frame) {
        this.apartmentID = apartmentID;
        this.apartmentIndex = apartmentIndex;
        this.building = building;
        this.floor = floor;
        this.roomNum = roomNum;
        this.status = status;
        this.area = area;
        this.fromRentPrice = fromRentPrice;
        this.toRentPrice = toRentPrice;
        this.fromBuyPrice = fromBuyPrice;
        this.toBuyPrice = toBuyPrice;
        this.searchBtn = searchBtn;
        this.table = table;
        this.frame = frame;

        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }

    public void filterTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        String id = apartmentID.getText().trim();
        String index = (String) apartmentIndex.getSelectedItem();
        String build = (String) building.getSelectedItem();
        String floorNum = (String) floor.getSelectedItem();
        String room = (String) roomNum.getSelectedItem();
        String stat = (String) status.getSelectedItem();
        String areaText = area.getText().trim();
        String rentFrom = fromRentPrice.getText().trim();
        String rentTo = toRentPrice.getText().trim();
        String buyFrom = fromBuyPrice.getText().trim();
        String buyTo = toBuyPrice.getText().trim();

        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + id, 0));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + index, 2));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + build, 3));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + floorNum, 4));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + room, 5));
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + stat, 6));

        // Lọc theo giá trị số (diện tích, giá thuê, giá mua)
        RowFilter<DefaultTableModel, Integer> rentFilter = new RowFilter<>() {
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                double rent = Double.parseDouble(entry.getStringValue(8));
                double buy = Double.parseDouble(entry.getStringValue(9));
                double areaVal = Double.parseDouble(entry.getStringValue(7));

                boolean areaMatch = areaText.isEmpty() || areaVal == Double.parseDouble(areaText);
                boolean rentMatch = (rentFrom.isEmpty() || rent >= Double.parseDouble(rentFrom))
                        && (rentTo.isEmpty() || rent <= Double.parseDouble(rentTo));
                boolean buyMatch = (buyFrom.isEmpty() || buy >= Double.parseDouble(buyFrom))
                        && (buyTo.isEmpty() || buy <= Double.parseDouble(buyTo));

                return areaMatch && rentMatch && buyMatch;
            }
        };

        sorter.setRowFilter(rentFilter);

        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
