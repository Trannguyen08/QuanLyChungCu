package java.utc2.apartmentManage.controller.user.register;

import utc2.apartmentManage.model.Resident;
import utc2.apartmentManage.model.Service;
import utc2.apartmentManage.repository.manager.serviceRepository;
import utc2.apartmentManage.service.implement.user.infoIMP;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class RegisterServiceController {
    private JTable table1, table2;
    private int resID;
    private JTextField searchField;
    private final serviceRepository serviceRepo = new serviceRepository();
    private final infoIMP infoService = new infoIMP();

    public RegisterServiceController(JTable table1, JTable table2, JTextField searchField, int accountID) {
        this.table1 = table1;
        this.table2 = table2;
        this.searchField = searchField;
        Resident res = infoService.getResidentByAccountID(accountID);
        this.resID = res.getResidentID();
        
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                searchTable();
            }
        });

        initTableModel();   // Đảm bảo có model
        placeHolder();
        setupTable1();
        setupClickEvents();
    }

    private void initTableModel() {
        // Table1: Dịch vụ có thể đăng ký
        DefaultTableModel model1 = new DefaultTableModel(
                new Object[]{"Tên DV", "Loại", "Giá", "Đơn vị", "Mô tả", "Thao tác"}, 0
        );
        table1.setModel(model1);

        // Table2: Dịch vụ đã đăng ký
        DefaultTableModel model2 = new DefaultTableModel(
                new Object[]{"Tên DV", "Giá", "Đơn vị", "Thao tác"}, 0
        );
        table2.setModel(model2);
    }

    public void setupTable1() {
        List<Service> services = serviceRepo.getPersonalServices();
        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        model.setRowCount(0);

        for (Service ser : services) {
            model.addRow(new Object[]{
                    ser.getServiceName(),
                    ser.getServiceType(),
                    ser.getPrice(),
                    ser.getUnit(),
                    ser.getDescription(),
                    "Đăng ký"
            });
        }

        setFont(table1);
        setFont(table2);

        // Gán renderer + editor cho cột "Thao tác"
        table1.getColumn("Thao tác").setCellRenderer(new ButtonRenderer());
        table1.getColumn("Thao tác").setCellEditor(new ButtonEditor(new JCheckBox(), rowIndex -> {
            DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
            DefaultTableModel model2 = (DefaultTableModel) table2.getModel();

            String serviceName = (String) model1.getValueAt(rowIndex, 0);
            Object price = model1.getValueAt(rowIndex, 2);
            String unit = (String) model1.getValueAt(rowIndex, 3);

            boolean exists = false;
            for (int i = 0; i < model2.getRowCount(); i++) {
                if (serviceName.equals(model2.getValueAt(i, 0))) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                // ✅ Lấy service_id từ tên
                Integer serviceID = serviceRepo.getServiceIdByName(serviceName);
                if (serviceID != null) {
                    boolean success = serviceRepo.registerService(resID, serviceID);
                    if (success) {
                        model2.addRow(new Object[]{serviceName, price, unit, "Hủy đăng ký"});
                        model1.setValueAt("Đã đăng ký", rowIndex, 5);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi đăng ký dịch vụ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy ID dịch vụ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // ✅ THÊM DÒNG NÀY
                JOptionPane.showMessageDialog(null, "Dịch vụ này đã được đăng ký!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }));
    }

    private void setupClickEvents() {
        // Đặt nút cho table2 nếu bạn muốn xử lý hủy đăng ký sau này
        table2.getColumn("Thao tác").setCellRenderer(new ButtonRenderer());
        table2.getColumn("Thao tác").setCellEditor(new ButtonEditor(new JCheckBox(), rowIndex -> {

            DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
            String serviceName = (String) model2.getValueAt(rowIndex, 0);

            // 🔎 Lấy service_id từ tên
            Integer serviceID = serviceRepo.getServiceIdByName(serviceName);
            if (serviceID != null) {
                boolean success = serviceRepo.unregisterService(resID, serviceID);
                if (success) {
                    model2.removeRow(rowIndex);

                    // cập nhật lại trạng thái ở table1 về "Đăng ký"
                    DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
                    for (int i = 0; i < model1.getRowCount(); i++) {
                        if (model1.getValueAt(i, 0).equals(serviceName)) {
                            model1.setValueAt("Đăng ký", i, 5);
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi khi hủy đăng ký dịch vụ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy ID dịch vụ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }));
    }

    public void setFont(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        ((DefaultTableCellRenderer) header.getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
    }
    
    private void placeHolder() {
        searchField.setForeground(java.awt.Color.GRAY);
        searchField.setText("Nhập tên dịch vụ...");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent evt) {
                if (searchField.getText().equals("Nhập tên dịch vụ...")) {
                    searchField.setText("");
                    searchField.setForeground(java.awt.Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(java.awt.Color.GRAY);
                    searchField.setText("Nhập tên dịch vụ...");
                }
            }
        });
    }
    
    private void searchTable() {
        String text = searchField.getText().trim();

        DefaultTableModel model = (DefaultTableModel) table1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table1.setRowSorter(sorter);

        if (text.isEmpty() || text.equals("Nhập tên dịch vụ...")) {
            sorter.setRowFilter(null); // không lọc gì
        } else {
            RowFilter<DefaultTableModel, Object> idFilter = RowFilter.regexFilter("(?i)" + text, 0); // lọc cột tên

            // Kết hợp 2 bộ lọc bằng OR (chỉ cần khớp 1 trong 2 cột)
            RowFilter<DefaultTableModel, Object> combinedFilter = RowFilter.orFilter(java.util.Arrays.asList(idFilter));
            sorter.setRowFilter(combinedFilter);
        }
    }
}
