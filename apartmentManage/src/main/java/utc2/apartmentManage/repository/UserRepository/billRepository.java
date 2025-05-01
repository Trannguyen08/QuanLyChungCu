
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
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.utc2.apartmentManage.model.PaidHistory;



public class billRepository {
     
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

    public List<Bill> filteredBill(int resID, Integer month, Integer year, String status) {
        List<Bill> list = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM bills WHERE resident_id = ?");
        List<Object> params = new ArrayList<>();
        params.add(resID); // luôn có

        if (month != null) {
            query.append(" AND MONTH(bill_date) = ?");
            params.add(month);
        }

        if (year != null) {
            query.append(" AND YEAR(bill_date) = ?");
            params.add(year);
        }

        if (status != null && !status.isEmpty()) {
            query.append(" AND status = ?");
            params.add(status);
        }

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query.toString())) {

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill(
                        rs.getInt("bill_id"),
                        ScannerUtil.convertDateFormat2(rs.getString("bill_date")),
                        ScannerUtil.convertDateFormat2(rs.getString("due_date")),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                );
                list.add(bill);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public List<PaidHistory> getAllPaidHistory(int resID) {
        String query = """
                            SELECT b.bill_id, b.total_amount, ih.paid_date, ih.note 
                            FROM bills b 
                            JOIN invoice_history ih ON b.bill_id = ih.bill_id
                            WHERE b.resident_id = ? 
                       """;
        List<PaidHistory> list = new ArrayList<>();
        
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {
            
            pstmt.setInt(1, resID);
            ResultSet res = pstmt.executeQuery();
            
            while( res.next() ) {
                list.add( new PaidHistory( 
                        res.getInt("bill_id"),
                        ScannerUtil.convertDateFormat2(res.getString("paid_date")),
                        res.getDouble("total_amount"),
                        res.getString("note")    
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(billRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
