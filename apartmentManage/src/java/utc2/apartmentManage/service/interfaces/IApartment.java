package java.utc2.apartmentManage.service.interfaces;

import utc2.apartmentManage.model.Apartment;

import javax.swing.*;
import java.util.List;

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
    public boolean isStillContract(JTable table);
}
