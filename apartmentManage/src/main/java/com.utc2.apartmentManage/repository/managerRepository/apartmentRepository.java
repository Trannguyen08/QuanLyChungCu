package main.java.com.utc2.apartmentManage.repository.managerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.com.utc2.apartmentManage.databaseConnect.ConnectDB;
import main.java.com.utc2.apartmentManage.model.Apartment;

public class apartmentRepository {

    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.apartment_id) + 1 AS next_id
                       FROM apartments a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM apartments a2 WHERE a2.apartment_id = a1.apartment_id + 1
                       );""";
        int ans = 0;
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if( res.next() ) {
                ans = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
        
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
        String sql = "INSERT INTO apartments (apartment_id, apartmentIndex, floor, building, " +
                "num_rooms, status, area, rent_price, purchase_price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, apartment.getId());
            pstmt.setInt(2, apartment.getIndex());
            pstmt.setInt(3, apartment.getFloor());
            pstmt.setString(4, apartment.getBuilding());
            pstmt.setInt(5, apartment.getNumRooms());
            pstmt.setString(6, apartment.getStatus());
            pstmt.setDouble(7, apartment.getArea());
            pstmt.setDouble(8, apartment.getRentPrice());
            pstmt.setDouble(9, apartment.getPurchasePrice());

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
    
    public boolean isHaveOwner(int id) {
        String sql = "SELECT status FROM apartments WHERE apartment_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id); 
            ResultSet rs = pstmt.executeQuery();
            if( rs.next() ){
                if( rs.getString("status").equals("Đã thuê") || rs.getString("status").equals("Đã bán") ) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Apartment getLastRow() {
        String query = "SELECT * FROM apartments ORDER BY apartment_id DESC LIMIT 1";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            if (res.next()) {
                return new Apartment(
                        res.getInt("apartment_id"),
                        res.getInt("apartmentIndex"),
                        res.getInt("floor"),
                        res.getString("building"),
                        res.getInt("num_rooms"),
                        res.getString("status"),
                        res.getDouble("area"),
                        res.getDouble("rent_price"),
                        res.getLong("purchase_price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Apartment> getAllApartment() {
        String query = "SELECT * FROM apartments";
        List<Apartment> apartmentList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
                apartmentList.add(new Apartment(
                        res.getInt("apartment_id"),
                        res.getInt("apartmentIndex"),
                        res.getInt("floor"),
                        res.getString("building"),
                        res.getInt("num_rooms"),
                        res.getString("status"),
                        res.getDouble("area"),
                        res.getDouble("rent_price"),
                        res.getLong("purchase_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartmentList;
    }

    public List<Apartment> getApartmentsByFilter(int apartmentID, int apartmentIndex, String building, Double fromArea, Double toArea,
                                             Double fromRentPrice, Double toRentPrice, Double fromBuyPrice, Double toBuyPrice,
                                             Integer fromFloor, Integer toFloor, Integer fromRoomNum, Integer toRoomNum, String status) {
        List<Apartment> apartments = new ArrayList<>();
        String query = "SELECT * FROM apartments WHERE 1=1"; 

        List<Object> params = new ArrayList<>();

        if( apartmentID != 0 ) {
            query += " AND apartment_id = ?";
            params.add(apartmentID);
        }
        if( apartmentIndex != 0 ) {
            query += " AND apartmentIndex = ?";
            params.add(apartmentIndex);
        }
        if( building != null && !building.isEmpty() ) {
            query += " AND building LIKE ?";
            params.add("%" + building + "%");
        }
        if( fromArea != null ) {
            query += " AND area >= ?";
            params.add(fromArea);
        }
        if( toArea != null ) {
            query += " AND area <= ?";
            params.add(toArea);
        }
        if( fromRentPrice != null ) {
            query += " AND rent_price >= ?";
            params.add(fromRentPrice);
        }
        if( toRentPrice != null ) {
            query += " AND rent_price <= ?";
            params.add(toRentPrice);
        }
        if( fromBuyPrice != null ) {
            query += " AND purchase_price >= ?";
            params.add(fromBuyPrice);
        }
        if( toBuyPrice != null ) {
            query += " AND purchase_price <= ?";
            params.add(toBuyPrice);
        }
        if( fromFloor != null ) {
            query += " AND floor >= ?";
            params.add(fromFloor);
        }
        if( toFloor != null ) {
            query += " AND floor <= ?";
            params.add(toFloor);
        }
        if( fromRoomNum != null ) {
            query += " AND num_rooms >= ?";
            params.add(fromRoomNum);
        }
        if( toRoomNum != null ) {
            query += " AND num_rooms <= ?";
            params.add(toRoomNum);
        }
        if( status != null && !status.isEmpty() ) {
            query += " AND status = ?";
            params.add(status);
        }

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                apartments.add(new Apartment(
                    rs.getInt("apartment_id"),
                    rs.getInt("apartmentIndex"),
                    rs.getInt("floor"),
                    rs.getString("building"),
                    rs.getInt("num_rooms"),
                    rs.getString("status"),
                    rs.getDouble("area"),
                    rs.getDouble("rent_price"),
                    rs.getLong("purchase_price")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartments;
    }

    public List<String> getImageByID(int id) {
        List<String> imgList = new ArrayList<>();
        String sql = "SELECT image_url FROM apartment_images WHERE apartment_id = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                imgList.add(rs.getString("image_url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return imgList;
    }


}
