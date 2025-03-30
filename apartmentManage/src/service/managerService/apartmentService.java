package service.managerService;

import dao.managerDAO.ApartmentDAO;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Apartment;
import util.ScannerUtil;

import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class apartmentService {
    private final ApartmentDAO apartmentDAO = new ApartmentDAO();

    //update database
    public boolean updateApartment(Apartment apartment) {
        return apartmentDAO.updateApartment(apartment);
    }

    // thêm vào database
    public boolean addApartment(Apartment apartment) {
        return apartmentDAO.addApartment(apartment);
    }

    // Xóa căn hộ khỏi database
    public boolean deleteApartment(int id) {
        return apartmentDAO.deleteApartment(id);
    }
    
    // load dữ liệu vào bảng
    public void setupApartmentTable(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();

        for( Apartment apartment : apartmentList ) {
            model.addRow(new Object[] {apartment.getId(), apartment.getIndex(), apartment.getFloor(),
                    apartment.getBuilding(), apartment.getNumRooms(), apartment.getStatus(),
                    apartment.getArea(), apartment.getRentPrice(), apartment.getPurchasePrice()});
        }

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 15));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

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

    // Xác nhận xóa
    public boolean confirmDelete() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa hàng này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }

    // check select từ table
    public boolean notification(JTable table) {
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
        boolean error = notification(table);
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
    public boolean validateData(JComboBox<String> apartmentIndex, JComboBox<String> floor,
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
        if (ScannerUtil.validateDouble(areaVal, "Diện tích") ||
                ScannerUtil.validateDouble(rent, "Giá thuê") ||
                ScannerUtil.validateDouble(buy, "Giá mua") ||
                ScannerUtil.spaceDouble(areaVal, 50, 80, "Diện tích") ||
                ScannerUtil.spaceDouble(rent, 5e6, 1.5e7, "Giá thuê") ||
                ScannerUtil.spaceDouble(buy, 2.5e9, 10e9, "Giá mua")) {
            return false;
        }
        return true;
    }

    public boolean validateSeachInput(JTextField apartmentID, JTextField fromArea, JTextField toArea,
                                      JTextField fromRentPrice, JTextField toRentPrice,
                                      JTextField fromBuyPrice, JTextField toBuyPrice,
                                      JComboBox<String> fromFloor, JComboBox<String> toFloor,
                                      JComboBox<String> fromRoomNum, JComboBox<String> toRoomNum) {

        boolean isIDEmpty = apartmentID.getText().trim().isEmpty();
        boolean isFromAreaEmpty = fromArea.getText().trim().isEmpty();
        boolean isToAreaEmpty = toArea.getText().trim().isEmpty();
        boolean isFromRentPriceEmpty = fromRentPrice.getText().trim().isEmpty();
        boolean isToRentPriceEmpty = toRentPrice.getText().trim().isEmpty();
        boolean isFromBuyPriceEmpty = fromBuyPrice.getText().trim().isEmpty();
        boolean isToBuyPriceEmpty = toBuyPrice.getText().trim().isEmpty();
        boolean isFromFloorEmpty = fromFloor.getSelectedItem().toString().trim().isEmpty();
        boolean isToFloorEmpty = toFloor.getSelectedItem().toString().trim().isEmpty();
        boolean isFromRoomNumEmpty = fromRoomNum.getSelectedItem().toString().trim().isEmpty();
        boolean isToRoomNumEmpty = toRoomNum.getSelectedItem().toString().trim().isEmpty();


        if (    (apartmentID.getText() != null && !isIDEmpty &&
                        ScannerUtil.validateInteger(apartmentID.getText().trim(), "ID căn hộ")) ||
                (fromArea.getText() != null && !isFromAreaEmpty &&
                        ScannerUtil.validateDouble(fromArea.getText().trim(), "Diện tích")) ||
                (toArea.getText() != null && !isToAreaEmpty &&
                        ScannerUtil.validateDouble(toArea.getText().trim(), "Diện tích")) ||
                (fromRentPrice.getText() != null && !isFromRentPriceEmpty &&
                        ScannerUtil.validateDouble(fromRentPrice.getText().trim(), "Giá thuê")) ||
                (toRentPrice.getText() != null && !isToRentPriceEmpty &&
                        ScannerUtil.validateDouble(toRentPrice.getText().trim(), "Giá thuê")) ||
                (fromBuyPrice.getText() != null && !isFromBuyPriceEmpty &&
                        ScannerUtil.validateDouble(fromBuyPrice.getText().trim(), "Giá mua")) ||
                (toBuyPrice.getText() != null && !isToBuyPriceEmpty &&
                        ScannerUtil.validateDouble(toBuyPrice.getText().trim(), "Giá mua")) ||
                (fromFloor.getSelectedItem() != null && !isFromFloorEmpty &&
                        ScannerUtil.validateDouble(fromFloor.getSelectedItem().toString().trim(), "Tầng")) ||
                (toFloor.getSelectedItem() != null && !isToFloorEmpty &&
                        ScannerUtil.validateDouble(toFloor.getSelectedItem().toString().trim(), "Tầng")) ||
                (fromRoomNum.getSelectedItem() != null && !isFromRoomNumEmpty &&
                        ScannerUtil.validateDouble(fromRoomNum.getSelectedItem().toString().trim(), "Số phòng")) ||
                (toRoomNum.getSelectedItem() != null && !isToRoomNumEmpty &&
                        ScannerUtil.validateDouble(toRoomNum.getSelectedItem().toString().trim(), "Số phòng")) ) {

            return false;
        }

        if (    !isFromAreaEmpty && !isToAreaEmpty &&
                        !ScannerUtil.validateRange(fromArea.getText().trim(), toArea.getText().trim(), "Diện tích") ||
                !isFromFloorEmpty && !isToFloorEmpty &&
                        !ScannerUtil.validateRange(fromFloor.getSelectedItem().toString().trim(), toFloor.getSelectedItem().toString().trim(), "Tầng") ||
                !isFromRoomNumEmpty && !isToRoomNumEmpty &&
                        !ScannerUtil.validateRange(fromRoomNum.getSelectedItem().toString().trim(), toRoomNum.getSelectedItem().toString().trim(), "Số phòng") ||
                !isFromRentPriceEmpty && !isToRentPriceEmpty &&
                        !ScannerUtil.validateRange(fromRentPrice.getText().trim(), toRentPrice.getText().trim(), "Giá thuê") ||
                !isFromBuyPriceEmpty && !isToBuyPriceEmpty &&
                        !ScannerUtil.validateRange(fromBuyPrice.getText().trim(), toBuyPrice.getText().trim(), "Giá mua") ) {

            return false;
        }

        return true;
    }


    public void loadFilterApartment(List<Apartment> apartments, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0);

        for (Apartment apt : apartments) {
            model.addRow(new Object[]{
                apt.getId(),  
                apt.getIndex(), 
                apt.getFloor(),       
                apt.getBuilding(),   
                apt.getNumRooms(),     
                apt.getStatus(),     
                apt.getArea(),        
                apt.getRentPrice(),  
                apt.getPurchasePrice()    
            });
        }
    }




}
