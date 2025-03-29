package controller.ManagerControl.ApartmentHandle;

import dao.managerDAO.ApartmentDAO;
import service.managerService.apartmentService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import model.Apartment;
import util.ScannerUtil;

public class searchIconHandler {
    private JTextField apartmentID, fromArea, fromBuyPrice, fromRentPrice, toArea, toBuyPrice, toRentPrice1;
    private JComboBox<String> apartmentIndex, building, status, fromFloor, fromRoomNum, toFloor, toRoomNum;
    private JButton searchBtn;
    private JTable table;
    private JFrame frame;
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
                            ? null : Integer.parseInt(fromFloor.getSelectedItem().toString().trim());

        Integer floorTo = (toFloor.getSelectedItem() == null || toFloor.getSelectedItem().toString().trim().isEmpty())
                            ? null : Integer.parseInt(toFloor.getSelectedItem().toString().trim());

        Integer roomFrom = (fromRoomNum.getSelectedItem() == null || fromRoomNum.getSelectedItem().toString().trim().isEmpty())
                ? null : Integer.parseInt(fromRoomNum.getSelectedItem().toString().trim());

        Integer roomTo = (toRoomNum.getSelectedItem() == null || toRoomNum.getSelectedItem().toString().trim().isEmpty())
                ? null : Integer.parseInt(toRoomNum.getSelectedItem().toString().trim());


        Double areaFrom = (fromArea.getText() == null || fromArea.getText().trim().isEmpty())
                ? null : Double.parseDouble(fromArea.getText().trim());

        Double areaTo = (toArea.getText() == null || toArea.getText().trim().isEmpty())
                ? null : Double.parseDouble(toArea.getText().trim());

        Double rentFrom = (fromRentPrice.getText() == null || fromRentPrice.getText().trim().isEmpty())
                ? null : Double.parseDouble(fromRentPrice.getText().trim());

        Double rentTo = (toRentPrice1.getText() == null || toRentPrice1.getText().trim().isEmpty())
                ? null : Double.parseDouble(toRentPrice1.getText().trim());

        Double buyFrom = (fromBuyPrice.getText() == null || fromBuyPrice.getText().trim().isEmpty())
                ? null : Double.parseDouble(fromBuyPrice.getText().trim());

        Double buyTo = (toBuyPrice.getText() == null || toBuyPrice.getText().trim().isEmpty())
                ? null : Double.parseDouble(toBuyPrice.getText().trim());


        // Lấy danh sách căn hộ từ database
        ApartmentDAO adao = new ApartmentDAO();
        List<Apartment> apartments = adao.getApartmentsByFilter(
                aptId, aptIndex, build,
                areaFrom, areaTo,
                rentFrom, rentTo,
                buyFrom, buyTo,
                floorFrom, floorTo,
                roomFrom, roomTo,
                stat
        );

        frame.setVisible(false);
        if (apartments.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        apartmentService.loadFilterApartment(apartments, table);
    }



}
