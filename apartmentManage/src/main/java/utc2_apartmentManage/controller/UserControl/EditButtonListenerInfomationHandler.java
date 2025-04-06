package main.java.utc2_apartmentManage.controller.UserControl;

import com.toedter.calendar.JDateChooser;
import main.java.utc2_apartmentManage.view.UserUI.Pages.Infomation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import main.java.utc2_apartmentManage.model.Resident;
import main.java.utc2_apartmentManage.service.managerService.residentService;
import main.java.utc2_apartmentManage.util.ScannerUtil;

public class EditButtonListenerInfomationHandler implements ActionListener {
    private Infomation infomationPanel;
    private final residentService residentService = new residentService();
    
    // Các JLabel từ Infomation
    private JLabel idTheLabel, idCuDanLabel, idTKLabel, nameLabel, phoneNumLabel, emailLabel, 
                   passwordLabel, genderLabel, dateOfBirthLabel, idApartmentLabel, 
                   numApartmentLabel, areaApartmentLabel, statusApartmentLabel;

    public EditButtonListenerInfomationHandler(Infomation infomationPanel, 
                                               JLabel idTheLabel, JLabel idCuDanLabel, JLabel idTKLabel, 
                                               JLabel nameLabel, JLabel phoneNumLabel, JLabel emailLabel, 
                                               JLabel passwordLabel, JLabel genderLabel, JLabel dateOfBirthLabel, 
                                               JLabel idApartmentLabel, JLabel numApartmentLabel, 
                                               JLabel areaApartmentLabel, JLabel statusApartmentLabel) {
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
        this.idApartmentLabel = idApartmentLabel;
        this.numApartmentLabel = numApartmentLabel;
        this.areaApartmentLabel = areaApartmentLabel;
        this.statusApartmentLabel = statusApartmentLabel;
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
        JTextField editIDApartment = new JTextField();
        JTextField editNumApartment = new JTextField();
        JTextField editArea = new JTextField();
        JTextField editStatus = new JTextField();

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
        editPanel.add(new JLabel("ID căn hộ:"));
        editPanel.add(editIDApartment);
        editPanel.add(new JLabel("Số căn hộ:"));
        editPanel.add(editNumApartment);
        editPanel.add(new JLabel("Diện tích:"));
        editPanel.add(editArea);
        editPanel.add(new JLabel("Trạng thái:"));
        editPanel.add(editStatus);

        // Load dữ liệu từ JLabel vào các field
        loadDataToFields(editIDThe, editIDCuDan, editIDTK, editName, editPhone, editEmail, editPassword,
                         editGender, editDateOfBirth, editIDApartment, editNumApartment, editArea, editStatus);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editDateOfBirth.getDate() == null) {
                    JOptionPane.showMessageDialog(editDialog, "Vui lòng chọn ngày sinh.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String date = ScannerUtil.convertJDateChooserToString(editDateOfBirth);

                int apartmentId;
                try {
                    apartmentId = Integer.parseInt(editIDApartment.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(editDialog, "ID căn hộ phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int residentId;
                try {
                    residentId = Integer.parseInt(editIDCuDan.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(editDialog, "ID cư dân phải là số nguyên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Resident resident = new Resident(
                    residentId,
                    editName.getText().trim(),
                    editGender.getSelectedItem().toString(),
                    date,
                    editPhone.getText().trim(),
                    editEmail.getText().trim(),
                    editIDThe.getText().trim(),
                    apartmentId
                );

                if (residentService.updateResident(resident)) {
                    idTheLabel.setText(editIDThe.getText());
                    idCuDanLabel.setText(editIDCuDan.getText());
                    idTKLabel.setText(editIDTK.getText());
                    nameLabel.setText(editName.getText());
                    phoneNumLabel.setText(editPhone.getText());
                    emailLabel.setText(editEmail.getText());
                    passwordLabel.setText(editPassword.getText());
                    genderLabel.setText(editGender.getSelectedItem().toString());
                    dateOfBirthLabel.setText(date);
                    idApartmentLabel.setText(editIDApartment.getText());
                    numApartmentLabel.setText(editNumApartment.getText());
                    areaApartmentLabel.setText(editArea.getText());
                    statusApartmentLabel.setText(editStatus.getText());

                    JOptionPane.showMessageDialog(editDialog, "Cập nhật thông tin thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    editDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(editDialog, "Cập nhật thông tin không thành công.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
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
                                 JTextField editPassword, JComboBox<String> editGender, JDateChooser editDateOfBirth, 
                                 JTextField editIDApartment, JTextField editNumApartment, JTextField editArea, 
                                 JTextField editStatus) {
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

        editIDApartment.setText(idApartmentLabel.getText());
        editNumApartment.setText(numApartmentLabel.getText());
        editArea.setText(areaApartmentLabel.getText());
        editStatus.setText(statusApartmentLabel.getText());
    }
}