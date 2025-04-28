package main.java.utc2_apartmentManage.service.managerService;

import java.awt.Font;
import java.text.NumberFormat;
import main.java.utc2_apartmentManage.repository.managerRepository.serviceRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import main.java.utc2_apartmentManage.model.Service;
import main.java.utc2_apartmentManage.util.ScannerUtil;

public class serviceService {
    private final serviceRepository serviceRepository = new serviceRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public int getNewID() {
        return serviceRepository.getIDMinNotExist();
    }
    
    public boolean isDuplicate(Service service) {
        return serviceRepository.isDuplicate(service);
    } 
    
    public boolean addService(Service service) {
        return serviceRepository.addService(service);
    }
    
    public boolean deleteService(int id) {
        return serviceRepository.deleteService(id);
    }

    public boolean confirmDelete(String s) {
        return ScannerUtil.comfirmWindow(s);
    }
    
    public boolean updateService(Service service) {
        return serviceRepository.updateService(service);
    }
    
    public boolean isDuplicate(Service service, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(service.getServiceName()) &&
                    model.getValueAt(i, 2).equals(service.getServiceType())) {
                return true;
            }
        }
        return false;
    }
    
    public void addToTable(List<Service> services, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Service service : services) {
            model.addRow(new Object[]{
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getServiceType(),
                    df.format(service.getPrice()),
                    service.getUnit(),
                    service.getDescription()
            });
        }
    }
    
    public void setupServiceTable(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        List<Service> list = serviceRepository.getAllServices();

        addToTable(list, table);

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    public boolean loadServicesIntoTable(String keyword, JTable table) {
        List<Service> services = serviceRepository.getFilteredServiceByKeyword(keyword);
        addToTable(services, table);
        return !services.isEmpty(); 
    }

    public Integer getServiceId(JTable table) {
        boolean check = errorNotification(table);
        if( !check ) {
            return null;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        return Integer.valueOf(model.getValueAt(selectedRow, 0).toString());
    }
    
    public String getServiceName(JTable table) {
        boolean check = errorNotification(table);
        if( !check ) {
            return null;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        return model.getValueAt(selectedRow, 1).toString();
    }

    public boolean errorNotification(JTable table) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean loadSelectedRowData(JTable table, JTextField serviceName, JComboBox<String> serviceType,
                                       JTextField price, JTextField unit, JTextArea description) {
        boolean check = errorNotification(table);
        if( !check ) {
            return false;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        serviceName.setText(model.getValueAt(selectedRow, 1).toString());
        serviceType.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        
        String priceS = model.getValueAt(selectedRow, 3).toString();
        priceS = priceS.replace(".", "");
        priceS = priceS.replace(",", ".");
        price.setText(priceS);
        
        unit.setText(model.getValueAt(selectedRow, 4).toString());
        description.setText(model.getValueAt(selectedRow, 5).toString());

        return true;
    }

    public boolean validateData(JTextField serviceName, JTextField servicePrice,
                            JTextField serviceUnit, JComboBox<String> serviceType) {
        if (serviceName.getText().trim().isEmpty() ||
                serviceType.getSelectedItem() == null ||
                servicePrice.getText().trim().isEmpty() ||
                serviceUnit.getText().trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin quan trọng!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if( !ScannerUtil.validateDouble(servicePrice.getText().trim(), "Đơn giá") ) {
            return false;
        }
        
        if( !ScannerUtil.spaceDouble(servicePrice.getText().trim(), 1000, 5000000, "Đơn giá") ) {
            return false;
        }
        
        return true;
    }
    
    public boolean validateSearchInput(JTextField serviceId, JTextField serviceName, JTextField fromServicePrice,
                                        JTextField serviceUnit, JComboBox<String> serviceType, JTextField toServicePrice) {
        
        boolean isIDEmpty = serviceId.getText().trim().isEmpty();
        boolean isFromPriceEmpty = fromServicePrice.getText().trim().isEmpty();
        boolean isToPriceEmpty = toServicePrice.getText().trim().isEmpty();
        
        if( !isIDEmpty && !ScannerUtil.validateInteger(serviceId.getText().trim(), "ID dịch vụ") ||
            !isFromPriceEmpty && !ScannerUtil.validateDouble(fromServicePrice.getText().trim(), "Đơn giá") ||
            !isToPriceEmpty && !ScannerUtil.validateDouble(toServicePrice.getText().trim(), "Đơn giá") ) {
            
            return false;
        }
        
        return ScannerUtil.validateRange(fromServicePrice.getText().trim(), toServicePrice.getText().trim(), "Đơn giá");
        
    }
    
    public boolean getFilterServiceByIcon(Service service, double toPrice, JTable table) {
        List<Service> emps = serviceRepository.getFilteredServiceByIcon(service, toPrice);
        if( emps.isEmpty() ){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            table.setRowSorter(null);
            model.setRowCount(0); 
            
            return false;
        }
        addToTable(emps, table);
        return true;
    }
}

