package main.java.utc2.apartmentManage.service.managerService;

import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.table.*;
import main.java.utc2.apartmentManage.model.Service;
import main.java.utc2.apartmentManage.repository.ManagerRepository.serviceRepository;
import main.java.utc2.apartmentManage.service.Interface.*;
import main.java.utc2.apartmentManage.util.ScannerUtil;


public class serviceIMP implements ISQL<Service>, ITable<Service>, IValidate, IService {
    private final serviceRepository serviceRepository = new serviceRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    @Override
    public boolean add(Service object) {
        return serviceRepository.addService(object);
    }

    @Override
    public boolean update(Service object) {
        return serviceRepository.updateService(object);
    }

    @Override
    public boolean delete(int id) {
        return serviceRepository.deleteService(id);
    }

    @Override
    public int getNewID() {
        return serviceRepository.getIDMinNotExist(); 
    }

    @Override
    public boolean isExist(int id) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean isDuplicate(Service object) {
        return serviceRepository.isDuplicate(object);
    }

    @Override
    public Service getObject(int id) {
        return serviceRepository.getServiceByID(id); 
    }
    
    @Override
    public boolean confirmDelete(String s) {
        return ScannerUtil.comfirmWindow(s);
    }
    

    @Override
    public void setUpTable(JTable table) {
        List<Service> list = serviceRepository.getAllServices();
        addData(table, list);
        setFont(table);
    }

    @Override
    public void addData(JTable table, List<Service> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Service service : list) {
            model.addRow(new Object[]{
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getServiceType(),
                    service.getRelevant(),
                    df.format(service.getPrice()),
                    service.getUnit(),
                    service.getDescription()
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
            if (i == 6) {
                table.getColumnModel().getColumn(i).setCellRenderer(new MultiLineCellRenderer());
            } else {
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        ((DefaultTableCellRenderer) table.getTableHeader()
                                    .getDefaultRenderer()).
                                    setHorizontalAlignment(SwingConstants.CENTER);
    }

    @Override
    public boolean isSelectedRow(JTable table) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public boolean addValidate(Object... obj) {
        JTextField serviceName = (JTextField) obj[0];
        JTextField servicePrice = (JTextField) obj[1];
        JTextField serviceUnit = (JTextField) obj[2];
        
        if (serviceName.getText().trim().isEmpty() ||
                servicePrice.getText().trim().isEmpty() ||
                serviceUnit.getText().trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if( !ScannerUtil.validateDouble(servicePrice.getText().trim(), "Đơn giá") ) {
            return false;
        }
        
        return !ScannerUtil.spaceDouble(servicePrice.getText().trim(), 1000, 5000000, "Đơn giá");
    }

    @Override
    public boolean editValidate(Object... obj) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public boolean searchValidate(Object... obj) {
        JTextField fromServicePrice = (JTextField) obj[0];
        JTextField toServicePrice = (JTextField) obj[1];
        
        boolean isFromPriceEmpty = fromServicePrice.getText().trim().isEmpty();
        boolean isToPriceEmpty = toServicePrice.getText().trim().isEmpty();
        
        if( !isFromPriceEmpty && !ScannerUtil.validateDouble(fromServicePrice.getText().trim(), "Đơn giá") ||
            !isToPriceEmpty && !ScannerUtil.validateDouble(toServicePrice.getText().trim(), "Đơn giá") ) {
            
            return false;
        }
        
        return ScannerUtil.validateRange(fromServicePrice.getText().trim(), toServicePrice.getText().trim(), "Đơn giá"); 
    }
    
    @Override
    public boolean getFilterServiceByIcon(Service service, double toPrice, JTable table) {
        List<Service> emps = serviceRepository.getFilteredServiceByIcon(service, toPrice);
        if( emps.isEmpty() ){
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            table.setRowSorter(null);
            model.setRowCount(0); 
            
            return false;
        }
        addData(table, emps);
        return true;
    }
    
    @Override
    public boolean loadSelectedRowData(JTable table, JTextField serviceName, JComboBox<String> serviceType,
                                       JComboBox<String> relevant, JTextField price, JTextField unit, JTextArea description) {
        boolean check = isSelectedRow(table);
        if( !check ) {
            return false;
        }
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        serviceName.setText(model.getValueAt(selectedRow, 1).toString());
        serviceType.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        relevant.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        String priceS = model.getValueAt(selectedRow, 4).toString();
        priceS = priceS.replace(".", "");
        priceS = priceS.replace(",", ".");
        price.setText(priceS);
        
        unit.setText(model.getValueAt(selectedRow, 5).toString());
        description.setText(model.getValueAt(selectedRow, 6).toString());

        return true;
    }

}
