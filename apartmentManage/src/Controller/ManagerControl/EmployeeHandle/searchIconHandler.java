
package Controller.ManagerControl.EmployeeHandle;

import Util.ScannerUtil;
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
    private javax.swing.JTextField employeeID;
    private javax.swing.JTextField fullName;
    private javax.swing.JComboBox<String> gender;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField email;
    private javax.swing.JComboBox<String> position;
    private javax.swing.JTextField salary;
    private javax.swing.JTextField toSalary;
    private JDateChooser hiringDate;
    private JDateChooser toHiringDate;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JButton searchBtn;
    private JTable table;
    private JFrame frame;

    public searchIconHandler(JTextField employeeID, JTextField fullName, JComboBox<String> gender,
                                JTextField phoneNumber, JTextField email, JComboBox<String> position,
                                JTextField salary, JDateChooser hiringDate, JComboBox<String> status,
                                JButton searchBtn, JTable table, JFrame frame, JTextField toSalary, 
                                JDateChooser toHiringDate) {
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.gender = gender;
        this.frame = frame;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.position = position;
        this.salary = salary;
        this.hiringDate = hiringDate;
        this.status = status;
        this.searchBtn = searchBtn;
        this.table = table;
        this.toHiringDate = toHiringDate;
        this.toSalary = toSalary;


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
        if ((employeeID.getText() != null && !employeeID.getText().trim().isEmpty() &&
                !ScannerUtil.validateInteger(employeeID.getText().trim(), "ID nhân viên")) ||
                (fullName.getText() != null && !fullName.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(fullName.getText().trim(), "Họ tên")) ||
                (phoneNumber.getText() != null && !phoneNumber.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(phoneNumber.getText().trim(), "Số điện thoại")) ||
                (!gender.getSelectedItem().toString().trim().isEmpty() &&
                        !ScannerUtil.validateRange(gender.getSelectedItem().toString().trim(), "Giới tính")) ||
                (email.getText() != null && !email.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(email.getText().trim(), "Email")) ||
                (salary.getText() != null && !salary.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(salary.getText().trim(), "Mức lương")) ||
                (toSalary.getText() != null && !toSalary.getText().trim().isEmpty() &&
                        !ScannerUtil.validateDouble(toSalary.getText().trim(), "Mức lương"))) {
            return;
        }

        if ((!fromFloor.getSelectedItem().toString().trim().isEmpty() && !toFloor.getSelectedItem().toString().trim().isEmpty() &&
                !ScannerUtil.validateRange(fromFloor.getSelectedItem().toString().trim(), toFloor.getSelectedItem().toString().trim(), "Tầng")) ||
                (!fromRoomNum.getSelectedItem().toString().trim().isEmpty() && !toRoomNum.getSelectedItem().toString().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromRoomNum.getSelectedItem().toString().trim(), toRoomNum.getSelectedItem().toString().trim(), "Số phòng ngủ")) ||
                (fromArea.getText() != null && toArea.getText() != null &&
                        !fromArea.getText().trim().isEmpty() && !toArea.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromArea.getText().trim(), toArea.getText().trim(), "Diện tích")) ||
                (fromRentPrice.getText() != null && toRentPrice1.getText() != null &&
                        !fromRentPrice.getText().trim().isEmpty() && !toRentPrice1.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromRentPrice.getText().trim(), toRentPrice1.getText().trim(), "Giá thuê")) ||
                (fromBuyPrice.getText() != null && toBuyPrice.getText() != null &&
                        !fromBuyPrice.getText().trim().isEmpty() && !toBuyPrice.getText().trim().isEmpty() &&
                        !ScannerUtil.validateRange(fromBuyPrice.getText().trim(), toBuyPrice.getText().trim(), "Giá mua"))) {
            return;
        }


        // nếu không null thì xét với table
        if (!apartmentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentID.getText().trim(), 0));
        }
        if (!residentID.getText().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + residentID.getText().trim(), 1));
        }
        if (apartmentIndex.getSelectedItem() != null && !apartmentIndex.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + apartmentIndex.getSelectedItem().toString().trim(), 2));
        }
        if (building.getSelectedItem() != null && !building.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + building.getSelectedItem().toString().trim(), 4));
        }
        if (status.getSelectedItem() != null && !status.getSelectedItem().toString().trim().isEmpty()) {
            filters.add(RowFilter.regexFilter("(?i)" + status.getSelectedItem().toString().trim(), 6));
        }

        // xét các khoảng số nguyên số thực
        RowFilter<DefaultTableModel, Integer> numberFilter = new RowFilter<>() {
            public boolean include(RowFilter.Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                try {
                    int floorVal = Integer.parseInt(entry.getStringValue(3).trim());
                    int roomNumVal = Integer.parseInt(entry.getStringValue(5).trim());
                    double areaVal = Double.parseDouble(entry.getStringValue(7).trim());
                    double rentPrice = Double.parseDouble(entry.getStringValue(8).trim());
                    double buyPrice = Double.parseDouble(entry.getStringValue(9).trim());

                    String fromFloorStr = fromFloor.getSelectedItem().toString().trim();
                    String toFloorStr = toFloor.getSelectedItem().toString().trim();
                    String fromRoomStr = fromRoomNum.getSelectedItem().toString().trim();
                    String toRoomStr = toRoomNum.getSelectedItem().toString().trim();
                    String fromAreaStr = fromArea.getText().trim();
                    String toAreaStr = toArea.getText().trim();
                    String fromRentStr = fromRentPrice.getText().trim();
                    String toRentStr = toRentPrice1.getText().trim();
                    String fromBuyStr = fromBuyPrice.getText().trim();
                    String toBuyStr = toBuyPrice.getText().trim();

                    boolean floorMatch = fromFloorStr.isEmpty() || floorVal >= Integer.parseInt(fromFloorStr);
                    boolean floorMaxMatch = toFloorStr.isEmpty() || floorVal <= Integer.parseInt(toFloorStr);

                    boolean roomMatch = fromRoomStr.isEmpty() || roomNumVal >= Integer.parseInt(fromRoomStr);
                    boolean roomMaxMatch = toRoomStr.isEmpty() || roomNumVal <= Integer.parseInt(toRoomStr);

                    boolean areaMatch = fromAreaStr.isEmpty() || areaVal >= Double.parseDouble(fromAreaStr);
                    boolean areaMaxMatch = toAreaStr.isEmpty() || areaVal <= Double.parseDouble(toAreaStr);

                    boolean rentMatch = fromRentStr.isEmpty() || rentPrice >= Double.parseDouble(fromRentStr);
                    boolean rentMaxMatch = toRentStr.isEmpty() || rentPrice <= Double.parseDouble(toRentStr);

                    boolean buyMatch = fromBuyStr.isEmpty() || buyPrice >= Double.parseDouble(fromBuyStr);
                    boolean buyMaxMatch = toBuyStr.isEmpty() || buyPrice <= Double.parseDouble(toBuyStr);

                    return floorMatch && floorMaxMatch && roomMatch && roomMaxMatch &&
                            areaMatch && areaMaxMatch && rentMatch && rentMaxMatch &&
                            buyMatch && buyMaxMatch;
                } catch (NumberFormatException e) {
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
