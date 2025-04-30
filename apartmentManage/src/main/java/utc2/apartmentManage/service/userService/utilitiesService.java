
package main.java.utc2.apartmentManage.service.userService;

import java.awt.Font;
import javax.swing.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import main.java.utc2.apartmentManage.model.Utilities;
import main.java.utc2.apartmentManage.repository.UserRepository.utilitiesRepository;

public class utilitiesService {
    private final utilitiesRepository utilitiesDAO = new utilitiesRepository();
    // load dữ liệu vào bảng
    public void setupUtilitiestTable(JTable table) {
        List<Utilities> utilitiesList = utilitiesDAO.getAllUtilities();
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        addToTable(utilitiesList, table);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }
    public void addToTable( List<Utilities> utilitiesList, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        table.setRowSorter(null);  
        model.setRowCount(0);
        
        for (Utilities u : utilitiesList) {
            model.addRow(new Object[]{
            u.getServiceName(),
            u.getServiceType(),
            u.getPrice(),
            u.getUnit(),
            u.getDescription()
            });
        }
        
    }
    public boolean filterUtilitiesByKeyword(String name, JTable table) {
        List<Utilities> utilities = utilitiesDAO.getFilteredUtilitiesByName(name);
        addToTable(utilities, table);
        return !utilities.isEmpty(); 
    }
}
