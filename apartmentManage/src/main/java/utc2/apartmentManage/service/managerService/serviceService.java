package main.java.utc2.apartmentManage.service.managerService;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.NumberFormat;
import main.java.utc2.apartmentManage.repository.ManagerRepository.serviceRepository;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Locale;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import main.java.utc2.apartmentManage.model.Service;
import main.java.utc2.apartmentManage.util.ScannerUtil;

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
    
    
    
    public void addToTable(List<Service> services, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Service service : services) {
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
    
    
    
    public void setupServiceTable(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        List<Service> list = serviceRepository.getAllServices();

        addToTable(list, table);

        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            if (i == 6) {
                table.getColumnModel().getColumn(i).setCellRenderer(new MultiLineCellRenderer());
            } else {
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        
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
                                       JComboBox<String> relevant, JTextField price, JTextField unit, JTextArea description) {
        boolean check = errorNotification(table);
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

    public boolean validateData(JTextField serviceName, JTextField servicePrice, JTextField serviceUnit) {
        if (serviceName.getText().trim().isEmpty() ||
                servicePrice.getText().trim().isEmpty() ||
                serviceUnit.getText().trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if( !ScannerUtil.validateDouble(servicePrice.getText().trim(), "Đơn giá") ) {
            return false;
        }
        
        if(ScannerUtil.spaceDouble(servicePrice.getText().trim(), 1000, 5000000, "Đơn giá")) {
            return false;
        }
        
        return true;
    }
    
    public boolean validateSearchInput(JTextField fromServicePrice, JTextField toServicePrice) {
        
        boolean isFromPriceEmpty = fromServicePrice.getText().trim().isEmpty();
        boolean isToPriceEmpty = toServicePrice.getText().trim().isEmpty();
        
        if( !isFromPriceEmpty && !ScannerUtil.validateDouble(fromServicePrice.getText().trim(), "Đơn giá") ||
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
    
    
    class MultiLineCellRenderer extends JTextArea implements javax.swing.table.TableCellRenderer {
        public MultiLineCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setFont(new Font("Arial", Font.PLAIN, 14)); // Tùy chỉnh font nếu muốn
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            setText(value == null ? "" : value.toString());

            if (isSelected) {
                setBackground(table.getSelectionBackground());
            } else {
                setBackground(table.getBackground());
            }

            setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
            int preferredHeight = getPreferredSize().height;

            // 👉 Reset chiều cao về mặc định trước, rồi set lại nếu cần
            int defaultHeight = table.getRowHeight(); // lấy chiều cao dòng mặc định
            table.setRowHeight(row, defaultHeight); // reset về mặc định

            if (preferredHeight > defaultHeight) {
                table.setRowHeight(row, preferredHeight); // set lại nếu cao hơn
            }
            setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            return this;
        }
    }
}

