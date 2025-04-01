package main.java.com.utc2.apartmentManage.controller.ManagerControl.ContractControl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import main.java.com.utc2.apartmentManage.service.managerService.contractService;
import main.java.com.utc2.apartmentManage.view.ManagerUI.Pages.ContractUI;

public class deleteButtonHandler {
    private JTable table;
    private JPanel panel;
    private final contractService contract_service = new contractService();

    public deleteButtonHandler(JTable table, JPanel panel) {
        this.table = table;
        this.panel = panel;
    }

    public void dltBtnClick() {
        boolean checkContractStatus = contract_service.checkContractStatus(table);
        if (!checkContractStatus) {
            return;
        }

        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());

        if (contract_service.confirmDelete()) {
            boolean isDeleted = (panel instanceof ContractUI) && contract_service.deleteContract(id);
            if( isDeleted ) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xóa dữ liệu không thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
