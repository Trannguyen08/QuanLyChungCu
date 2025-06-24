package java.utc2.apartmentManage.controller.manager.contract;

import utc2.apartmentManage.service.implement.manager.contractIMP;
import utc2.apartmentManage.view.manager.pages.ContractUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class deleteButtonHandle {
    private JTable table;
    private JPanel panel;
    private final contractIMP contract_service = new contractIMP();

    public deleteButtonHandle(JTable table, JPanel panel) {
        this.table = table;
        this.panel = panel;
    }

    public void dltBtnClick() {
        int selectedRow = table.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int id = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
        if( !contract_service.isAcceptedDelete(id) ) {
            JOptionPane.showMessageDialog(null,
                        "Không thể xóa hợp đồng còn hiệu lực.",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return; 
        }
        
        
        if (contract_service.confirmDelete("hợp đồng")) {
            boolean isDeleted = (panel instanceof ContractUI) && contract_service.delete(id);
            if( isDeleted ) {
                ((DefaultTableModel) table.getModel()).removeRow(table.getSelectedRow());
                JOptionPane.showMessageDialog(null,
                        "Xóa dữ liệu thành công.",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(null,
                        "Xóa dữ liệu không thành công.",
                        "Thông báo", JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
}
