
package controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import model.Resident;
import dao.managerDAO.ResidentDAO;
import service.managerService.residentService;
import util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private final residentService residentService = new residentService();
    
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
    
   public void loadSelectedRowData() throws ParseException {
        boolean check = residentService.validateData(table, fullName, phoneNumber,  email, idCard,  birthDate,  gender,  apartmentID);
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
                
        Date selectedDate = birthDate.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = (selectedDate != null) ? sdf.format(selectedDate) : "N/A";
        
        model.setValueAt(fullName.getText(), selectedRow, 1);
        model.setValueAt(phoneNumber.getText(), selectedRow, 2);
        model.setValueAt(email.getText(),selectedRow, 3);
        model.setValueAt(idCard.getText(), selectedRow, 4);
        model.setValueAt(formattedDate, selectedRow, 5);
        model.setValueAt(gender.getSelectedItem(), selectedRow, 6);
        model.setValueAt(apartmentID.getText(), selectedRow, 7);

        edit.setVisible(false);
        JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}

