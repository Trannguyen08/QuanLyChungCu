package java.utc2.apartmentManage.controller.manager.apartment;

import utc2.apartmentManage.model.Apartment;
import utc2.apartmentManage.service.implement.manager.apartmentIMP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;


public class editButtonHandle {
    private JButton editBtn;
    private JComboBox<String> interior, status;
    private JTextField buyPrice, rentPrice;
    private JTable table;
    private JFrame edit;
    private final apartmentIMP apartmentservice = new apartmentIMP();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    
    public editButtonHandle(JComboBox<String> interior, JComboBox<String> status, JTextField rentPrice, 
                            JTextField buyPrice, JButton editBtn, JTable table, JFrame edit) {
        this.editBtn = editBtn;
        this.buyPrice = buyPrice;
        this.rentPrice = rentPrice;
        this.interior = interior;
        this.status = status;
        this.table = table;
        this.edit = edit;

        this.editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRow();
            }
        });
        
    }
    
    public void loadSelectedRowData() {
        boolean check = apartmentservice.loadSelectedRowData(table, status, interior, rentPrice, buyPrice);
    }

    public void updateSelectedRow() {  
        int selectedRow = table.getSelectedRow();

        if (!apartmentservice.editValidate(rentPrice, buyPrice)) {
            return;
        }

        int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
        Apartment apartment = apartmentservice.getApartmentByID(id, interior, status, rentPrice, buyPrice);

        if (!apartmentservice.update(apartment)) {
            JOptionPane.showMessageDialog(null,
                    "Cập nhật dữ liệu không thành công.",
                    "Lỗi", JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        updateTableRow(selectedRow, apartment);
        JOptionPane.showMessageDialog(null,
                "Cập nhật dữ liệu thành công.",
                "Thông báo", JOptionPane.INFORMATION_MESSAGE
        );
        edit.setVisible(false);
    }


    private void updateTableRow(int rowIndex, Apartment apartment) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setValueAt(apartment.getInterior(), rowIndex, 4);
        model.setValueAt(apartment.getStatus(), rowIndex, 6);
        model.setValueAt(df.format(apartment.getRentPrice()), rowIndex, 7);
        model.setValueAt(df.format(apartment.getPurchasePrice()), rowIndex, 8);
    }
}

