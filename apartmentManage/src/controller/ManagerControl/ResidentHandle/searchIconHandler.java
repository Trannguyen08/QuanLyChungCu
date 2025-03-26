
package controller.ManagerControl.ResidentHandle;

import util.ScannerUtil;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author nghia
 */
public class searchIconHandler {
    private javax.swing.JTextField residentID;
    private javax.swing.JTextField fullName;
    private javax.swing.JComboBox<String> gender;
    private JDateChooser birthDate;
    private JDateChooser toBirthDate;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField email;
    private javax.swing.JTextField idCard;
    private javax.swing.JTextField apartmentID;
    private javax.swing.JButton searchBtn;
    private JTable table;
    private JFrame frame;

    public searchIconHandler(JTextField residentID, JTextField fullName, JComboBox<String> gender,
                                JDateChooser birthDate, JDateChooser toBirthDate, JTextField phoneNumber,
                                JTextField email, JTextField idCard, JTextField apartmentID, 
                                JButton searchBtn, JTable table, JFrame frame) {
        this.residentID = residentID;
        this.fullName = fullName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.toBirthDate = toBirthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.idCard = idCard;
        this.apartmentID = apartmentID;
        this.searchBtn = searchBtn;
        this.table = table;


        this.searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterTableData();
            }
        });
    }

    public void filterTableData() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);

        List<RowFilter<DefaultTableModel, Integer>> filters = new ArrayList<>();

        // validate dữ diệu nhập đúng kiểu
        if ((residentID.getText() != null && !residentID.getText().trim().isEmpty() &&
                !ScannerUtil.validateInteger(residentID.getText().trim(), "ID cư dân")) ||
                (fullName.getText() != null && !fullName.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fullName.getText().trim(), "Họ tên")) ||
                (phoneNumber.getText() != null && !phoneNumber.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(phoneNumber.getText().trim(), "Số điện thoại")) ||
                (email.getText() != null && !email.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(email.getText().trim(), "Email")) ||
                (idCard.getText() != null && !idCard.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(idCard.getText().trim(), "số căn cước công dân")) ||
                (gender.getSelectedItem() == null || gender.getSelectedItem().toString().trim().isEmpty()) ||
                (apartmentID.getText() != null && !apartmentID.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(apartmentID.getText().trim(), "ID căn hộ"))) {
            return;
        }

        if ((!birthDate.getDate().toString().trim().isEmpty() && !toBirthDate.getDate().toString().trim().isEmpty() &&
                !ScannerUtil.validateRange(birthDate.getDate().toString().trim(), toBirthDate.getDate().toString().trim(), "Ngày sinh")) ) {
            return;
        }


        // nếu không null thì xét với table
        if (!residentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + residentID.getText().trim(), 0));
        }
        if (!fullName.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + fullName.getText().trim(), 1));
        }
        if (phoneNumber.getText() != null && !phoneNumber.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + phoneNumber.getText().trim(), 2));
        }
        if (email.getText() != null && !email.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + email.getText().trim(), 3));
        }
        if (idCard.getText() != null && !idCard.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + idCard.getText().trim(), 4));
        }
        if (birthDate.getDate() != null && !birthDate.getDate().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + birthDate.getDate().toString().trim(), 5));
        }
        if (gender.getSelectedItem() != null && !gender.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + gender.getSelectedItem().toString().trim(), 6));
        }
        if (apartmentID.getText() != null && !apartmentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentID.getText().trim(), 7));
        }
        sorter.setRowFilter(RowFilter.andFilter(filters));
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
