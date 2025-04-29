
package main.java.utc2.apartmentManage.service.managerService;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import main.java.utc2.apartmentManage.controller.ManagerControl.ReportControl.BarChartPanel;
import main.java.utc2.apartmentManage.controller.ManagerControl.ReportControl.PieChartPanel;
import main.java.utc2.apartmentManage.model.Amount;
import main.java.utc2.apartmentManage.repository.ManagerRepository.reportRepository;


public class reportFinanceIMP  {
    private reportRepository rr = new reportRepository();
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));

    public void setUpTable(JTable table) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public void setUpTable1(JTable table, int month, int year, JPanel chartPanel) {
        //List<Amount> list = rr.calculateTotalRevenueByService(month, year);
        List<Amount> list = new ArrayList<>();
        list.add(new Amount("Dịch vụ A", 1000000));
        list.add(new Amount("Dịch vụ B", 2500000));
        list.add(new Amount("Dịch vụ C", 1500000));
        list.add(new Amount("Dịch vụ D", 2000000));
        //double totalAmount = rr.calculateMonthlyRevenue(year, month);
        double totalAmount = 7000000;
        for (Amount a : list) {
            a.setPercent(a.getTotal() / totalAmount * 100);
        }

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Amount a : list) {
            model.addRow(new Object[] {
                a.getName(),
                df.format(a.getTotal()),
                df.format(a.getPercent())
            });
        }

        setFont(table);

        
        chartPanel.removeAll();
        chartPanel.setLayout(new BorderLayout());
        PieChartPanel pieChart = new PieChartPanel(list);
        pieChart.setPreferredSize(new Dimension(450, 300));
        chartPanel.add(pieChart, BorderLayout.CENTER);
        chartPanel.revalidate();
        chartPanel.repaint();
    }

    public void setUpTable2(JTable table, int month, int year, JPanel chartPanel) {
        //List<Amount> list = rr.calculateTotalRevenueByServiceWithoutJoin(month, year);
        List<Amount> list = new ArrayList<>();
        list.add(new Amount("Dịch vụ A", 570000));
        list.add(new Amount("Dịch vụ B", 850000));
        list.add(new Amount("Dịch vụ C", 1500000));
        list.add(new Amount("Dịch vụ D", 275000));

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        for (Amount a : list) {
            model.addRow(new Object[] {
                    a.getName(),
                    df.format(a.getTotal())
            });
        }

        setFont(table);

        // Vẽ biểu đồ cột
        chartPanel.removeAll();
        chartPanel.setLayout(new BorderLayout());
        BarChartPanel barChart = new BarChartPanel(list);
        chartPanel.add(barChart, BorderLayout.CENTER);
        chartPanel.revalidate();
        chartPanel.repaint();
    }


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
    
}
