package Controller.ManagerControl.ApartmentHandle;
import Util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class searchIconHandler {
    private javax.swing.JTextField apartmentID;
    private javax.swing.JComboBox<String> apartmentIndex;
    private javax.swing.JComboBox<String> building;
    private javax.swing.JTextField fromArea;
    private javax.swing.JTextField fromBuyPrice;
    private javax.swing.JComboBox<String> fromFloor;
    private javax.swing.JTextField fromRentPrice;
    private javax.swing.JComboBox<String> fromRoomNum;
    private javax.swing.JTextField residentID;
    private javax.swing.JButton searchBtn;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField toArea;
    private javax.swing.JTextField toBuyPrice;
    private javax.swing.JComboBox<String> toFloor;
    private javax.swing.JTextField toRentPrice1;
    private javax.swing.JComboBox<String> toRoomNum;
    private JTable table;
    private JFrame frame;

    public searchIconHandler(JTextField apartmentID, JComboBox<String> apartmentIndex, JComboBox<String> building,
                             JFrame frame, JTextField fromArea, JTextField fromBuyPrice, JComboBox<String> fromFloor,
                             JTextField fromRentPrice, JComboBox<String> fromRoomNum, JTextField residentID,
                             JButton searchBtn, JComboBox<String> status, JTable table, JTextField toArea,
                             JTextField toBuyPrice, JComboBox<String> toFloor, JTextField toRentPrice1, JComboBox<String> toRoomNum) {
        this.apartmentID = apartmentID;
        this.apartmentIndex = apartmentIndex;
        this.building = building;
        this.frame = frame;
        this.fromArea = fromArea;
        this.fromBuyPrice = fromBuyPrice;
        this.fromFloor = fromFloor;
        this.fromRentPrice = fromRentPrice;
        this.fromRoomNum = fromRoomNum;
        this.residentID = residentID;
        this.searchBtn = searchBtn;
        this.status = status;
        this.table = table;
        this.toArea = toArea;
        this.toBuyPrice = toBuyPrice;
        this.toFloor = toFloor;
        this.toRentPrice1 = toRentPrice1;
        this.toRoomNum = toRoomNum;

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

        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

        // validate dữ diệu nhập đúng kiểu
        if ((apartmentID.getText() != null && !apartmentID.getText().trim().isEmpty() &&
                !ScannerUtil.validateInteger(apartmentID.getText().trim(), "ID Căn hộ")) ||
                (residentID.getText() != null && !residentID.getText().trim().isEmpty() &&
                        !ScannerUtil.validateInteger(residentID.getText().trim(), "ID Cư dân")) ||
                (fromArea.getText() != null && !fromArea.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fromArea.getText().trim(), "Diện tích")) ||
                (toArea.getText() != null && !toArea.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toArea.getText().trim(), "Diện tích")) ||
                (fromRentPrice.getText() != null && !fromRentPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fromRentPrice.getText().trim(), "Giá thuê")) ||
                (toRentPrice1.getText() != null && !toRentPrice1.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toRentPrice1.getText().trim(), "Giá thuê")) ||
                (fromBuyPrice.getText() != null && !fromBuyPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fromBuyPrice.getText().trim(), "Giá mua")) ||
                (toBuyPrice.getText() != null && !toBuyPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toBuyPrice.getText().trim(), "Giá mua"))) {
            return;
        }

        if ((!fromFloor.getSelectedItem().toString().trim().isEmpty() && !toFloor.getSelectedItem().toString().trim().isEmpty() &&
                !ScannerUtil.validateRange(fromFloor.getSelectedItem().toString().trim(), toFloor.getSelectedItem().toString().trim(), "Tầng")) ||
                (!fromRoomNum.getSelectedItem().toString().trim().isEmpty() && !toRoomNum.getSelectedItem().toString().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromRoomNum.getSelectedItem().toString().trim(), toRoomNum.getSelectedItem().toString().trim(), "Số phòng ngủ")) ||
                (fromArea.getText() != null && toArea.getText() != null &&
                        !fromArea.getText().trim().isEmpty() && !toArea.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromArea.getText().trim(), toArea.getText().trim(), "Diện tích")) ||
                (fromRentPrice.getText() != null && toRentPrice1.getText() != null &&
                        !fromRentPrice.getText().trim().isEmpty() && !toRentPrice1.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromRentPrice.getText().trim(), toRentPrice1.getText().trim(), "Giá thuê")) ||
                (fromBuyPrice.getText() != null && toBuyPrice.getText() != null &&
                        !fromBuyPrice.getText().trim().isEmpty() && !toBuyPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromBuyPrice.getText().trim(), toBuyPrice.getText().trim(), "Giá mua"))) {
            return;
        }


        // nếu không null thì xét với table
        if (!apartmentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentID.getText().trim(), 0));
        }
        if (!residentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + residentID.getText().trim(), 1));
        }
        if (apartmentIndex.getSelectedItem() != null && !apartmentIndex.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentIndex.getSelectedItem().toString().trim(), 2));
        }
        if (building.getSelectedItem() != null && !building.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + building.getSelectedItem().toString().trim(), 4));
        }
        if (status.getSelectedItem() != null && !status.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + status.getSelectedItem().toString().trim(), 6));
        }

        // xét các khoảng số nguyên số thực
        RowFilter<DefaultTableModel, Integer> numberFilter = new RowFilter<>() {
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                try {
                    int floorVal = Integer.parseInt(entry.getStringValue(3).trim());
                    int roomNumVal = Integer.parseInt(entry.getStringValue(5).trim());
                    double areaVal = Double.parseDouble(entry.getStringValue(7).trim());
                    double rentPrice = Double.parseDouble(entry.getStringValue(8).trim());
                    double buyPrice = Double.parseDouble(entry.getStringValue(9).trim());

                    String fromFloorStr = fromFloor.getSelectedItem().toString().trim();
                    String toFloorStr = toFloor.getSelectedItem().toString().trim();
                    String fromRoomStr = fromRoomNum.getSelectedItem().toString().trim();
                    String toRoomStr = toRoomNum.getSelectedItem().toString().trim();
                    String fromAreaStr = fromArea.getText().trim();
                    String toAreaStr = toArea.getText().trim();
                    String fromRentStr = fromRentPrice.getText().trim();
                    String toRentStr = toRentPrice1.getText().trim();
                    String fromBuyStr = fromBuyPrice.getText().trim();
                    String toBuyStr = toBuyPrice.getText().trim();

                    boolean floorMatch = fromFloorStr.isEmpty() || floorVal >= Integer.parseInt(fromFloorStr);
                    boolean floorMaxMatch = toFloorStr.isEmpty() || floorVal <= Integer.parseInt(toFloorStr);

                    boolean roomMatch = fromRoomStr.isEmpty() || roomNumVal >= Integer.parseInt(fromRoomStr);
                    boolean roomMaxMatch = toRoomStr.isEmpty() || roomNumVal <= Integer.parseInt(toRoomStr);

                    boolean areaMatch = fromAreaStr.isEmpty() || areaVal >= Double.parseDouble(fromAreaStr);
                    boolean areaMaxMatch = toAreaStr.isEmpty() || areaVal <= Double.parseDouble(toAreaStr);

                    boolean rentMatch = fromRentStr.isEmpty() || rentPrice >= Double.parseDouble(fromRentStr);
                    boolean rentMaxMatch = toRentStr.isEmpty() || rentPrice <= Double.parseDouble(toRentStr);

                    boolean buyMatch = fromBuyStr.isEmpty() || buyPrice >= Double.parseDouble(fromBuyStr);
                    boolean buyMaxMatch = toBuyStr.isEmpty() || buyPrice <= Double.parseDouble(toBuyStr);

                    return floorMatch && floorMaxMatch && roomMatch && roomMaxMatch &&
                            areaMatch && areaMaxMatch && rentMatch && rentMaxMatch &&
                            buyMatch && buyMaxMatch;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        };

        filters.add(numberFilter);
        sorter.setRowFilter(RowFilter.andFilter(filters));
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }


}
