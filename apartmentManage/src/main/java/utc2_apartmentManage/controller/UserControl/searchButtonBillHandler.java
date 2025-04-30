
package main.java.utc2_apartmentManage.controller.UserControl;

import javax.swing.*;
import main.java.utc2.apartmentManage.service.userService.billService;
import main.java.utc2.apartmentManage.util.ScannerUtil;




public class searchButtonBillHandler {
    private JTextField searchField;
    private JButton searchBtn;
    private JTable table;
    private final billService billService = new billService();

    public searchButtonBillHandler(JTextField searchField, JButton searchBtn, JTable table) {
        this.searchField = searchField;
        this.searchBtn = searchBtn;
        this.table = table;
    }

    public void searchBtnClick() {
        String content = searchField.getText().trim();
        if( content.equals("Nhập id hóa đơn...") || searchField.getText() == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị cho ô tìm kiếm",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if( content.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng không nhập chuỗi chỉ chứa khoảng trắng",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if( !ScannerUtil.validateInteger(content, "ô tìm kiếm") ) {
            return;
        }

        billService.filterBillByKeyword(Integer.parseInt(content), table);
    }
}
