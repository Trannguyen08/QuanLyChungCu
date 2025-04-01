
package main.java.com.utc2.apartmentManage.service.managerService;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.com.utc2.apartmentManage.repository.managerRepository.employeeRepository;
import util.ScannerUtil;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import main.java.com.utc2.apartmentManage.model.Contract;
import main.java.com.utc2.apartmentManage.model.Employee;

public class employeeService {
    private final employeeRepository employeeDAO = new employeeRepository();

    // validate trùng số điện thoại và email
    public boolean isDuplicate(Employee employee, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String existingPhone = model.getValueAt(i, 3).toString();
            String existingEmail = model.getValueAt(i, 4).toString();

            if (existingPhone.equals(employee.getPhoneNumber()) || existingEmail.equals(employee.getEmail())) {
                return true; 
            }
        }
        return false;
    }
    
    public void setupContractTable(JTable table) {
        //List<Contract> contractList = contractDAO.getAllContract();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        /*
        for( Contract contract : contractList ) {
            model.addRow(new Object[] {contract.getId(), contract.getOwnerName(), contract.getApartmentIndex(),
                    contract.getContractType(), contract.getStartDate(), contract.getEndDate(),
                    contract.getContractValue(), contract.getContractStatus()});
        }*/

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    // thêm vào database
    public boolean addEmployee(Employee employee) {
        return employeeDAO.addEmployee(employee);
    }

    // lấy dòng cuối để update table
    public Object[] getLastRow() {
        return employeeDAO.getLastRow();
    }

    // lấy id
    public Integer getEmployeeId(JTable table) {
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
    public boolean deleteEmployee(int id) {
        return employeeDAO.deleteEmployee(id);
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
    public boolean loadSelectedRowData(JTable table, JTextField fullName, JComboBox<String> gender, JTextField phoneNumber, 
                                        JTextField email, JComboBox<String> position, JTextField salary, JDateChooser hiringDate, 
                                        JComboBox<String> status) throws ParseException {
        boolean error = errorNotifiaction(table);
        if( !error ) {
            return false;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        fullName.setText(model.getValueAt(selectedRow, 1).toString());
        gender.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        phoneNumber.setText(model.getValueAt(selectedRow, 3).toString());
        email.setText(model.getValueAt(selectedRow, 4).toString());
        position.setSelectedItem(model.getValueAt(selectedRow, 5).toString());
        salary.setText(model.getValueAt(selectedRow, 6).toString());
        String dateStr = model.getValueAt(selectedRow, 7).toString(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        Date date = sdf.parse(dateStr); // Gán vào JDateChooser
        hiringDate.setDate(date);
        status.setSelectedItem(model.getValueAt(selectedRow, 8).toString());

        return true;
    }

    // validate data
    public boolean validateData(JTable table, JTextField fullName, JComboBox<String> gender, JTextField phoneNumber, 
                                JTextField email, JComboBox<String> position, JTextField salary, JDateChooser hiringDate, 
                                JComboBox<String> status) {

        String fName = fullName.getText().trim();
        Object eGender = gender.getSelectedItem().toString();
        String pNumber = phoneNumber.getText().trim();
        String eEmail = email.getText().trim();
        Object ePosition = position.getSelectedItem().toString();
        Object eStatus = status.getSelectedItem().toString();

        // Lấy ngày và định dạng
        Date selectedDate = hiringDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";

        double salaryValue = Double.parseDouble(salary.getText().trim());

        // Kiểm tra các giá trị có rỗng không
        if (eGender == null || ePosition == null || eStatus == null ||
            fName.isEmpty() || pNumber.isEmpty() || eEmail.isEmpty() || 
            salary.getText().trim().isEmpty() || formattedDate == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin trước khi cập nhật!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra lương hợp lệ
        try {
            salaryValue = Double.parseDouble(salary.getText().trim());
            if (salaryValue <= 0) {
                JOptionPane.showMessageDialog(null, "Lương phải lớn hơn 0!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lương phải là một số hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra số điện thoại hợp lệ
        if (!ScannerUtil.validatePhoneNumber(pNumber)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải có đúng 10 chữ số!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra email hợp lệ
        if (!ScannerUtil.validateEmail(eEmail)) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean updateEmployee(Employee employee) {
        return employeeDAO.updateEmployee(employee);
    }
    public boolean validateSearchInput(JTextField employeeID, JTextField fullName, JComboBox<String> gender,
                                       JTextField phoneNumber, JTextField email, JComboBox<String> position,
                                       JTextField salary, JDateChooser hiringDate, JComboBox<String> status,
                                       JTextField toSalary, JDateChooser toHiringDate) {
        // Kiểm tra giá trị nhập vào có hợp lệ không
        if ((!employeeID.getText().trim().isEmpty() && !ScannerUtil.validateInteger(employeeID.getText().trim(), "ID nhân viên")) ||
            (!fullName.getText().trim().isEmpty() && !ScannerUtil.validateInteger(fullName.getText().trim(), "Họ tên nhân viên")) ||
            (!salary.getText().trim().isEmpty() && !ScannerUtil.validateDouble(salary.getText().trim(), "Lương")) ||
            (!toSalary.getText().trim().isEmpty() && !ScannerUtil.validateDouble(toSalary.getText().trim(), "Lương tối đa")) ||
            (!phoneNumber.getText().trim().isEmpty() && !ScannerUtil.validatePhoneNumber(phoneNumber.getText().trim())) ||
            (!email.getText().trim().isEmpty() && !ScannerUtil.validateEmail(email.getText().trim()))) {
            return false;
        }

        // Kiểm tra giá trị trong phạm vi hợp lệ
        if ((!salary.getText().trim().isEmpty() && !toSalary.getText().trim().isEmpty() &&
             !ScannerUtil.validateRange(salary.getText().trim(), toSalary.getText().trim(), "Lương")) ||
            (hiringDate.getDate() != null && toHiringDate.getDate() != null &&
             !ScannerUtil.validateDateRange(hiringDate.getDate(), toHiringDate.getDate(), "Ngày tuyển dụng"))) {
            return false;
        }

        return true;
    }

    


}
