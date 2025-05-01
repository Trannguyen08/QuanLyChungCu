package main.java.utc2.apartmentManage.service.managerService;


import main.java.utc2.apartmentManage.repository.ManagerRepository.apartmentRepository;
import main.java.utc2.apartmentManage.repository.ManagerRepository.apartmentImageRepository;
import main.java.utc2.apartmentManage.model.Apartment;
import main.java.utc2.apartmentManage.service.Interface.*;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.io.File;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

public class apartmentIMP implements ISQL<Apartment>, ITable<Apartment>, IValidate, IApartment {
    private final apartmentRepository apartmentDAO = new apartmentRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    //update database
    @Override
    public boolean add(Apartment apartment) { return apartmentDAO.addApartment(apartment); }

    @Override
    public boolean update(Apartment apartment) { return apartmentDAO.updateApartment(apartment); }

    @Override
    public boolean delete(int id) { return false; }

    @Override
    public int getNewID() { return apartmentDAO.getIDMinNotExist(); }

    @Override
    public boolean isExist(int id) { return apartmentDAO.isHaveOwner(id); }

    @Override
    public boolean isDuplicate(Apartment apartment) { return apartmentDAO.isDuplicate(apartment);}

    @Override
    public Apartment getObject(int id) { return apartmentDAO.getApartmentById(id);}

    @Override
    public List<String> getAllImageByID(int id) { return apartmentDAO.getImageByID(id); }
    
    @Override
    public boolean confirmDelete(String s) { return ScannerUtil.comfirmWindow(s); }

    @Override
    public void deleteImage(int id, String path) {
        new apartmentImageRepository().deleteImage(id, path);
    }

    @Override
    public boolean saveApartmentImages(int apartmentID, List<String> imagePaths) {
        return new apartmentImageRepository().saveApartmentImages(apartmentID, imagePaths);
    }
    
    @Override
    public void setUpTable(JTable table) {
        List<Apartment> apartmentList = apartmentDAO.getAllApartment();
        addData(table, apartmentList);
        setFont(table);
    }


    @Override
    public void addData(JTable table, List<Apartment> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for( Apartment apartment : list ) {
            model.addRow(new Object[] {apartment.getId(), apartment.getIndex(), apartment.getFloor(),
                    apartment.getBuilding(), apartment.getInterior(), 
                    df.format(apartment.getArea()), apartment.getStatus(), 
                    df.format(apartment.getRentPrice()), 
                    df.format(apartment.getPurchasePrice())});
        }

    }
    
    @Override 
    public void setFont(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader()
                                    .getDefaultRenderer()).
                                    setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public boolean isSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng chọn một dòng.",
                    "Thông báo", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    @Override
    public Integer getApartmentId(JTable table) {
        isSelectedRow(table);
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object value = model.getValueAt(selectedRow, 0);
        try {
            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "ID không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE
            );
            return null;
        }
    }

    @Override
    public Apartment getApartmentByID(int id, JComboBox<String> interior, JComboBox<String> status,
                                            JTextField rentPrice, JTextField buyPrice ) {

        Apartment apartment = getObject(id);

        String inte = interior.getSelectedItem().toString();
        String sta = status.getSelectedItem().toString();
        double rent = ScannerUtil.replaceDouble(rentPrice);
        double buy = ScannerUtil.replaceDouble(buyPrice);

        apartment.setInterior(inte);
        apartment.setStatus(sta);
        apartment.setRentPrice(rent);
        apartment.setPurchasePrice(buy);

        return apartment;
    }

    // gán dữ liệu từ dòng đã chọn vào form
    @Override
    public boolean loadSelectedRowData(JTable table, JComboBox<String> status, JComboBox<String> interior,
                                       JTextField rentPrice, JTextField buyPrice) {
        boolean error = isSelectedRow(table);
        if( !error ) {
            return false;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        interior.setSelectedItem(model.getValueAt(selectedRow, 4).toString());
        status.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
        String rentPriceText = model.getValueAt(selectedRow, 7).toString().replace(".", "");
        rentPriceText = rentPriceText.replace(",", ".");
        String buyPriceText = model.getValueAt(selectedRow, 8).toString().replace(".", "");
        buyPriceText = buyPriceText.replace(",", ".");
        rentPrice.setText(rentPriceText);
        buyPrice.setText(buyPriceText);

        return true;
    }

    // validate data
    @Override
    public boolean priceValidate(JTextField rentPrice, JTextField buyPrice) {
        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        if (!ScannerUtil.validateDouble(rent, "Giá thuê")) {
            return false;
        }
        if (ScannerUtil.spaceDouble(rent, 4e6, 1.5e7, "Giá thuê")) {
            return false;
        }

        if (!ScannerUtil.validateDouble(buy, "Giá mua")) {
            return false;
        }
        if (ScannerUtil.spaceDouble(buy, 2e9, 10e9, "Giá mua")) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addValidate(Object... args) {
        JTextField area = (JTextField) args[0];
        JTextField rentPrice = (JTextField) args[1];
        JTextField buyPrice = (JTextField) args[2];

        String areaVal = area.getText().trim();
        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        if (areaVal.isEmpty() || rent.isEmpty() || buy.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng điền đầy đủ thông tin!",
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!ScannerUtil.validateDouble(areaVal, "Diện tích")) {
            return false;
        }

        if (ScannerUtil.spaceDouble(areaVal, 50, 90, "Diện tích")) {
            return false;
        }

        return priceValidate(rentPrice, buyPrice);
    }

    @Override
    public boolean editValidate(Object... args) {
        JTextField rentPrice = (JTextField) args[0];
        JTextField buyPrice = (JTextField) args[1];

        String rent = rentPrice.getText().trim();
        String buy = buyPrice.getText().trim();

        if (rent.isEmpty() || buy.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng điền đầy đủ thông tin trước khi cập nhật!",
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return priceValidate(rentPrice, buyPrice);
    }

    @Override
    public boolean searchValidate(Object... args) {
        JTextField fromArea = (JTextField) args[0];
        JTextField toArea = (JTextField) args[1];
        JTextField fromRentPrice = (JTextField) args[2];
        JTextField toRentPrice = (JTextField) args[3];
        JTextField fromBuyPrice = (JTextField) args[4];
        JTextField toBuyPrice = (JTextField) args[5];

        boolean isFromAreaEmpty = fromArea.getText().trim().isEmpty();
        boolean isToAreaEmpty = toArea.getText().trim().isEmpty();
        boolean isFromRentPriceEmpty = fromRentPrice.getText().trim().isEmpty();
        boolean isToRentPriceEmpty = toRentPrice.getText().trim().isEmpty();
        boolean isFromBuyPriceEmpty = fromBuyPrice.getText().trim().isEmpty();
        boolean isToBuyPriceEmpty = toBuyPrice.getText().trim().isEmpty();

        if ((fromArea.getText() != null && !isFromAreaEmpty &&
                !ScannerUtil.validateDouble(fromArea.getText().trim(), "Diện tích")) ||
                (toArea.getText() != null && !isToAreaEmpty &&
                        !ScannerUtil.validateDouble(toArea.getText().trim(), "Diện tích")) ||
                (fromRentPrice.getText() != null && !isFromRentPriceEmpty &&
                        !ScannerUtil.validateDouble(fromRentPrice.getText().trim(), "Giá thuê")) ||
                (toRentPrice.getText() != null && !isToRentPriceEmpty &&
                        !ScannerUtil.validateDouble(toRentPrice.getText().trim(), "Giá thuê")) ||
                (fromBuyPrice.getText() != null && !isFromBuyPriceEmpty &&
                        !ScannerUtil.validateDouble(fromBuyPrice.getText().trim(), "Giá mua")) ||
                (toBuyPrice.getText() != null && !isToBuyPriceEmpty &&
                        !ScannerUtil.validateDouble(toBuyPrice.getText().trim(), "Giá mua"))) {
            return false;
        }

        return !(
                !isFromAreaEmpty && !isToAreaEmpty &&
                    !ScannerUtil.validateRange(fromArea.getText().trim(), toArea.getText().trim(), "Diện tích") ||
                !isFromRentPriceEmpty && !isToRentPriceEmpty &&
                    !ScannerUtil.validateRange(fromRentPrice.getText().trim(), toRentPrice.getText().trim(), "Giá thuê") ||
                !isFromBuyPriceEmpty && !isToBuyPriceEmpty &&
                    !ScannerUtil.validateRange(fromBuyPrice.getText().trim(), toBuyPrice.getText().trim(), "Giá mua")
        );
    }

    @Override
    public void loadFilterApartment(Apartment apts,Integer toFloor, Integer toRoom, Double toArea,
                                                Double toRentPrice, Double toBuyPrice, JTable table) {

        List<Apartment> apartments = apartmentDAO.getApartmentsByFilter(apts, toFloor, toRoom, toArea,
                                                                        toRentPrice, toBuyPrice);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0);

        if( apartments.isEmpty() ) {
            JOptionPane.showMessageDialog(null,
                    "Không tìm thấy kết quả phù hợp" ,
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE
            );
        }
        addData(table, apartments);
    }
    
    public boolean selectImageAndSetToLabel(List<String> selectedImageNames, JLabel label) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh", "jpg", "png", "jpeg"));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                selectedImageNames.clear();
                selectedImageNames.add(selectedFile.getAbsolutePath());

                setImageToLabel(selectedFile.getAbsolutePath(), label);

                JOptionPane.showMessageDialog(null, "Đã chọn ảnh thành công!",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
            return true;
        }
        return false;
    }

    private void setImageToLabel(String imagePath, JLabel label) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image img = icon.getImage();

        int labelWidth = label.getWidth();
        int labelHeight = label.getHeight();

        if (labelWidth <= 0 || labelHeight <= 0) {
            labelWidth = label.getPreferredSize().width;
            labelHeight = label.getPreferredSize().height;
        }

        int imgWidth = icon.getIconWidth();
        int imgHeight = icon.getIconHeight();

        double widthRatio = (double) labelWidth / imgWidth;
        double heightRatio = (double) labelHeight / imgHeight;
        double ratio = Math.min(widthRatio, heightRatio); // chọn tỷ lệ nhỏ hơn để đảm bảo không tràn

        int newWidth = (int) (imgWidth * ratio);
        int newHeight = (int) (imgHeight * ratio);

        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(scaledImg));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);

        label.revalidate();
        label.repaint();
    }
    
    






}
