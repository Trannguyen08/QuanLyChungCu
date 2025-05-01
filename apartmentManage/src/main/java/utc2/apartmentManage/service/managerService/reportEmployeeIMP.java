package main.java.utc2.apartmentManage.service.managerService;

import java.awt.Font;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import main.java.utc2.apartmentManage.model.EmployeeReport;
import main.java.utc2.apartmentManage.repository.ManagerRepository.reportRepository;
import main.java.utc2.apartmentManage.service.Interface.ITable;

public class reportEmployeeIMP implements ITable<EmployeeReport> {
    private reportRepository rr = new reportRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    @Override
    public void setUpTable(JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void setUpTable1(JTable table, int month, int year) {
        List<EmployeeReport> list = rr.getAllEmployeeReport(month, year);
        for (EmployeeReport er : list) {
            int actualMinutes = er.getTotalHour();
            int expectedMinutes = er.getWorkDateNum() * 9 * 60;
            int diffMinutes = actualMinutes - expectedMinutes;
            double diffHours = Math.abs(diffMinutes) / 60.0;

            double amount = diffHours * 50000;

            if (diffMinutes > 0) {
                er.setBonus(amount);
            } else if (diffMinutes < 0) {
                er.setFoul(amount);
            }
        }
        
        addData(table, list);
        setFont(table);
        
    }

    @Override
    public void addData(JTable table, List<EmployeeReport> list) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for( EmployeeReport er : list ) {
            model.addRow(new Object[] {
                er.getId(),
                er.getName(),
                er.getJob(),
                er.getShift(),
                er.getWorkDateNum(),
                er.getTotalHour(),
                df.format(er.getSalary()),
                df.format(er.getBonus()),
                df.format(er.getFoul()),
                df.format(er.getSalary() + er.getBonus() - er.getFoul())
            });
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
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE
            );
            return false;
        }
        return true;
    }
    
}
