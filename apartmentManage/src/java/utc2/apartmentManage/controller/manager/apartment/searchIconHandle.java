package java.utc2.apartmentManage.controller.manager.apartment;

import utc2.apartmentManage.model.Apartment;
import utc2.apartmentManage.service.implement.manager.apartmentIMP;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class searchIconHandle {
    private JTextField fromArea, fromBuyPrice, fromRentPrice, toArea, toBuyPrice, toRentPrice1;
    private JComboBox<String> building, status, fromFloor, fromRoomNum, toFloor, toRoomNum, interior;
    private JButton searchBtn;
    private JTable table;
    private JFrame frame;
    private final apartmentIMP apartmentIMP = new apartmentIMP();

    public searchIconHandle(JComboBox<String> building, JFrame frame, JTextField fromArea, JComboBox<String> interior,
                            JTextField fromBuyPrice, JComboBox<String> fromFloor,
                            JTextField fromRentPrice, JComboBox<String> fromRoomNum,
                            JButton searchBtn, JComboBox<String> status, JTable table, JTextField toArea,
                            JTextField toBuyPrice, JComboBox<String> toFloor, JTextField toRentPrice1, JComboBox<String> toRoomNum) {
        
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
        this.interior = interior;

        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }

    public void filterTableData() {
        boolean check = apartmentIMP.searchValidate(fromArea, toArea, fromRentPrice, toRentPrice1, fromBuyPrice,toBuyPrice);
        if (!check) {
            return;
        }

        // Chuyển đổi giá trị nhập từ component, kiểm tra giá trị null và chuỗi rỗng

        String build = building.getSelectedItem().toString().trim();

        String stat = status.getSelectedItem().toString().trim();

        String inte = interior.getSelectedItem().toString().trim();

        int floorFrom = (fromFloor.getSelectedItem() == null || fromFloor.getSelectedItem().toString().trim().isEmpty())
                            ? 0 : Integer.parseInt(fromFloor.getSelectedItem().toString().trim());

        Integer floorTo = (toFloor.getSelectedItem() == null || toFloor.getSelectedItem().toString().trim().isEmpty())
                            ? null : Integer.valueOf(toFloor.getSelectedItem().toString().trim());

        int roomFrom = (fromRoomNum.getSelectedItem() == null || fromRoomNum.getSelectedItem().toString().trim().isEmpty())
                ? 0 : Integer.parseInt(fromRoomNum.getSelectedItem().toString().trim());

        Integer roomTo = (toRoomNum.getSelectedItem() == null || toRoomNum.getSelectedItem().toString().trim().isEmpty())
                ? null : Integer.valueOf(toRoomNum.getSelectedItem().toString().trim());


        double areaFrom = (fromArea.getText() == null || fromArea.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(fromArea);

        Double areaTo = (toArea.getText() == null || toArea.getText().trim().isEmpty())
                ? null : ScannerUtil.replaceDouble(toArea);

        double rentFrom = (fromRentPrice.getText() == null || fromRentPrice.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(fromRentPrice);

        Double rentTo = (toRentPrice1.getText() == null || toRentPrice1.getText().trim().isEmpty())
                ? null : ScannerUtil.replaceDouble(toRentPrice1);

        double buyFrom = (fromBuyPrice.getText() == null || fromBuyPrice.getText().trim().isEmpty())
                ? 0 : ScannerUtil.replaceDouble(fromBuyPrice);

        Double buyTo = (toBuyPrice.getText() == null || toBuyPrice.getText().trim().isEmpty())
                ? null : ScannerUtil.replaceDouble(toBuyPrice);


        // Lấy danh sách căn hộ từ database
        Apartment apt = new Apartment(0, 0, floorFrom, build, roomFrom, 0, inte, stat, areaFrom, rentFrom, buyFrom);

        apartmentIMP.loadFilterApartment(apt, floorTo, roomTo, areaTo, rentTo, buyTo, table);
        frame.setVisible(false);
        
    }



}
