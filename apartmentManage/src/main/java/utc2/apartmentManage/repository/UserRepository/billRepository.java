
package main.java.utc2.apartmentManage.repository.UserRepository;

import main.java.utc2.apartmentManage.model.Bill;
import main.java.utc2.apartmentManage.db.ConnectDB;
import main.java.utc2.apartmentManage.util.ScannerUtil;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.java.utc2.apartmentManage.model.BillDetail;
import main.java.utc2.apartmentManage.model.PaidHistory;



public class billRepository {
     
    public List<Bill> getAllBills(int aptId) {
        String query = "SELECT * FROM bills WHERE resident_id = ? ";
                   
        List<Bill> billList = new ArrayList<>();

        try (Connection con = ConnectDB.getConnection();
            PreparedStatement pstm = con.prepareStatement(query)) {
            
            pstm.setInt(1, aptId);
            ResultSet rs = pstm.executeQuery();
            
            while (rs.next()) {
                billList.add( new Bill(
                        rs.getInt("bill_id"),
                        ScannerUtil.convertDateFormat2(rs.getString("bill_date")),
                        ScannerUtil.convertDateFormat2(rs.getString("due_date")),
                        rs.getDouble("total_amount"),
                        rs.getString("status")                  
                ));
            }
            rs.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return billList;
    }

    public List<Bill> filteredBill(int resID, int month, int year, String status) {
        List<Bill> list = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM bills WHERE resident_id = ?");
        List<Object> params = new ArrayList<>();
        params.add(resID); 

        if (month != 0) {
            query.append(" AND MONTH(due_date) = ?");
            params.add(month);
        }

        if (year != 0) {
            query.append(" AND YEAR(due_date) = ?");
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
    
    public List<BillDetail> getAllDetailByBillID(int bill_id) {
        List<BillDetail> details = new ArrayList<>();
        String query = """
            SELECT bdu.bill_id, s.service_name, bdu.quantity, s.price, bdu.quantity * s.price AS 'amount'
            FROM bill_detail_users bdu
            JOIN services s ON bdu.service_id = s.service_id
            WHERE bdu.bill_id = ?
        """;

        try (Connection conn = ConnectDB.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bill_id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                details.add(new BillDetail(
                        rs.getInt("bill_id"),
                        rs.getString("service_name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getDouble("amount")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return details;
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
    
    public boolean updateBillById(int id, String status) {
        String query = """
            UPDATE bills
            SET status = ?
            WHERE bill_id = ?
        """;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            
            pstmt.setString(1, status);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.history_id) + 1 AS next_id
                       FROM invoice_history a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM invoice_history a2 WHERE a2.history_id = a1.history_id + 1
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
    
    public boolean addInvoiceHistory(int billId, String paidDate, String note) {
        String query = "INSERT INTO invoice_history (history_id, bill_id, paid_date, note) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, getIDMinNotExist());
            pstmt.setInt(2, billId);
            pstmt.setString(3, ScannerUtil.convertDateFormat1(paidDate)); 
            pstmt.setString(4, note);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 

}
