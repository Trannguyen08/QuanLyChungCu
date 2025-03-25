package Controller.ManagerControl.ApartmentHandle;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class editEmployeeHandler {
    private JButton editBtn;
    private JTextField fullName, phoneNumber, email, salary;
    private JComboBox<String> gender, position, status;
    private JDateChooser hiringDate;
    private JTable table;
    private JFrame edit;

    public editEmployeeHandler(JButton addBtn, JTextField fullName, JComboBox<String> gender, JTextField phoneNumber, 
                            JTextField email, JComboBox<String> position, JTextField salary, JDateChooser hiringDate, 
                            JComboBox<String> status, JTable table, JFrame edit) {
        this.editBtn = addBtn;
        this.fullName = fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.hiringDate = hiringDate;
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
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để chỉnh sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if( model == null ) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        for( int col = 0 ; col < model.getColumnCount() ; col++ ) {
            if( model.getValueAt(selectedRow, col ) == null) {
                model.setValueAt("", selectedRow, col);
            }
        }

        fullName.setText(model.getValueAt(selectedRow, 2).toString());
        gender.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
        phoneNumber.setText(model.getValueAt(selectedRow, 4).toString());
        email.setText(model.getValueAt(selectedRow, 5).toString());
        position.setSelectedItem(model.getValueAt(selectedRow, 6).toString());
        salary.setText(model.getValueAt(selectedRow, 7).toString());
        String dateString = model.getValueAt(selectedRow, 8).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            hiringDate.setDate(date);
        } catch (ParseException e) {
            hiringDate.setDate(null);
        }
        status.setSelectedItem(model.getValueAt(selectedRow, 9).toString());
    }

    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhân viên để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if (model == null) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
                
        Date selectedDate = hiringDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";
        
        model.setValueAt(fullName.getText(), selectedRow, 2);
        model.setValueAt(gender.getSelectedItem(), selectedRow, 3);
        model.setValueAt(phoneNumber.getText(), selectedRow, 4);
        model.setValueAt(email.getText(), selectedRow, 5);
        model.setValueAt(position.getSelectedItem(), selectedRow, 6);
        model.setValueAt(salary.getText(), selectedRow, 7);
        model.setValueAt(formattedDate, selectedRow, 8);
        model.setValueAt(status.getSelectedItem(), selectedRow, 9);

        edit.setVisible(false);
        JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}