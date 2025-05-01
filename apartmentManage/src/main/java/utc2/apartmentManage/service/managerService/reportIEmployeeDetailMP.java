
package main.java.utc2.apartmentManage.service.managerService;

import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.*;
import main.java.utc2.apartmentManage.model.WorkDate;
import main.java.utc2.apartmentManage.repository.ManagerRepository.reportRepository;
import main.java.utc2.apartmentManage.service.Interface.*;

public class reportIEmployeeDetailMP implements ITable<WorkDate> {
    private reportRepository rr = new reportRepository();
    
    public void setUpTable2(JTable table, int empId, int month, int year) {
        List<WorkDate> list = rr.getAllDateByIDAndMonth(empId, month, year);
        addData(table, list);
        setFont(table);
    }

    @Override
    public void addData(JTable table, List<WorkDate> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for( WorkDate wd : list ) {
            model.addRow(new Object[] {wd.getDate(), wd.getInTime(), wd.getOutTime()});
        }
    }

    @Override
    public void setFont(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        
        for( int i = 0 ; i < table.getColumnCount() ; i++ ) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        ((DefaultTableCellRenderer) table.getTableHeader()
                                    .getDefaultRenderer()).
                                    setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    @Override
    public boolean isSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null,
                    "Vui lòng chọn một dòng.",
                    "Thông báo", JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    @Override
    public void setUpTable(JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
   
    
}
