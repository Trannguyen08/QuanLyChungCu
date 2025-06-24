package java.utc2.apartmentManage.service.interfaces;

import javax.swing.*;
import java.util.List;

public interface ITable<T> {
    public void setUpTable(JTable table);
    public void addData(JTable table, List<T> list);
    public void setFont(JTable table);
    public boolean isSelectedRow(JTable table);
}
