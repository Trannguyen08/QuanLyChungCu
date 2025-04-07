package main.java.utc2_apartmentManage.service.managerService;

import com.toedter.calendar.JDateChooser;
import main.java.utc2_apartmentManage.repository.managerRepository.residentRepository;
import main.java.utc2_apartmentManage.util.ScannerUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.utc2_apartmentManage.model.Resident;


public class residentService {
    private final residentRepository residentRepository = new residentRepository();

    // Update cư dân
    public boolean updateResident(Resident resident) {
        return residentRepository.updateResident(resident);
    }

    // Xóa cư dân khỏi database
    public boolean deleteResident(int id) {
        return residentRepository.deleteResident(id);
    }

    // Kiểm tra hợp đồng còn hiệu lực
    public boolean isStillContract(int id) {
        return residentRepository.isStillContract(id);
    }
    
    // Hiển thị thông báo lỗi
    private void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    // Xác nhận xóa cư dân
    public boolean confirmDelete(String s) {
        return ScannerUtil.comfirmWindow(s);
    }
    
    // check trùng
    public boolean isDuplicate(Resident resident) {
        
        int ans = residentRepository.isDuplicateResident(resident);
        
        switch(ans) {
            case 1 -> {
                JOptionPane.showMessageDialog(null, "Mã căn hộ này đã thuộc về cư dân khác.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            case 2 -> {
                JOptionPane.showMessageDialog(null, "Email này đã tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            case 3 -> {
                JOptionPane.showMessageDialog(null, "Số điện thoại này đã tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;
            }
            case 4 -> {
                JOptionPane.showMessageDialog(null, "CCCD này đã tồn tại.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return false;            
            }
        }
        
        return true; 
    }


    // Thiết lập dữ liệu bảng cư dân
    public void setupResidentTable(JTable table) {
        List<Resident> residentList = residentRepository.getAllResident();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        
        boolean dataLoaded = addDataToTable(residentList, table);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    // Thêm dữ liệu cư dân vào bảng
    public boolean addDataToTable(List<Resident> residentList, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0);

        for (Resident resident : residentList) {
            model.addRow(new Object[]{
                    resident.getResidentID(),
                    resident.getApartmentID(),
                    resident.getName(),
                    resident.getGender(),
                    resident.getBirthDate(),
                    resident.getPhoneNumber(),
                    resident.getIdCard(),
                    resident.getEmail()
            });
        }
        return true;
    }

    // Lấy ID cư dân từ bảng
    public Integer getResidentId(JTable table) {
        if (!isRowSelected(table)) {
            return null;
        }

        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object value = model.getValueAt(selectedRow, 0);
        try {
            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e) {
            showErrorMessage("ID không hợp lệ!", "Lỗi");
            return null;
        }
    }

    // Kiểm tra xem có dòng nào được chọn trong bảng không
    public boolean isRowSelected(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showErrorMessage("Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo");
            return false;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            showErrorMessage("Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi");
            return false;
        }
        return true;
    }

    // Gán dữ liệu dòng đã chọn vào các trường nhập liệu
    public boolean loadSelectedRowData(JTable table, JTextField apartmentID, JTextField fullName, JComboBox<String> gender, JDateChooser birthDate,
                                        JTextField phoneNumber, JTextField email, JTextField idCard) {
        if (!isRowSelected(table)) {
            return false;
        }

        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        apartmentID.setText(model.getValueAt(selectedRow, 1).toString());
        fullName.setText(model.getValueAt(selectedRow, 2).toString());
        gender.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        String dateStr = model.getValueAt(selectedRow, 4).toString();
        ScannerUtil.setDateChooserFromString(birthDate, dateStr);
        phoneNumber.setText(model.getValueAt(selectedRow, 5).toString());
        idCard.setText(model.getValueAt(selectedRow, 6).toString());
        email.setText(model.getValueAt(selectedRow, 7).toString());

        return true;
    }

    // Kiểm tra và validate dữ liệu nhập vào
    public boolean validateData(JTable table, JTextField apartmentID, JTextField fullName, JTextField phoneNumber, JTextField email,
                                JTextField idCard, JDateChooser birthDate, JComboBox<String> gender) {

        if (!ScannerUtil.validateInteger(apartmentID.getText().trim(), "ID Căn hộ")) {
            return false;
        }

        String fName = fullName.getText().trim();
        String pNumber = phoneNumber.getText().trim();
        String rEmail = email.getText().trim();
        String rIdCard = idCard.getText().trim();
        Object rGender = gender.getSelectedItem();
        Date selectedDate = birthDate.getDate();

        if (fName.isEmpty() || pNumber.isEmpty() || rEmail.isEmpty() || rIdCard.isEmpty() || rGender == null || selectedDate == null) {
            showErrorMessage("Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu");
            return false;
        }

        if (!ScannerUtil.isValidFullName(fName)) {
            return false;
        }

        if (!ScannerUtil.isValidAge(selectedDate)) {
            showErrorMessage("Cư dân chưa đủ 18 tuổi!", "Lỗi nhập liệu");
            return false;
        }

        if (!ScannerUtil.validatePhoneNumber(pNumber)) {
            return false;
        }

        if (!ScannerUtil.validateEmail(rEmail)) {
            return false;
        }

        if (!ScannerUtil.isValidCCCD(rIdCard)) {
            return false;
        }

        return true;
    }

    // Kiểm tra dữ liệu tìm kiếm
    public boolean validateSearchInput(JTextField residentID, JTextField apartmentID, JDateChooser birthDate, 
                                    JDateChooser toBirthDate, JTextField phoneNumber, JTextField email, JTextField idCard) {

        if (!residentID.getText().trim().isEmpty() && 
            !ScannerUtil.validateInteger(residentID.getText().trim(), "ID cư dân")) {
            return false;
        }

        if (!phoneNumber.getText().trim().isEmpty() && 
            !ScannerUtil.validatePhoneNumber(phoneNumber.getText().trim())) {
            return false;
        }

        if (!email.getText().trim().isEmpty() && 
            !ScannerUtil.validateEmail(email.getText().trim())) {
            return false;
        }

        if (!idCard.getText().trim().isEmpty() && 
            !ScannerUtil.isValidCCCD(idCard.getText().trim())) {
            return false;
        }

        if (!apartmentID.getText().trim().isEmpty() && 
            !ScannerUtil.validateInteger(apartmentID.getText().trim(), "Mã căn hộ")) {
            return false;
        }

        // Kiểm tra ngày sinh hợp lệ

        return !(birthDate.getDate() != null && toBirthDate.getDate() != null &&
                !ScannerUtil.validateDateRange(birthDate.getDate(), toBirthDate.getDate(), "Ngày sinh"));
    }
}

