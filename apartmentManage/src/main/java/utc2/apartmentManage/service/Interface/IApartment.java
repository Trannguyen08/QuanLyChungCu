package main.java.utc2.apartmentManage.service.Interface;

import java.util.List;
import javax.swing.JComboBox;
import javax.swing.*;
import main.java.utc2.apartmentManage.model.Apartment;

public interface IApartment {
    public List<String> getAllImageByID(int id);
    public void deleteImage(int id, String path);
    public boolean confirmDelete(String s);
    public boolean saveApartmentImages(int apartmentID, List<String> imagePaths);
    public Integer getApartmentId(JTable table);
    public Apartment getApartmentByID(int id, JComboBox<String> interior, JComboBox<String> status,
                                            JTextField rentPrice, JTextField buyPrice );
    public boolean loadSelectedRowData(JTable table, JComboBox<String> status, JComboBox<String> interior,
                                       JTextField rentPrice, JTextField buyPrice);
    public boolean priceValidate(JTextField rentPrice, JTextField buyPrice);
    public void loadFilterApartment(Apartment apts,Integer toFloor, Integer toRoom, Double toArea,
                                                Double toRentPrice, Double toBuyPrice, JTable table);
}
