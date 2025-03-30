package service.managerService;

import dao.managerDAO.ServiceDAO;
import model.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class serviceService {
    private final ServiceDAO serviceDAO = new ServiceDAO();

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

    public boolean addService(Service service) {
        return serviceDAO.addService(service);
    }

    public Object[] getLastRow() {
        List<Service> services = serviceDAO.getAllServices();
        if (services.isEmpty()) return null;
        Service lastService = services.get(services.size() - 1);
        return new Object[]{
                lastService.getServiceId(),
                lastService.getServiceName(),
                lastService.getServiceType(),
                lastService.getPrice(),
                lastService.getUnit(),
                lastService.getDescription()
        };
    }

    public Integer getServiceId(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hàng để thao tác!", "Lỗi", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        return Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
    }

    public boolean deleteService(int id) {
        return serviceDAO.deleteService(id);
    }

    public boolean confirmDelete() {
        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa dịch vụ này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        return confirm == JOptionPane.YES_OPTION;
    }

    public boolean errorNotification(JTable table) {
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean loadSelectedRowData(JTable table, JTextField serviceName, JComboBox<String> serviceType,
                                       JTextField price, JTextField unit, JTextField description) {
        if (!errorNotification(table)) return false;
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        serviceName.setText(model.getValueAt(selectedRow, 1).toString());
        serviceType.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        price.setText(model.getValueAt(selectedRow, 3).toString());
        unit.setText(model.getValueAt(selectedRow, 4).toString());
        description.setText(model.getValueAt(selectedRow, 5).toString());

        return true;
    }

    public boolean validateData(JTextField serviceName, JComboBox<String> serviceType,
                                JTextField price, JTextField unit, JTextField description) {
        if (serviceName.getText().trim().isEmpty() ||
                serviceType.getSelectedItem() == null ||
                price.getText().trim().isEmpty() ||
                unit.getText().trim().isEmpty() ||
                description.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            Double.parseDouble(price.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Giá phải là số hợp lệ!", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public boolean updateService(Service service) {
        return serviceDAO.updateService(service);
    }

    public void loadServicesIntoTable(JTable table) {
        List<Service> services = serviceDAO.getAllServices();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Service service : services) {
            model.addRow(new Object[]{
                    service.getServiceId(),
                    service.getServiceName(),
                    service.getServiceType(),
                    service.getPrice(),
                    service.getUnit(),
                    service.getDescription()
            });
        }
    }
}
