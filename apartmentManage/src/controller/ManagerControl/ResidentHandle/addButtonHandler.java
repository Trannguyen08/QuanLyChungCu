package controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import model.Resident;
import dao.managerDAO.ResidentDAO;
import util.ScannerUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.managerService.residentService;

import javax.swing.table.DefaultTableModel;

public class addButtonHandler {
    
    private JButton addBtn;
    private JTextField fullName, phoneNumber, email, idCard, apartmentID;
    private JComboBox<String> gender ;
    private JDateChooser birthDate;
    private JTable table;
    private JFrame add;
    private final residentService residentService = new residentService();

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
        String fName = fullName.getText().trim();
        Object rGender = gender.getSelectedItem().toString();
        String rNumber = phoneNumber.getText().trim();
        String rEmail = email.getText().trim();
        Object rIdCard = idCard.getText().toString();
        int rApartmentID = Integer.parseInt(apartmentID.getText().trim());

        // Lấy ngày và định dạng
        Date selectedDate = birthDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";
        
        // Kiểm tra dữ liệu hợp lệ trước khi tiếp tục
        boolean check = residentService.validateData(table, fullName, phoneNumber,  email, idCard,  birthDate,  gender,  apartmentID);
        if (!check) {
            return;
        }
        // Tạo đối tượng Resident
        Resident resident = new Resident(0, fullName.getText().trim(), phoneNumber.getText().trim(),email.getText().trim(), idCard.getText().trim(),
                                (birthDate.getDate() != null) ? new SimpleDateFormat("yyyy-MM-dd").format(birthDate.getDate()) : "N/A",
                                gender.getSelectedItem().toString(), Integer.parseInt(apartmentID.getText().trim()));
      
        // Kiểm tra trùng lặp nhân viên
        if (residentService.isDuplicate(resident, table)) {
            JOptionPane.showMessageDialog(null, "Nhân viên này đã tồn tại!", "Lỗi trùng lặp", JOptionPane.ERROR_MESSAGE);
            return;
        }
       // thêm vào database và table
        boolean isAddedComplete = residentService.addResident(resident);
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(residentService.getLastRow());

        if( isAddedComplete ) {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Thêm dữ liệu không thành công.",
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

 
}
