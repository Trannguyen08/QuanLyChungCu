package main.java.com.utc2.apartmentManage.controller.ManagerControl.ContractControl;

import javax.swing.*;
import main.java.com.utc2.apartmentManage.service.managerService.contractService;


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
        String searchText = searchField.getText().trim();
        if( searchText.trim().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập từ khóa tìm kiếm!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        contract_service.updateTableWithContracts(searchText, table);
    }
}
