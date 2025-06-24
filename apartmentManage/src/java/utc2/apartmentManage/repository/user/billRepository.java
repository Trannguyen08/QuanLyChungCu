package java.utc2.apartmentManage.repository.user;

import utc2.apartmentManage.db.databaseConnect;
import utc2.apartmentManage.model.*;
import utc2.apartmentManage.util.ScannerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class billRepository {
     
    public List<Bill> getAllBills(int aptId) {
        String query = "SELECT * FROM bills WHERE resident_id = ? ";
                   
        List<Bill> billList = new ArrayList<>();

        try (Connection con = databaseConnect.getConnection();
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
    
    public List<BillManager> getAllBillForManager(int month, int year, String status) {
        StringBuilder query = new StringBuilder("""
            SELECT 
                b.bill_id,
                r.apartment_id,
                pi.full_name,
                b.due_date,
                b.total_amount,
                b.status,
                ih.paid_date
            FROM bills b
            JOIN residents r ON b.resident_id = r.resident_id
            JOIN personal_info pi ON r.person_id = pi.person_id
            LEFT JOIN invoice_history ih ON b.bill_id = ih.bill_id
            WHERE MONTH(b.due_date) = ? AND YEAR(b.due_date) = ?
        """);

        // Nếu status khác "tất cả" thì thêm điều kiện vào query
        if (!"Tất cả".equals(status)) {
            query.append(" AND b.status = ?");
        }

        List<BillManager> billList = new ArrayList<>();

        try (Connection con = databaseConnect.getConnection();
             PreparedStatement pstm = con.prepareStatement(query.toString())) {

            pstm.setInt(1, month);
            pstm.setInt(2, year);

            if (!"Tất cả".equals(status)) {
                pstm.setString(3, status);
            }

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                String paidDate = rs.getString("paid_date");
                billList.add(new BillManager(
                    rs.getInt("bill_id"),
                    rs.getInt("apartment_id"),
                    rs.getString("full_name"),
                    ScannerUtil.convertDateFormat2(rs.getString("due_date")),
                    rs.getDouble("total_amount"),
                    rs.getString("status"),
                    paidDate == null ? "" : ScannerUtil.convertDateFormat2(paidDate)
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

        try (Connection con = databaseConnect.getConnection();
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
            SELECT bdu.bill_id, s.service_name, bdu.quantity, bdu.price, bdu.quantity * bdu.price AS 'amount'
            FROM bill_detail_users bdu
            JOIN services s ON bdu.service_id = s.service_id
            WHERE bdu.bill_id = ?
        """;

        try (Connection conn = databaseConnect.getConnection();
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
        
        try (Connection con = databaseConnect.getConnection();
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

        try (Connection con = databaseConnect.getConnection();
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
    
    public BillInfo fetchBillInfo(int billId) {
        String sql = """
            SELECT b.bill_id, r.apartment_id, pi.full_name, b.due_date, b.total_amount
            FROM bills b
            JOIN residents r ON b.resident_id = r.resident_id
            JOIN personal_info pi ON r.person_id = pi.person_id
            WHERE b.bill_id = ?
        """;

        try (Connection con = databaseConnect.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, billId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new BillInfo(
                    rs.getInt("bill_id"),
                    rs.getInt("apartment_id"),
                    rs.getString("full_name"),
                    rs.getString("due_date"),
                    rs.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.history_id) + 1 AS next_id
                       FROM invoice_history a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM invoice_history a2 WHERE a2.history_id = a1.history_id + 1
                       );""";
        int ans = 0;
        try (Connection con = databaseConnect.getConnection();
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

        try (Connection conn = databaseConnect.getConnection();
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
    

    public LocalDate getDueDate(int billId) {
        String sql = "SELECT due_date FROM bills WHERE bill_id = ?";

        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, billId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                var dueDateSql = rs.getDate("due_date");
                if (dueDateSql != null) {
                    return dueDateSql.toLocalDate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Nếu không tìm thấy hoặc lỗi
    }

    
    public boolean updateBillStatus(int billId) {
        String sql = "UPDATE bills SET status = ? WHERE bill_id = ?";

        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "Đã thanh toán");   
            pstmt.setInt(2, billId);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<BillManagerDetail> getAllBillManagerDetail(int month, int year) {
        String query = "SELECT * FROM bill_detail_managers WHERE MONTH(paidDate) = ? AND YEAR(paidDate) = ?";
        List<BillManagerDetail> list = new ArrayList<>();

        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, month);
            pstmt.setInt(2, year);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new BillManagerDetail(
                        rs.getInt("id"),
                        rs.getString("service_name"),
                        rs.getDouble("price"),
                        ScannerUtil.convertDateFormat2(rs.getString("paidDate"))
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return list;
    }

    public boolean add(BillManagerDetail bm) {
        String query = "INSERT INTO bill_detail_managers(service_name, price, paidDate) VALUES (?, ?, ?)";
        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, bm.getName());
            pstmt.setDouble(2, bm.getPrice());
            pstmt.setString(3, ScannerUtil.convertDateFormat1(bm.getPaidDate()));

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi thêm hóa đơn: " + e.getMessage());
        }

        return false;
    }
 

}
