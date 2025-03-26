package controller.ManagerControl.ApartmentHandle;
import service.managerService.apartmentService;
import util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class searchIconHandler {
    public JTextField apartmentID, fromArea, fromBuyPrice, fromRentPrice, toArea, toBuyPrice, toRentPrice1;
    public JComboBox<String> apartmentIndex, building, status, fromFloor, fromRoomNum, toFloor, toRoomNum;
    public JButton searchBtn;
    public JTable table;
    public JFrame frame;
    private final service.managerService.apartmentService apartmentService = new apartmentService();

    public searchIconHandler(JTextField apartmentID, JComboBox<String> apartmentIndex, JComboBox<String> building,
                             JFrame frame, JTextField fromArea, JTextField fromBuyPrice, JComboBox<String> fromFloor,
                             JTextField fromRentPrice, JComboBox<String> fromRoomNum,
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

        boolean check = apartmentService.validateSeachInput(apartmentID, fromArea, toArea, fromRentPrice, toRentPrice1, fromBuyPrice,
                                                            toBuyPrice, fromFloor, toFloor, fromRoomNum, toRoomNum);
        if( !check ) {
            return;
        }


        // nếu không null thì xét với table
        if (!apartmentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentID.getText().trim(), 0));
        }
        if (apartmentIndex.getSelectedItem() != null && !apartmentIndex.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentIndex.getSelectedItem().toString().trim(), 1));
        }
        if (building.getSelectedItem() != null && !building.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + building.getSelectedItem().toString().trim(), 3));
        }
        if (status.getSelectedItem() != null && !status.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + status.getSelectedItem().toString().trim(), 5));
        }

        // xét các khoảng số nguyên số thực
        RowFilter<DefaultTableModel, Integer> numberFilter = new RowFilter<>() {
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                try {
                    int floorVal = Integer.parseInt(entry.getStringValue(2).trim());
                    int roomNumVal = Integer.parseInt(entry.getStringValue(4).trim());
                    double areaVal = Double.parseDouble(entry.getStringValue(6).trim());
                    double rentPrice = Double.parseDouble(entry.getStringValue(7).trim());
                    double buyPrice = Double.parseDouble(entry.getStringValue(8).trim());

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
