package main.java.com.utc2.apartmentManage.controller.ManagerControl.ApartmentHandle;

import main.java.com.utc2.apartmentManage.repository.managerRepository.apartmentRepository;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import main.java.com.utc2.apartmentManage.model.Apartment;
import main.java.com.utc2.apartmentManage.service.managerService.apartmentService;

public class searchIconHandler {
    private JTextField apartmentID, fromArea, fromBuyPrice, fromRentPrice, toArea, toBuyPrice, toRentPrice1;
    private JComboBox<String> apartmentIndex, building, status, fromFloor, fromRoomNum, toFloor, toRoomNum;
    private JButton searchBtn;
    private JTable table;
    private JFrame frame;
    private final apartmentService apartmentService = new apartmentService();

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
        boolean check = apartmentService.validateSeachInput(apartmentID, fromArea, toArea, fromRentPrice, toRentPrice1, fromBuyPrice,
                toBuyPrice, fromFloor, toFloor, fromRoomNum, toRoomNum);
        if (!check) {
            System.out.println("hello");
            return;
        }

        // Chuyển đổi giá trị nhập từ component, kiểm tra giá trị null và chuỗi rỗng

        String aptIndexStr = (String) apartmentIndex.getSelectedItem();
        int aptIndex = (aptIndexStr != null && !aptIndexStr.trim().isEmpty()) ? Integer.parseInt(aptIndexStr) : 0;

        String aptIdStr = apartmentID.getText().trim();
        int aptId = (!aptIdStr.isEmpty()) ? Integer.parseInt(aptIdStr) : 0;

        String build = (String) building.getSelectedItem();

        String stat = (String) status.getSelectedItem();

        Integer floorFrom = (fromFloor.getSelectedItem() == null || fromFloor.getSelectedItem().toString().trim().isEmpty())
                            ? null : Integer.valueOf(fromFloor.getSelectedItem().toString().trim());

        Integer floorTo = (toFloor.getSelectedItem() == null || toFloor.getSelectedItem().toString().trim().isEmpty())
                            ? null : Integer.valueOf(toFloor.getSelectedItem().toString().trim());

        Integer roomFrom = (fromRoomNum.getSelectedItem() == null || fromRoomNum.getSelectedItem().toString().trim().isEmpty())
                ? null : Integer.valueOf(fromRoomNum.getSelectedItem().toString().trim());

        Integer roomTo = (toRoomNum.getSelectedItem() == null || toRoomNum.getSelectedItem().toString().trim().isEmpty())
                ? null : Integer.valueOf(toRoomNum.getSelectedItem().toString().trim());


        Double areaFrom = (fromArea.getText() == null || fromArea.getText().trim().isEmpty())
                ? null : Double.valueOf(fromArea.getText().trim());

        Double areaTo = (toArea.getText() == null || toArea.getText().trim().isEmpty())
                ? null : Double.valueOf(toArea.getText().trim());

        Double rentFrom = (fromRentPrice.getText() == null || fromRentPrice.getText().trim().isEmpty())
                ? null : Double.valueOf(fromRentPrice.getText().trim());

        Double rentTo = (toRentPrice1.getText() == null || toRentPrice1.getText().trim().isEmpty())
                ? null : Double.valueOf(toRentPrice1.getText().trim());

        Double buyFrom = (fromBuyPrice.getText() == null || fromBuyPrice.getText().trim().isEmpty())
                ? null : Double.valueOf(fromBuyPrice.getText().trim());

        Double buyTo = (toBuyPrice.getText() == null || toBuyPrice.getText().trim().isEmpty())
                ? null : Double.valueOf(toBuyPrice.getText().trim());


        // Lấy danh sách căn hộ từ database
        apartmentRepository adao = new apartmentRepository();
        List<Apartment> apartments = adao.getApartmentsByFilter(
                aptId, aptIndex, build,
                areaFrom, areaTo,
                rentFrom, rentTo,
                buyFrom, buyTo,
                floorFrom, floorTo,
                roomFrom, roomTo,
                stat
        );

        apartmentService.loadFilterApartment(apartments, table);
        frame.setVisible(false);
        if (apartments.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }



}
