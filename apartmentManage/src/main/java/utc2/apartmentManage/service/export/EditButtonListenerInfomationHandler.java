package main.java.utc2.apartmentManage.service.export;


import com.toedter.calendar.JDateChooser;
import main.java.utc2.apartmentManage.view.UserUI.Pages.Infomation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import main.java.utc2.apartmentManage.service.managerService.residentIMP;
import main.java.utc2.apartmentManage.util.ScannerUtil;

public class EditButtonListenerInfomationHandler implements ActionListener {
    private Infomation infomationPanel;
    private final residentIMP residentService = new residentIMP();
    
    // Các JLabel từ Infomation
    private JLabel idTheLabel, idCuDanLabel, idTKLabel, nameLabel, phoneNumLabel, emailLabel, 
                   passwordLabel, genderLabel, dateOfBirthLabel;

    public EditButtonListenerInfomationHandler(Infomation infomationPanel, 
                                               JLabel idTheLabel, JLabel idCuDanLabel, JLabel idTKLabel, 
                                               JLabel nameLabel, JLabel phoneNumLabel, JLabel emailLabel, 
                                               JLabel passwordLabel, JLabel genderLabel, JLabel dateOfBirthLabel) {
        this.infomationPanel = infomationPanel;
        this.idTheLabel = idTheLabel;
        this.idCuDanLabel = idCuDanLabel;
        this.idTKLabel = idTKLabel;
        this.nameLabel = nameLabel;
        this.phoneNumLabel = phoneNumLabel;
        this.emailLabel = emailLabel;
        this.passwordLabel = passwordLabel;
        this.genderLabel = genderLabel;
        this.dateOfBirthLabel = dateOfBirthLabel;
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showEditDialog();
    }

    private void showEditDialog() {
        JDialog editDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(infomationPanel), "Chỉnh sửa thông tin", true);
        editDialog.setSize(400, 600);
        editDialog.setLayout(new BorderLayout());

        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(0, 2, 10, 10));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Khai báo các thành phần giao diện
        JTextField editIDThe = new JTextField();
        JTextField editIDCuDan = new JTextField();
        JTextField editIDTK = new JTextField();
        JTextField editName = new JTextField();
        JTextField editPhone = new JTextField();
        JTextField editEmail = new JTextField();
        JTextField editPassword = new JTextField();
        JComboBox<String> editGender = new JComboBox<>(new String[]{"Nam", "Nữ", "Khác"});
        JDateChooser editDateOfBirth = new JDateChooser();
        

        // Thêm các field vào panel
        editPanel.add(new JLabel("ID thẻ:"));
        editPanel.add(editIDThe);
        editPanel.add(new JLabel("ID cư dân:"));
        editPanel.add(editIDCuDan);
        editPanel.add(new JLabel("ID tài khoản:"));
        editPanel.add(editIDTK);
        editPanel.add(new JLabel("Họ và tên:"));
        editPanel.add(editName);
        editPanel.add(new JLabel("Số điện thoại:"));
        editPanel.add(editPhone);
        editPanel.add(new JLabel("Email:"));
        editPanel.add(editEmail);
        editPanel.add(new JLabel("Mật khẩu:"));
        editPanel.add(editPassword);
        editPanel.add(new JLabel("Giới tính:"));
        editPanel.add(editGender);
        editPanel.add(new JLabel("Ngày sinh:"));
        editPanel.add(editDateOfBirth);

        // Load dữ liệu từ JLabel vào các field
        loadDataToFields(editIDThe, editIDCuDan, editIDTK, editName, editPhone, editEmail, editPassword,
                         editGender, editDateOfBirth);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(evt -> {
            if (editDateOfBirth.getDate() == null) {
                JOptionPane.showMessageDialog(editDialog, "Vui lòng chọn ngày sinh.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String idTheStr = editIDThe.getText().trim();
            String idCuDanStr = editIDCuDan.getText().trim();
            String idTKStr = editIDTK.getText().trim();
            String name = editName.getText().trim();
            String phone = editPhone.getText().trim();
            String email = editEmail.getText().trim();
            String password = editPassword.getText().trim();
            String gender = editGender.getSelectedItem().toString();
            String date = ScannerUtil.convertJDateChooserToString(editDateOfBirth);

            // Kiểm tra các trường bắt buộc không để trống
            if (idTheStr.isEmpty() || idCuDanStr.isEmpty() || idTKStr.isEmpty() ||
                name.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(editDialog, "Vui lòng điền đầy đủ tất cả thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra ID có phải là số nguyên không
            try {
                Integer.parseInt(idTheStr);
                Integer.parseInt(idCuDanStr);
                Integer.parseInt(idTKStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(editDialog, "ID thẻ, ID cư dân và ID tài khoản phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra email hợp lệ
            if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(editDialog, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra số điện thoại hợp lệ
            if (!phone.matches("^\\d{9,11}$")) {
                JOptionPane.showMessageDialog(editDialog, "Số điện thoại không hợp lệ (phải từ 9-11 chữ số).", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Sau khi hợp lệ, cập nhật lại label hiển thị
            idTheLabel.setText(idTheStr);
            idCuDanLabel.setText(idCuDanStr);
            idTKLabel.setText(idTKStr);
            nameLabel.setText(name);
            phoneNumLabel.setText(phone);
            emailLabel.setText(email);
            passwordLabel.setText(password);
            genderLabel.setText(gender);
            dateOfBirthLabel.setText(date);

            JOptionPane.showMessageDialog(editDialog, "Cập nhật thông tin thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            editDialog.dispose();
        });

        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog.dispose();
            }
        });

        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        editDialog.add(new JScrollPane(editPanel), BorderLayout.CENTER);
        editDialog.add(buttonPanel, BorderLayout.SOUTH);
        editDialog.setLocationRelativeTo(infomationPanel);
        editDialog.setVisible(true);
    }

    private void loadDataToFields(JTextField editIDThe, JTextField editIDCuDan, JTextField editIDTK, 
                                 JTextField editName, JTextField editPhone, JTextField editEmail, 
                                 JTextField editPassword, JComboBox<String> editGender, JDateChooser editDateOfBirth
                                 ) {
        editIDThe.setText(idTheLabel.getText());
        editIDCuDan.setText(idCuDanLabel.getText());
        editIDTK.setText(idTKLabel.getText());
        editName.setText(nameLabel.getText());
        editPhone.setText(phoneNumLabel.getText());
        editEmail.setText(emailLabel.getText());
        editPassword.setText(passwordLabel.getText());
        editGender.setSelectedItem(genderLabel.getText());
        
        // Chuyển đổi chuỗi ngày sinh sang Date để hiển thị trong JDateChooser
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Giả định định dạng ngày
            if (!dateOfBirthLabel.getText().isEmpty()) {
                editDateOfBirth.setDate(sdf.parse(dateOfBirthLabel.getText()));
            }
        } catch (ParseException e) {
            editDateOfBirth.setDate(null); // Nếu lỗi định dạng, để trống
        }

        
    }
}