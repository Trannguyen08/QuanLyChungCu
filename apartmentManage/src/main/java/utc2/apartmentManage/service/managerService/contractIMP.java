package main.java.utc2.apartmentManage.service.managerService;


import com.toedter.calendar.JDateChooser;
import main.java.utc2.apartmentManage.model.Contract;
import main.java.utc2.apartmentManage.repository.ManagerRepository.contractRepository;
import main.java.utc2.apartmentManage.service.Interface.*;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;


public class contractIMP implements ISQL<Contract>, ITable<Contract>, IValidate, IContract {
    private final contractRepository contractDAO = new contractRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    // Xác nhận xóa
    @Override
    public boolean confirmDelete(String s) {
        return ScannerUtil.comfirmWindow(s);
    }
    
    @Override
    public boolean isAcceptedDelete(int id) {
        return contractDAO.isNotValidContract(id);
    }

    @Override
    public boolean searchValidate(Object... args) {

        JTextField fromValue = (JTextField) args[0];
        JTextField toValue = (JTextField) args[1];
        JDateChooser startDate = (JDateChooser) args[2];
        JDateChooser endDate = (JDateChooser) args[3];

        // Kiểm tra giá trị hợp đồng
        if (   (fromValue.getText() != null && !fromValue.getText().trim().isEmpty() &&
                !ScannerUtil.validateDouble(fromValue.getText().trim(), "Giá trị hợp đồng")) ||
                (toValue.getText() != null && !toValue.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toValue.getText().trim(), "Giá trị hợp đồng")) ) {
            return false;
        }

        // Kiểm tra khoảng giá trị hợp đồng
        if (  !fromValue.getText().trim().isEmpty() && !toValue.getText().trim().isEmpty() &&
                !ScannerUtil.validateRange(fromValue.getText().trim(), toValue.getText().trim(), "Giá trị hợp đồng") ) {
            return false;
        }

        // Kiểm tra ngày bắt đầu và ngày kết thúc
        if (startDate.getDate() != null && endDate.getDate() != null) {
            Date start = startDate.getDate();
            Date end = endDate.getDate();

            if (start.after(end)) {
                JOptionPane.showMessageDialog(null, "Ngày bắt đầu không được lớn hơn ngày kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return true;
    }
    
    @Override
    public boolean filterContracts(Contract contract, String startDate, String endDate,
                                   double fromValue, double toValue, JTable table){
    
        List<Contract> contractList = contractDAO.getFilteredContracts(contract, startDate, endDate, fromValue, toValue);
        addData(table, contractList);
        return true;
    } 
    
    @Override
    public void updateTableWithContracts(String keyword, JTable table) {

    }
    
    public boolean checkAllNull(JTextField ownerName, JTextField fromValue, JTextField toValue,
                                       JDateChooser startDate, JDateChooser endDate, 
                                        JComboBox<String> contractStatus, JComboBox<String> contractType ) {

        if (ownerName.getText().isEmpty() && fromValue.getText().isEmpty() && toValue.getText().isEmpty() 
                && contractStatus.getSelectedItem().toString().trim().isEmpty()
                && contractType.getSelectedItem().toString().trim().isEmpty()
                && startDate.getDate() == null && endDate.getDate() == null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        return contractDAO.deleteContract(id);
    }

    @Override
    public void setUpTable(JTable table) {
        List<Contract> list = contractDAO.getAllContract();
        addData(table, list);
        setFont(table);
    }

    @Override
    public void addData(JTable table, List<Contract> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for( Contract contract : list ) {
            model.addRow(new Object[] {contract.getId(), contract.getOwnerName(), contract.getApartmentIndex(),
                    contract.getContractType(), contract.getStartDate(), contract.getEndDate(),
                    df.format(contract.getContractValue()), contract.getContractStatus()});
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
                    "Vui lòng chọn một dòng để xóa",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE
            );
            return false;
        }
        return true;
    }

    @Override
    public boolean addContract(Contract object, int aptID, int resID) { return contractDAO.addContract(object, aptID, resID); }

    @Override
    public boolean update(Contract object) { return false; }

    @Override
    public boolean isExist(int id) { return false; }

    @Override
    public boolean isDuplicate(Contract object) { return false; }

    @Override
    public int getNewID() { return contractDAO.getIDMinNotExist(); }

    @Override
    public Contract getObject(int id) { return null; }
    
    @Override
    public boolean addValidate(Object... obj) { return false; }

    @Override
    public boolean editValidate(Object... obj) { return false; }

    @Override
    public boolean add(Contract object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
