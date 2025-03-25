package Controller.ManagerControl.ApartmentHandle;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class addEmployeeHandler {
    private JButton addBtn;
    private JTextField fullName, phoneNumber, email, salary;
    private JComboBox<String> gender, position,  status;
    private JDateChooser hiringDate;
    private JTable table;
    private JFrame add;

    public addEmployeeHandler(JButton addBtn, JTextField fullName, JComboBox<String> gender, JTextField phoneNumber, 
                            JTextField email, JComboBox<String> position, JTextField salary, JDateChooser hiringDate, 
                            JComboBox<String> status, JTable table, JFrame add) {
        this.fullName = fullName;
        this.addBtn = addBtn;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.status = status;
        this.table = table;
        this.add = add;

        this.addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewRow();
            }
        });
    }

    private void addNewRow() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Date selectedDate = hiringDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";
        model.addRow(new Object[]{
                fullName.getText(),
                gender.getSelectedItem(),
                phoneNumber.getText(),
                email.getText(),
                position.getSelectedItem(),
                salary.getText(),
                formattedDate, 
                status.getSelectedItem()
        });

        add.setVisible(false);
        JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}

