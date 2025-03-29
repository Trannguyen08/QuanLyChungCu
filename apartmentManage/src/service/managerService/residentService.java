
package service.managerService;
import com.toedter.calendar.JDateChooser;
import dao.managerDAO.ResidentDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Resident;
import util.ScannerUtil;

import java.util.List;
/**
 *
 * @author nghia
 */
public class residentService {
    private final ResidentDAO residentDAO = new ResidentDAO();

    // validate trùng số điện thoại và email
    public boolean isDuplicate(Resident resident, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String existingPhone = model.getValueAt(i, 3).toString();
            String existingEmail = model.getValueAt(i, 4).toString();
            String existingIdCard = model.getValueAt(i, 5).toString();
            int existingApartmentID = Integer.parseInt(model.getValueAt(i, 1).toString());

            if (existingPhone.equals(resident.getPhoneNumber()) || existingEmail.equals(resident.getEmail()) ||
                existingIdCard.equals(resident.getIdCard()) || existingApartmentID == resident.getApartmentID()){
                return true; 
            }
        }
        return false;
    }
    // thêm vào database
    public boolean addResident(Resident resident) {
        return residentDAO.addResident(resident);
    }

    // lấy dòng cuối để update table
    public Object[] getLastRow() {
        return residentDAO.getLastRow();
    }

    // lấy id
    public Integer getResidentId(JTable table) {
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
    public boolean deleteResident(int id) {
        return residentDAO.deleteResident(id);
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
    public boolean loadSelectedRowData(JTable table, JTextField apartmentID, JTextField fullName, JComboBox<String> gender, JDateChooser birthDate, 
                                        JTextField phoneNumber, JTextField email, JTextField idCard) throws ParseException {
        boolean error = errorNotifiaction(table);
        if( !error ) {
            return false;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        apartmentID.setText(model.getValueAt(selectedRow, 1).toString());
        fullName.setText(model.getValueAt(selectedRow, 2).toString());
        phoneNumber.setText(model.getValueAt(selectedRow, 3).toString());
        email.setText(model.getValueAt(selectedRow, 4).toString());
        idCard.setText(model.getValueAt(selectedRow, 5).toString());
        String dateStr = model.getValueAt(selectedRow, 6).toString(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date = sdf.parse(dateStr); // Gán vào JDateChooser
        birthDate.setDate(date);
        gender.setSelectedItem(model.getValueAt(selectedRow, 7).toString());

        return true;
    }

    // validate data
    public boolean validateData(JTable table, JTextField apartmentID, JTextField fullName,JTextField phoneNumber, JTextField email,
                                JTextField idCard, JDateChooser birthDate, JComboBox<String> gender) {

        String fName = fullName.getText().trim();
        String pNumber = phoneNumber.getText().trim();
        String rEmail = email.getText().trim();
        String rIdCard = idCard.getText().trim();
        Object rGender = gender.getSelectedItem().toString();
        int rApartmentID = Integer.parseInt(apartmentID.getText().trim());

        // Kiểm tra nếu apartmentID rỗng trước khi chuyển đổi
        rApartmentID = 0;
        if (!apartmentID.getText().trim().isEmpty()) {
            try {
                rApartmentID = Integer.parseInt(apartmentID.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Mã căn hộ phải là số nguyên hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Lấy ngày và định dạng
        Date selectedDate = birthDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";

        // Kiểm tra các giá trị có rỗng không
        if (fName.isEmpty() || pNumber.isEmpty() || rEmail.isEmpty() || rIdCard.isEmpty() || rGender == null || selectedDate == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        // Kiểm tra số điện thoại hợp lệ
        if (!ScannerUtil.validatePhoneNumber(pNumber)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có đúng 10 chữ số!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra email hợp lệ
        if (!ScannerUtil.validateEmail(rEmail)) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        return true;
    }

    public boolean updateResident(Resident resident) {
        return residentDAO.updateResident(resident);
    }
    public boolean validateSearchInput(JTextField residentID, JTextField apartmentID, JTextField fullName, JComboBox<String> gender,
                                JDateChooser birthDate, JDateChooser toBirthDate, JTextField phoneNumber,JTextField email, JTextField idCard) {
        // Kiểm tra giá trị nhập vào có hợp lệ không
        if ((!residentID.getText().trim().isEmpty() && !ScannerUtil.validateInteger(residentID.getText().trim(), "ID cư dân")) ||
           (!fullName.getText().trim().isEmpty() && !ScannerUtil.validateInteger(fullName.getText().trim(), "Họ tên cư dân")) ||
           (!phoneNumber.getText().trim().isEmpty() && !ScannerUtil.validatePhoneNumber(phoneNumber.getText().trim())) ||
           (!email.getText().trim().isEmpty() && !ScannerUtil.validateEmail(email.getText().trim())) ||
           (!idCard.getText().trim().isEmpty() && !ScannerUtil.validateInteger(idCard.getText().trim(), "CCCD")) ||
           (!apartmentID.getText().trim().isEmpty() && !ScannerUtil.validateInteger(apartmentID.getText().trim(), "Mã căn hộ"))) {
           return false;
        }

        // Kiểm tra ngày sinh hợp lệ
        if (birthDate.getDate() != null && toBirthDate.getDate() != null &&
           !ScannerUtil.validateDateRange(birthDate.getDate(), toBirthDate.getDate(), "Ngày sinh")) {
           return false;
        }
        return true;
    }
    public List<RowFilter<DefaultTableModel, Integer>> createFilters(JTextField residentID, JTextField apartmentID, JTextField fullName,JTextField phoneNumber,
                                                                    JTextField email, JTextField idCard){
        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

        if (!residentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + residentID.getText().trim(), 0));
        }
        if (!apartmentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentID.getText().trim(), 1)); 
        }
        if (!fullName.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + fullName.getText().trim(), 2));
        }
        if (!phoneNumber.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + phoneNumber.getText().trim(), 3));
        }
        if (!email.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + email.getText().trim(), 4));
        }
        if (!idCard.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + idCard.getText().trim(), 5)); 
        }

        return filters;
    }


}