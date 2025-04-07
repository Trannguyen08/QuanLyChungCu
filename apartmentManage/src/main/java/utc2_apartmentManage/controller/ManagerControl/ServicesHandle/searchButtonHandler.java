package main.java.utc2_apartmentManage.controller.ManagerControl.ServicesHandle;

import javax.swing.*;
import main.java.utc2_apartmentManage.service.managerService.serviceService;

public class searchButtonHandler {
    private JTable table;
    private JTextField searchField;
    private JButton searchButton;
    private serviceService ss = new serviceService();
    
    public searchButtonHandler(JTextField searchField, JButton searchButton, JTable table) {
        this.searchField = searchField;
        this.searchButton = searchButton;
        this.table = table;
    }
    
    public void filterWithSearchField() {
        String content = searchField.getText().trim();
        if( content.equals("Nhập id, tên dịch vụ...") || searchField.getText() == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị cho ô tìm kiếm",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if( content.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng không nhập chuỗi chỉ chứa khoảng trắng",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean checkRun = ss.loadServicesIntoTable(content, table);
        if( !checkRun ) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả phú hợp",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
