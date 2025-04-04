package main.java.utc2_apartmentManage.service.managerService;

import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.utc2_apartmentManage.repository.managerRepository.employeeRepository;
import main.java.utc2_apartmentManage.util.ScannerUtil;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import main.java.utc2_apartmentManage.model.Employee;


public class employeeService {
    private final employeeRepository employeeDAO = new employeeRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
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
    
    public int getNewID() {
        return employeeDAO.getIDMinNotExist();
    }
    
    public void setupEmployeeTable(JTable table) {
        List<Employee> employList = employeeDAO.getAllEmployee();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        
        for( Employee emp : employList ) {
            model.addRow(new Object[] {emp.getId(), emp.getName(), emp.getGender(), emp.getPhoneNumber(),
                                        emp.getEmail(), emp.getHiringDate(), emp.getPosition(),
                                        df.format(emp.getSalary()), emp.getStatus()});
        }

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
                                        JComboBox<String> status) {
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
        
        String dateStr = model.getValueAt(selectedRow, 5).toString();        
        ScannerUtil.setDateChooserFromString(hiringDate, dateStr);
        
        position.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
        
        String salaryText = model.getValueAt(selectedRow, 7).toString().replace(".", "");
        salaryText = salaryText.replace(",", ".");
        salary.setText(salaryText);
        
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
        Date selectedDate = hiringDate.getDate();
        Date today = new Date();
        String sal = salary.getText().trim();

        if (eGender == null || ePosition == null || eStatus == null ||
            fName.isEmpty() || pNumber.isEmpty() || eEmail.isEmpty() || 
            sal.isEmpty() || selectedDate == null) {
            
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin trước khi thêm!", 
                                            "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if( !ScannerUtil.isValidFullName(fName) ) {
            return false;
        }
        
        if( selectedDate.after(today) ) {
            JOptionPane.showMessageDialog(null, "Ngày tuyển dụng không thể lớn hơn ngày hôm nay", 
                                            "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if( !ScannerUtil.validateDouble(sal, "Lương" ) ) {
            return false;
        } else { 
            double salaryValue = Double.parseDouble(salary.getText().trim());
            if (salaryValue < 3000000 || salaryValue > 20000000) {
                JOptionPane.showMessageDialog(null, "Lương nhân viên phải nằm trong khoảng từ 3 triệu đến 20 triệu", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } 

        if (!ScannerUtil.validatePhoneNumber(pNumber)) {
            return false;
        }

        if (!ScannerUtil.validateEmail(eEmail)) {
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

        
        if ((!employeeID.getText().trim().isEmpty() && !ScannerUtil.validateInteger(employeeID.getText().trim(), "ID nhân viên")) ||
            (!fullName.getText().trim().isEmpty() && !ScannerUtil.isValidFullName(fullName.getText().trim())) ||
            (!salary.getText().trim().isEmpty() && !ScannerUtil.validateDouble(salary.getText().trim(), "Lương tối thiểu")) ||
            (!toSalary.getText().trim().isEmpty() && !ScannerUtil.validateDouble(toSalary.getText().trim(), "Lương tối đa")) ||
            (!phoneNumber.getText().trim().isEmpty() && !ScannerUtil.validatePhoneNumber(phoneNumber.getText().trim())) ||
            (!email.getText().trim().isEmpty() && !ScannerUtil.validateEmail(email.getText().trim()))) {
            return false;
        }

        
        if ((!salary.getText().trim().isEmpty() && !toSalary.getText().trim().isEmpty() &&
             !ScannerUtil.validateRange(salary.getText().trim(), toSalary.getText().trim(), "Lương")) ||
            (hiringDate.getDate() != null && toHiringDate.getDate() != null &&
             !ScannerUtil.validateDateRange(hiringDate.getDate(), toHiringDate.getDate(), "Ngày tuyển dụng"))) {
            return false;
        }

        return true;
    }
    
    public void updateTable(String keyword, JTable table) {
        List<Employee> emps = employeeDAO.getFilteredEmployeeByKeyword(keyword);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0); 

        if (emps.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for( Employee emp : emps ) {
            model.addRow(new Object[]{
                emp.getId(), emp.getName(), emp.getGender(), emp.getPhoneNumber(),
                emp.getEmail(), emp.getHiringDate(), emp.getPosition(),
                df.format(emp.getSalary()), emp.getStatus()
            });
        }
    }

    


}
