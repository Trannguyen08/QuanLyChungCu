package controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

public class addButtonHandler {
    
    private JButton addBtn;
    private JTextField fullName, phoneNumber, email, idCard, apartmentID;
    private JComboBox<String> gender ;
    private JDateChooser birthDate;
    private JTable table;
    private JFrame add;

    public addButtonHandler(JButton addBtn, JTextField fullName, JComboBox<String> gender, JDateChooser birthDate, 
                            JTextField phoneNumber, JTextField email, JTextField idCard, JTextField apartmentID, 
                            JTable table, JFrame add) {
        this.addBtn = addBtn;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
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
        Date selectedDate = birthDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";
        model.addRow(new Object[]{
                fullName.getText(),
                gender.getSelectedItem(),
                formattedDate,
                phoneNumber.getText(),
                email.getText(),
                idCard.getText(),
                apartmentID.getText()
        });

        add.setVisible(false);
        JOptionPane.showMessageDialog(null, "Thêm cư dân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}
