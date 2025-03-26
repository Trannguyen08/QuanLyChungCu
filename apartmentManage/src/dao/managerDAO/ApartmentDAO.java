package dao.managerDAO;

import DatabaseConnect.ConnectDB;
import model.Apartment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ApartmentDAO {
    private int getRowCount() {
        String query = "SELECT COUNT(*) FROM apartments";
        int count = 0;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if( res.next() ) {
                count = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    public boolean updateApartment(Apartment apartment) {
        String sql = "UPDATE apartments SET apartmentIndex = ?, floor = ?, building = ?, " +
                     "num_rooms = ?, status = ?, area = ?, rent_price = ?, purchase_price = ? WHERE apartment_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, apartment.getIndex());
            pstmt.setInt(2, apartment.getFloor());
            pstmt.setString(3, apartment.getBuilding());
            pstmt.setInt(4, apartment.getNumRooms());
            pstmt.setString(5, apartment.getStatus());
            pstmt.setDouble(6, apartment.getArea());
            pstmt.setDouble(7, apartment.getRentPrice());
            pstmt.setDouble(8, apartment.getPurchasePrice());
            pstmt.setInt(9, apartment.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật căn hộ: " + e.getMessage());
        }
        return false; 
    }

    public boolean addApartment(Apartment apartment) {
        String sql = "INSERT INTO apartments (apartmentIndex, floor, building, " +
                "num_rooms, status, area, rent_price, purchase_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, apartment.getIndex());
            pstmt.setInt(2, apartment.getFloor());
            pstmt.setString(3, apartment.getBuilding());
            pstmt.setInt(4, apartment.getNumRooms());
            pstmt.setString(5, apartment.getStatus());
            pstmt.setDouble(6, apartment.getArea());
            pstmt.setDouble(7, apartment.getRentPrice());
            pstmt.setDouble(8, apartment.getPurchasePrice());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm căn hộ: " + e.getMessage());
        }
        return false;
    }
    
    public boolean deleteApartment(int id) {
        String sql = "DELETE FROM apartments WHERE apartment_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsDeleted = pstmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa căn hộ: " + e.getMessage());
        }
        return false;
    }

    public Object[] getLastRow() {
        String query = "SELECT * FROM apartments ORDER BY apartment_id DESC LIMIT 1";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if (res.next()) {
                return new Object[]{
                        res.getInt("apartment_id"),
                        res.getInt("apartmentIndex"),
                        res.getInt("floor"),
                        res.getString("building"),
                        res.getInt("num_rooms"),
                        res.getString("status"),
                        res.getDouble("area"),
                        res.getDouble("rent_price"),
                        res.getDouble("purchase_price")
                };
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addDataToTable(JTable table) {
        String query = "SELECT * FROM apartments";
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while( res.next() ) {
                model.addRow( new Object[]{
                        res.getInt("apartment_id"),
                        res.getInt("apartmentIndex"),
                        res.getInt("floor"),
                        res.getString("building"),
                        res.getInt("num_rooms"),
                        res.getString("status"),
                        res.getDouble("area"),
                        res.getDouble("rent_price"),
                        res.getDouble("purchase_price")}
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
