package java.utc2.apartmentManage.service.implement.manager;

import com.toedter.calendar.JDateChooser;
import utc2.apartmentManage.model.*;
import utc2.apartmentManage.repository.manager.*;
import utc2.apartmentManage.service.implement.login.loginIMP;
import utc2.apartmentManage.service.interfaces.*;
import utc2.apartmentManage.util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class residentIMP implements ISQL<Resident>, ITable<Resident>, IValidate, IResident {
    private final residentRepository residentRepository = new residentRepository();
    private final loginIMP loginService = new loginIMP();
    
    // Kiểm tra hợp đồng còn hiệu lực
    @Override
    public boolean isStillContract(int id) {
        return residentRepository.isStillContract(id);
    }
    
    // Xác nhận xóa cư dân
    @Override
    public boolean confirmDelete(String s) {
        return ScannerUtil.comfirmWindow(s);
    }
    
    // Lấy ID cư dân từ bảng
    @Override
    public Integer getResidentId(JTable table) {
        if (!isSelectedRow(table)) {
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

    @Override
    public boolean add(Resident object) {
        return residentRepository.addResident(object);
    }
    
    @Override
    public int getNewID() {
        return residentRepository.getIDMinNotExist(); 
    }

    @Override
    public boolean delete(int id) {
        return residentRepository.deleteResident(id);
    }

    @Override
    public boolean isDuplicate(Resident object) {
        int ans = residentRepository.isDuplicateResident(object);
        
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

    @Override
    public void setUpTable(JTable table) {
        List<Resident> list = residentRepository.getAllResident();
        addData(table, list);
        setFont(table);
    }

    @Override
    public void addData(JTable table, List<Resident> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0);

        for (Resident resident : list) {
            model.addRow(new Object[]{
                    resident.getResidentID(),
                    resident.getName(),
                    resident.getGender(),
                    resident.getBirthDate(),
                    resident.getPhoneNumber(),
                    resident.getIdCard(),
                    resident.getEmail(),
                    resident.getContractStatus()
            });
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
                    "Vui lòng chọn một dòng",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE
            );
            return false;
        }
        return true;
    }
    
    private void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void filterResident(JTable table, Resident resident, String toDate) {
        List<Resident> list = residentRepository.getAllFilterResident(resident, toDate);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0);

        if( list.isEmpty() ) {
            JOptionPane.showMessageDialog(null,
                    "Không tìm thấy kết quả phù hợp" ,
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        addData(table, list);
    }

    @Override
    public boolean addValidate(Object... obj) {
        JTextField usernameField = (JTextField) obj[0];
        JTextField passwordField = (JTextField) obj[1];
        JTextField fullNameField = (JTextField) obj[2];
        JTextField phoneField = (JTextField) obj[3];
        JTextField idCardField = (JTextField) obj[4];
        JTextField emailField = (JTextField) obj[5];
        JTextField apartmentIdField = (JTextField) obj[6];
        JDateChooser birthDateChooser = (JDateChooser) obj[7];
        JDateChooser startDateChooser = (JDateChooser) obj[8];

        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String fullName = fullNameField.getText().trim();
        String phoneNumber = phoneField.getText().trim();
        String idCard = idCardField.getText().trim();
        String email = emailField.getText().trim();
        String apartmentId = apartmentIdField.getText().trim();
        Date birthDate = birthDateChooser.getDate();
        Date startDate = startDateChooser.getDate();
        
        

        // Kiểm tra các trường không được để trống
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty() ||
            phoneNumber.isEmpty() || idCard.isEmpty() || email.isEmpty() || apartmentId.isEmpty() ||
            birthDate == null || startDate == null) {
            showErrorMessage("Vui lòng điền đầy đủ tất cả thông tin!", "Lỗi nhập liệu");
            return false;
        }

        // Kiểm tra username hợp lệ
        if (!ScannerUtil.isValidUsername(username)) {
            return false;
        }
        
        List<Account> list = loginService.gettAllAccount();
        boolean isDuplicate = list.stream()
                                .anyMatch(acc -> acc.getUsername().equalsIgnoreCase(username));

        if (isDuplicate) {
            JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại!", 
                    "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra password hợp lệ
        if (!ScannerUtil.isValidPassword(password)) {
            return false;
        }

        // Kiểm tra họ tên hợp lệ
        if (!ScannerUtil.isValidFullName(fullName)) {
            return false;
        }

        // Kiểm tra số điện thoại hợp lệ
        if (!ScannerUtil.validatePhoneNumber(phoneNumber)) {
            return false;
        }

        // Kiểm tra CCCD hợp lệ
        if (!ScannerUtil.isValidCCCD(idCard)) {
            return false;
        }

        // Kiểm tra email hợp lệ
        if (!ScannerUtil.validateEmail(email)) {
            return false;
        }

        // Kiểm tra ID căn hộ là số nguyên dương
        if (!ScannerUtil.validateInteger(apartmentId, "ID căn hộ")) {
            return false;
        }
        List<Apartment> apartments = new apartmentRepository().getAllApartment();
        int aptID = Integer.parseInt(apartmentId);

        boolean exists = apartments.stream()
                .anyMatch(apt -> apt.getId() == aptID);

        if (!exists) {
            JOptionPane.showMessageDialog(null, "ID căn hộ không tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Apartment a = new apartmentIMP().getObject(aptID);
        if( a.getStatus().equals("Đã thuê") || a.getStatus().equals("Đã bán") ) {
            JOptionPane.showMessageDialog(null, "Căn hộ đã có chủ sở hữu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        

        // Kiểm tra tuổi >= 18
        if (!ScannerUtil.isValidAge(birthDate)) {
            return false;
        }

        // Kiểm tra ngày bắt đầu >= ngày sinh
        if (ScannerUtil.isBeforeToday(startDate)) {
            showErrorMessage("Ngày bắt đầu không được trước ngày hôm nay!", "Lỗi nhập liệu");
            return false;
        }

        return true;
    }

    @Override
    public boolean searchValidate(Object... obj) {
        JDateChooser birthdayChooser = (JDateChooser) obj[0];
        JDateChooser toBirthdayChooser = (JDateChooser) obj[1];

        Date birthday = null;
        Date toBirthday = null;

        try {
            birthday = birthdayChooser.getDate();
            if (birthdayChooser.getDateEditor().getUiComponent() instanceof JTextField) {
                String text = ((JTextField) birthdayChooser.getDateEditor().getUiComponent()).getText().trim();
                if (!text.isEmpty() && birthday == null) {
                    showErrorMessage("Ngày sinh không hợp lệ!", "Lỗi nhập liệu");
                    return false;
                }
            }
        } catch (Exception e) {
            showErrorMessage("Ngày sinh không hợp lệ!", "Lỗi nhập liệu");
            return false;
        }

        try {
            toBirthday = toBirthdayChooser.getDate();
            if (toBirthdayChooser.getDateEditor().getUiComponent() instanceof JTextField) {
                String text = ((JTextField) toBirthdayChooser.getDateEditor().getUiComponent()).getText().trim();
                if (!text.isEmpty() && toBirthday == null) {
                    showErrorMessage("Ngày sinh kết thúc không hợp lệ!", "Lỗi nhập liệu");
                    return false;
                }
            }
        } catch (Exception e) {
            showErrorMessage("Ngày sinh kết thúc không hợp lệ!", "Lỗi nhập liệu");
            return false;
        }

        // Nếu cả hai ngày đều có giá trị thì kiểm tra birthday < toBirthday
        if (birthday != null && toBirthday != null) {
            if (birthday.after(toBirthday)) {
                showErrorMessage("Ngày sinh phải nhỏ hơn ngày kết thúc!", "Lỗi nhập liệu");
                return false;
            }
        }
        return true;
    }
    
    @Override
    public boolean editValidate(Object... obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public Resident getObject(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public boolean update(Resident object) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean isExist(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    public String getApartmentInfo(int aptID) {
        return residentRepository.getApartmentInfo(aptID);
    }
}
