package main.java.utc2_apartmentManage.controller.UserControl;

import main.java.utc2_apartmentManage.view.UserUI.Pages.Infomation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditButtonListenerInfomation implements ActionListener {
    private Infomation infomationPanel;

    // Constructor nhận tham chiếu đến Infomation panel
    public EditButtonListenerInfomation(Infomation infomationPanel) {
        this.infomationPanel = infomationPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showEditDialog();
    }

    private void showEditDialog() {
        // Tạo JDialog để chỉnh sửa thông tin
        JDialog editDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(infomationPanel), "Chỉnh sửa thông tin", true);
        editDialog.setSize(400, 600);
        editDialog.setLayout(new BorderLayout());

        // Panel chứa các field chỉnh sửa
        JPanel editPanel = new JPanel();
        editPanel.setLayout(new GridLayout(0, 2, 10, 10));
        editPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Các field chỉnh sửa cho Personal Info
        editPanel.add(new JLabel("ID thẻ:"));
        JTextField editIDThe = new JTextField(infomationPanel.getIDThe_label().getText());
        editPanel.add(editIDThe);

        editPanel.add(new JLabel("ID cư dân:"));
        JTextField editIDCuDan = new JTextField(infomationPanel.getIDCuDan_label().getText());
        editPanel.add(editIDCuDan);

        editPanel.add(new JLabel("ID tài khoản:"));
        JTextField editIDTK = new JTextField(infomationPanel.getIDTK_label().getText());
        editPanel.add(editIDTK);

        editPanel.add(new JLabel("Họ và tên:"));
        JTextField editName = new JTextField(infomationPanel.getName_label().getText());
        editPanel.add(editName);

        editPanel.add(new JLabel("Số điện thoại:"));
        JTextField editPhone = new JTextField(infomationPanel.getPhoneNum_label().getText());
        editPanel.add(editPhone);

        editPanel.add(new JLabel("Email:"));
        JTextField editEmail = new JTextField(infomationPanel.getEmail_label().getText());
        editPanel.add(editEmail);

        editPanel.add(new JLabel("Mật khẩu:"));
        JTextField editPassword = new JTextField(infomationPanel.getPassword_label().getText());
        editPanel.add(editPassword);

        editPanel.add(new JLabel("Giới tính:"));
        JTextField editGender = new JTextField(infomationPanel.getGender_label().getText());
        editPanel.add(editGender);

        editPanel.add(new JLabel("Ngày sinh:"));
        JTextField editDateOfBirth = new JTextField(infomationPanel.getDateOfBirth_label().getText());
        editPanel.add(editDateOfBirth);

        // Các field chỉnh sửa cho Apartment Info
        editPanel.add(new JLabel("ID căn hộ:"));
        JTextField editIDApartment = new JTextField(infomationPanel.getIDApartment_label().getText());
        editPanel.add(editIDApartment);

        editPanel.add(new JLabel("Số căn hộ:"));
        JTextField editNumApartment = new JTextField(infomationPanel.getNumApartment_label().getText());
        editPanel.add(editNumApartment);

        editPanel.add(new JLabel("Diện tích:"));
        JTextField editArea = new JTextField(infomationPanel.getAreaApartment_lablel().getText());
        editPanel.add(editArea);

        editPanel.add(new JLabel("Trạng thái:"));
        JTextField editStatus = new JTextField(infomationPanel.getStatusApartment_label().getText());
        editPanel.add(editStatus);

        // Panel chứa các nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Nút Lưu
        JButton saveButton = new JButton("Lưu");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cập nhật dữ liệu từ các field vào label
                infomationPanel.getIDThe_label().setText(editIDThe.getText());
                infomationPanel.getIDCuDan_label().setText(editIDCuDan.getText());
                infomationPanel.getIDTK_label().setText(editIDTK.getText());
                infomationPanel.getName_label().setText(editName.getText());
                infomationPanel.getPhoneNum_label().setText(editPhone.getText());
                infomationPanel.getEmail_label().setText(editEmail.getText());
                infomationPanel.getPassword_label().setText(editPassword.getText());
                infomationPanel.getGender_label().setText(editGender.getText());
                infomationPanel.getDateOfBirth_label().setText(editDateOfBirth.getText());
                infomationPanel.getIDApartment_label().setText(editIDApartment.getText());
                infomationPanel.getNumApartment_label().setText(editNumApartment.getText());
                infomationPanel.getAreaApartment_lablel().setText(editArea.getText());
                infomationPanel.getStatusApartment_label().setText(editStatus.getText());

                JOptionPane.showMessageDialog(editDialog, "Thông tin đã được cập nhật!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                editDialog.dispose(); // Đóng dialog
            }
        });

        // Nút Hủy
        JButton cancelButton = new JButton("Hủy");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editDialog.dispose(); // Đóng dialog mà không lưu
            }
        });

        // Thêm nút vào buttonPanel
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Thêm các thành phần vào dialog
        editDialog.add(new JScrollPane(editPanel), BorderLayout.CENTER); // Dùng JScrollPane để cuộn nếu nhiều field
        editDialog.add(buttonPanel, BorderLayout.SOUTH);
        editDialog.setLocationRelativeTo(infomationPanel);
        editDialog.setVisible(true);
    }
}