package controller.ManagerControl.ContractControl;

import service.managerService.contractService;
import util.ScannerUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


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
