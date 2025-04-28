
package main.java.utc2.apartmentManage.repository.UserRepository;

import main.java.utc2.apartmentManage.model.Bill;
import main.java.utc2.apartmentManage.util.ConnectDB;
import main.java.utc2.apartmentManage.util.ScannerUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class billRepository {
    public int getRowCount() {
        String query = "SELECT COUNT(*) FROM bills";
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
    
    
    public List<Bill> getAllBills(int aptId) {
        String query = "SELECT * FROM bills WHERE apartment_id = ? ";
                   
        List<Bill> billList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
            PreparedStatement pstm = con.prepareStatement(query)) {
            
            pstm.setInt(1, aptId);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {

            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return billList;
    }
    public List<Bill> getFilteredBills(Bill bill, Double to_totalAmount) {
        List<Bill> bills = new ArrayList<>();
        String sql = ("SELECT * FROM bills WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

       
        if (bill.getBillId() > 0) {
            sql += " AND bill_id = ?";
            parameters.add(bill.getBillId());
        }
        
        if (bill.getStatus() != null && !bill.getStatus().trim().isEmpty()) {
            sql += " AND status = ?";
            parameters.add(bill.getStatus().trim());
        }
        
        if (bill.getBillDate() != null) {
            sql += " AND due_date >= ?";
            parameters.add(ScannerUtil.convertDateFormat1(bill.getBillDate()));
        }
        
        if (bill.getDueDate() != null) {
            sql += " AND due_date <= ?";
            parameters.add(ScannerUtil.convertDateFormat1(bill.getDueDate()));
        }
        
        if (bill.getTotalAmount() != null ) {  
            sql += " AND total_amount >= ?";
            parameters.add(bill.getTotalAmount());
        }
        
        if (to_totalAmount != null ) {  
            sql += " AND total_amount <= ?";
            parameters.add(to_totalAmount);
        }

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(
                    rs.getInt("bill_id"),
                    rs.getDouble("total_amount"),
                    ScannerUtil.convertDateFormat2(rs.getString("bill_date")),
                    ScannerUtil.convertDateFormat2(rs.getString("due_date")),
                    rs.getString("status")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }
     public List<Bill> getFilteredBillsByID(int id) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT * FROM bills WHERE bill_id = ? ";          

        try (Connection conn = ConnectDB.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); 

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                 bills.add(new Bill(
                     rs.getInt("bill_id"),
                     rs.getDouble("total_amount"),
                    ScannerUtil.convertDateFormat2(rs.getString("bill_date")),
                    ScannerUtil.convertDateFormat2(rs.getString("due_date")),
                     rs.getString("status")
                 ));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }
}
