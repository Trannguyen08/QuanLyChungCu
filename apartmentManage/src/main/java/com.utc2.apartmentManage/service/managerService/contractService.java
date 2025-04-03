package main.java.com.utc2.apartmentManage.service.managerService;


import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import main.java.com.utc2.apartmentManage.model.Contract;
import main.java.com.utc2.apartmentManage.repository.managerRepository.contractRepository;
import main.java.com.utc2.apartmentManage.util.ScannerUtil;


public class contractService {
    private final contractRepository contractDAO = new contractRepository();

    public boolean checkSelectRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean checkContractStatus(JTable table) {
        boolean checkSelect = checkSelectRow(table);
        if( !checkSelect ) {
            return false;
        }

        int selectedRow = table.getSelectedRow();
        int id = (int) table.getModel().getValueAt(selectedRow, 0);
        boolean checkValidContract = contractDAO.isNotValidContract(id);
        if( checkValidContract ) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Không thể xóa hợp đồng còn hiệu lực", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

    }

    public boolean confirmDelete() {
        int confirm = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn xóa hàng này?",
                "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }

    public boolean deleteContract(int id) {
        return contractDAO.deleteContract(id);
    }

    // load dữ liệu vào bảng
    public void setupContractTable(JTable table) {
        List<Contract> contractList = contractDAO.getAllContract();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for( Contract contract : contractList ) {
            model.addRow(new Object[] {contract.getId(), contract.getOwnerName(), contract.getApartmentIndex(),
                    contract.getContractType(), contract.getStartDate(), contract.getEndDate(),
                    contract.getContractValue(), contract.getContractStatus()});
        }

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    public String refactorName(String name) {
        String[] nameArr = name.trim().split("\\s+"); 
        StringBuilder newName = new StringBuilder();
        for (String s : nameArr) {
            if (!s.isEmpty()) {
                newName.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase()).append(" ");
            }
        }
        return newName.toString().trim();
    }

    public boolean validateSeachInput(JTextField contractID, JTextField ownerName, JTextField fromValue,
                                      JTextField toValue, JDateChooser startDate, JDateChooser endDate,
                                      JComboBox<String> contractType) {

        if (    (contractID.getText() != null && !contractID.getText().trim().isEmpty() &&
                        !ScannerUtil.validateInteger(contractID.getText().trim(), "ID hợp đồng")) ||
                (fromValue.getText() != null && !fromValue.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fromValue.getText().trim(), "Giá trị hợp đồng")) ||
                (toValue.getText() != null && !toValue.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toValue.getText().trim(), "Giá trị hợp đồng")) ) {
            
            return false;
        }

        if (  !fromValue.getText().trim().isEmpty() && !toValue.getText().trim().isEmpty() &&
              !ScannerUtil.validateRange(fromValue.getText().trim(), toValue.getText().trim(), "Giá trị hợp đồng") ) {
            
            return false;
        }

        if( startDate.getDate() != null && endDate.getDate() != null ) {
            Date start = startDate.getDate();
            Date end = endDate.getDate();

            if (start.after(end)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được lớn hơn ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            String contractTypeSelected = contractType.getSelectedItem().toString();

            // Chuyển Date thành LocalDate để dễ kiểm tra khoảng cách
            LocalDate startLocal = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endLocal = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long daysBetween = ChronoUnit.DAYS.between(startLocal, endLocal); 

            if ("Cho thuê".equalsIgnoreCase(contractTypeSelected) && daysBetween < 30) {
                JOptionPane.showMessageDialog(null, "Hợp đồng cho thuê phải có thời gian tối thiểu 30 ngày!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            if ("Mua bán".equalsIgnoreCase(contractTypeSelected) && daysBetween < (50 * 365)) {
                JOptionPane.showMessageDialog(null, "Hợp đồng mua bán phải có thời gian tối thiểu 50 năm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }
    
    public boolean filterContracts(Contract contract, JDateChooser startDate, 
                                          JDateChooser endDate, double fromValue, double toValue, JTable table){
        
        List<Contract> contractList = contractDAO.getFilteredContracts(contract, startDate, endDate, fromValue, toValue);
          
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0);
        
        for( Contract c : contractList ) {
            model.addRow(new Object[]{
                c.getId(),
                c.getOwnerName(),
                c.getApartmentIndex(),  
                c.getContractType(),
                c.getStartDate(),
                c.getEndDate(),
                c.getContractValue(),
                c.getContractStatus()
            });
        }
        return true;
    } 
    
    public void updateTableWithContracts(String keyword, JTable table) {
        List<Contract> contractList = contractDAO.getFilteredContractsByKeyword(keyword);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);
        model.setRowCount(0); 

        if (contractList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy hợp đồng nào!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for( Contract c : contractList ) {
            model.addRow(new Object[]{
                c.getId(),
                c.getOwnerName(),
                c.getApartmentIndex(),
                c.getContractType(),
                c.getStartDate(),
                c.getEndDate(),
                c.getContractValue(),
                c.getContractStatus()
            });
        }
    }
    
    public boolean checkAllNull(JTextField id, JTextField ownerName, JTextField fromValue, JTextField toValue,
                                       JDateChooser startDate, JDateChooser endDate, 
                                        JComboBox<String> contractStatus, JComboBox<String> contractType ) {

        if (ownerName.getText().isEmpty() || fromValue.getText().isEmpty() || toValue.getText().isEmpty() 
                || contractStatus.getSelectedItem().toString().trim().isEmpty()
                || contractType.getSelectedItem().toString().trim().isEmpty() ) {
            return true;
        }

        if( id.getText().trim().isEmpty() ) {
            return true;
        }

        Date start = startDate.getDate();
        Date end = endDate.getDate();
        if (start == null || end == null) {
            return true;
        }

        return false;
    }


}
