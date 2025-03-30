
package controller.ManagerControl.ResidentHandle;

import com.toedter.calendar.JDateChooser;
import service.managerService.residentService;
import util.ScannerUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nghia
 */
public class searchIconHandler {
    private javax.swing.JTextField residentID, fullName, phoneNumber,email, idCard, apartmentID ;
    private javax.swing.JComboBox<String> gender;
    private JDateChooser birthDate;
    private JDateChooser toBirthDate;
    private javax.swing.JButton searchBtn;
    private JTable table;
    private JFrame frame;
    private final residentService residentService = new residentService();

    public searchIconHandler(JTextField residentID,JTextField apartmentID,  JTextField fullName, JComboBox<String> gender,
                                JDateChooser birthDate, JDateChooser toBirthDate, JTextField phoneNumber,
                                JTextField email, JTextField idCard,JButton searchBtn, JTable table, JFrame frame) {
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
        
        boolean check = residentService.validateSearchInput( residentID, apartmentID, fullName, gender, birthDate, toBirthDate, phoneNumber, email, idCard);
        if( !check ) {
            return;
        }

        // nếu không null thì xét với table

        if (!residentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + residentID.getText().trim(), 0));
        }
        if (apartmentID.getText() != null && !apartmentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentID.getText().trim(), 1));
        }
        if (!fullName.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + fullName.getText().trim(), 2));
        }
        if (phoneNumber.getText() != null && !phoneNumber.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + phoneNumber.getText().trim(), 3));
        }
        if (email.getText() != null && !email.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + email.getText().trim(), 4));
        }
        if (idCard.getText() != null && !idCard.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + idCard.getText().trim(), 5));
        }
        if (gender.getSelectedItem() != null && !gender.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + gender.getSelectedItem().toString().trim(), 7));
        }
        // xét các khoảng số nguyên số thực
        RowFilter<DefaultTableModel, Integer> numberFilter = new RowFilter<>() {
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                try {
                    // Chuyển đổi ngày tuyển dụng từ bảng
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date hiringDateFrom = sdf.parse(entry.getStringValue(6).trim()); 

                    Date birthDateInput = birthDate.getDate();
                    Date toBirthDateInput = toBirthDate.getDate();

                    boolean birthDateMatch = (birthDateInput == null) || hiringDateFrom.compareTo(birthDateInput) >= 0;
                    boolean birthDateMaxMatch = (toBirthDateInput == null) || hiringDateFrom.compareTo(toBirthDateInput) <= 0;

                    return birthDateMatch && birthDateMaxMatch;
                } catch (ParseException e) {
                return false;
                }
            }
        };

        filters.add(numberFilter);
        sorter.setRowFilter(RowFilter.andFilter(filters));
        frame.setVisible(false);
        if (table.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phù hợp!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}