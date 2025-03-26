
package Controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nghia
 */
public class editButtonHandler {
    private JButton editBtn;
    private JTextField fullName, phoneNumber, email, idCard, apartmentID;
    private JComboBox<String> gender ;
    private JDateChooser birthDate;
    private JTable table;
    private JFrame edit;
    
    public editButtonHandler(JButton addBtn,  JTextField fullName, JComboBox<String> gender, JDateChooser birthDate, 
                            JTextField phoneNumber, JTextField email, JTextField idCard, JTextField apartmentID, 
                            JTable table, JFrame edit) {
        this.editBtn = addBtn;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
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
        fullName.setText(model.getValueAt(selectedRow, 1).toString());
        gender.setSelectedItem(model.getValueAt(selectedRow, 2).toString());
        String dateString = model.getValueAt(selectedRow, 3).toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            birthDate.setDate(date);
        } catch (ParseException e) {
            birthDate.setDate(null);
        }
        phoneNumber.setText(model.getValueAt(selectedRow, 4).toString());
        email.setText(model.getValueAt(selectedRow, 5).toString());
        idCard.setText(model.getValueAt(selectedRow, 6).toString());
        apartmentID.setText(model.getValueAt(selectedRow, 7).toString());
    }

    public void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if( selectedRow == -1 ) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để cập nhật.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        if( model == null ) {
            JOptionPane.showMessageDialog(null, "Lỗi: Dữ liệu bảng chưa được khởi tạo.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date selectedDate = birthDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";
        model.setValueAt(fullName.getText(), selectedRow, 1);
        model.setValueAt(gender.getSelectedItem(), selectedRow, 2);
        model.setValueAt(formattedDate, selectedRow, 3);
        model.setValueAt(phoneNumber.getText(), selectedRow, 4);
        model.setValueAt(email.getText(), selectedRow, 5);
        model.setValueAt(idCard.getText(), selectedRow, 6);
        model.setValueAt(apartmentID.getText(), selectedRow, 7);

        edit.setVisible(false);
        JOptionPane.showMessageDialog(null, "Cập nhật dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}

