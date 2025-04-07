package main.java.utc2_apartmentManage.controller.ManagerControl.ContractControl;

import javax.swing.*;
import main.java.utc2_apartmentManage.service.managerService.contractService;


public class searchButtonHandler {
    private JTextField searchField;
    private JButton searchBtn;
    private JTable table;
    private final contractService contract_service = new contractService();

    public searchButtonHandler(JTextField searchField, JButton searchBtn, JTable table) {
        this.searchField = searchField;
        this.searchBtn = searchBtn;
        this.table = table;
    }

    public void searchBtnClick() {
        String content = searchField.getText().trim();
        if( content.equals("Nhập id hợp đồng, tên chủ sở hữu ...") || searchField.getText() == null ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị cho ô tìm kiếm",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        if( content.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng không nhập chuỗi chỉ chứa khoảng trắng",
                                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        contract_service.updateTableWithContracts(content, table);
    }
}
