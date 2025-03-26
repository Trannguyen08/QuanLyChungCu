package service.managerService;

import dao.managerDAO.ApartmentDAO;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Apartment;
import util.ScannerUtil;

import java.util.List;

public class apartmentService {
    private final ApartmentDAO apartmentDAO = new ApartmentDAO();

    // validate trùng căn hộ
    public boolean isDuplicate(Apartment apartment, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for( int i = 0 ; i < model.getRowCount() ; i++ ) {
            Object existingApartment = model.getValueAt(i, 1);
            Object existingFloor = model.getValueAt(i, 2);
            Object existingBuilding = model.getValueAt(i, 3);

            if( existingApartment.equals(apartment.getIndex()) &&
                existingFloor.equals(apartment.getFloor()) &&
                existingBuilding.equals(apartment.getBuilding())) {
                return true; 
            }
        }
        return false;
    }

    // thêm vào database
    public boolean addApartment(Apartment apartment) {
        return apartmentDAO.addApartment(apartment);
    }

    // lấy dòng cuối để update table
    public Object[] getLastRow() {
        return apartmentDAO.getLastRow();
    }

    // lấy id
    public Integer getApartmentId(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để xóa!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object value = model.getValueAt(selectedRow, 0);
        if (value == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy ID của hàng đã chọn!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            return Integer.parseInt(value.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Xóa căn hộ khỏi database
    public boolean deleteApartment(int id) {
        return apartmentDAO.deleteApartment(id);
    }

    // Xác nhận xóa
    public boolean confirmDelete() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa hàng này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }

    // check select từ table
    public boolean errorNotifiaction(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    // gán dữ liệu từ dòng đã chọn vào form
    public boolean loadSelectedRowData(JTable table, JComboBox<String> apartmentIndex, JComboBox<String> floor,
                                       JComboBox<String> building, JComboBox<String> roomNum, JComboBox<String> status,
                                       JTextField area, JTextField rentPrice, JTextField buyPrice) {
        boolean error = errorNotifiaction(table);
        if( !error ) {
            return false;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        apartmentIndex.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
        floor.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        building.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        roomNum.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
        status.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
        area.setText(model.getValueAt(selectedRow, 6).toString());
        rentPrice.setText(model.getValueAt(selectedRow, 7).toString());
        buyPrice.setText(model.getValueAt(selectedRow, 8).toString());

        return true;
    }

    // validate data
    public boolean validateData(JTable table, JComboBox<String> apartmentIndex, JComboBox<String> floor,
                                   JComboBox<String> building, JComboBox<String> roomNum, JComboBox<String> status,
                                   JTextField area, JTextField rentPrice, JTextField buyPrice) {

        Object apartment = apartmentIndex.getSelectedItem();
        Object floorNum = floor.getSelectedItem();
        Object buildingNum = building.getSelectedItem();
        Object roomNumber = roomNum.getSelectedItem();
        Object statusVal = status.getSelectedItem();
        String areaVal = area.getText().trim();
        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        // Kiểm tra dữ liệu đầu vào
        if (apartment == null || floorNum == null ||
                buildingNum == null || roomNumber == null || statusVal == null ||
                areaVal.isEmpty() || rent.isEmpty() || buy.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin trước khi cập nhật!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra giá trị hợp lệ
        if (!ScannerUtil.validateDouble(areaVal, "Diện tích") ||
                !ScannerUtil.validateDouble(rent, "Giá thuê") ||
                !ScannerUtil.validateDouble(buy, "Giá mua") ||
                !ScannerUtil.spaceDouble(areaVal, 50, 70, "Diện tích") ||
                !ScannerUtil.spaceDouble(rent, 5e6, 1e7, "Giá thuê") ||
                !ScannerUtil.spaceDouble(buy, 2.5e9, 7e9, "Giá mua")) {
            return false;
        }

        return true;
    }

    public boolean updateApartment(Apartment apartment) {
        return apartmentDAO.updateApartment(apartment);
    }

    public boolean validateSeachInput(JTextField apartmentID, JTextField fromArea, JTextField toArea,
                                 JTextField fromRentPrice, JTextField toRentPrice,
                                 JTextField fromBuyPrice, JTextField toBuyPrice,
                                 JComboBox<String> fromFloor, JComboBox<String> toFloor,
                                 JComboBox<String> fromRoomNum, JComboBox<String> toRoomNum) {
        if ((apartmentID.getText() != null && !apartmentID.getText().trim().isEmpty() &&
                !ScannerUtil.validateInteger(apartmentID.getText().trim(), "ID Căn hộ")) ||
                (fromArea.getText() != null && !fromArea.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fromArea.getText().trim(), "Diện tích")) ||
                (toArea.getText() != null && !toArea.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toArea.getText().trim(), "Diện tích")) ||
                (fromRentPrice.getText() != null && !fromRentPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fromRentPrice.getText().trim(), "Giá thuê")) ||
                (toRentPrice.getText() != null && !toRentPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toRentPrice.getText().trim(), "Giá thuê")) ||
                (fromBuyPrice.getText() != null && !fromBuyPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fromBuyPrice.getText().trim(), "Giá mua")) ||
                (toBuyPrice.getText() != null && !toBuyPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toBuyPrice.getText().trim(), "Giá mua"))) {
            return false;
        }

        if ((!fromFloor.getSelectedItem().toString().trim().isEmpty() && !toFloor.getSelectedItem().toString().trim().isEmpty() &&
                !ScannerUtil.validateRange(fromFloor.getSelectedItem().toString().trim(), toFloor.getSelectedItem().toString().trim(), "Tầng")) ||
                (!fromRoomNum.getSelectedItem().toString().trim().isEmpty() && !toRoomNum.getSelectedItem().toString().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromRoomNum.getSelectedItem().toString().trim(), toRoomNum.getSelectedItem().toString().trim(), "Số phòng ngủ")) ||
                (fromArea.getText() != null && toArea.getText() != null &&
                        !fromArea.getText().trim().isEmpty() && !toArea.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromArea.getText().trim(), toArea.getText().trim(), "Diện tích")) ||
                (fromRentPrice.getText() != null && toRentPrice.getText() != null &&
                        !fromRentPrice.getText().trim().isEmpty() && !toRentPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromRentPrice.getText().trim(), toRentPrice.getText().trim(), "Giá thuê")) ||
                (fromBuyPrice.getText() != null && toBuyPrice.getText() != null &&
                        !fromBuyPrice.getText().trim().isEmpty() && !toBuyPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromBuyPrice.getText().trim(), toBuyPrice.getText().trim(), "Giá mua"))) {
            return false;
        }

        return true;
    }

    public List<RowFilter<DefaultTableModel, Integer>> createFilters(JTextField apartmentID, JComboBox<String> apartmentIndex,
                                                                     JComboBox<String> building, JComboBox<String> status) {
        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

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

        return filters;
    }


}
