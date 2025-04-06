
package main.java.utc2_apartmentManage.repository.UserRepository;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import main.java.utc2_apartmentManage.model.Bill;
import main.java.utc2_apartmentManage.databaseConnect.ConnectDB;
import main.java.utc2_apartmentManage.util.ScannerUtil;


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
    
    public boolean updateBill(Bill bill) {
        String sql = "UPDATE bills SET apartment_id = ?, total_amount = ?, bill_date = ?, due_date = ?, status = ? WHERE bill_id = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, bill.getApartmentId());
            pstmt.setDouble(2, bill.getTotalAmount());
            pstmt.setString(3, ScannerUtil.convertDateFormat1(bill.getBillDate()));
            pstmt.setString(4, ScannerUtil.convertDateFormat1(bill.getDueDate()));
            pstmt.setString(5, bill.getStatus());
            pstmt.setInt(6, bill.getBillId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0; 
        } catch (SQLException e) {
            System.err.println("Lỗi cập nhật hóa đơn: " + e.getMessage());
        }
        return false; 
    }
    public List<Bill> getAllBills() {
        String query = "SELECT b.bill_id, " +
                   "a.apartmentIndex, a.floor, a.building, " +
                   "b.bill_date, b.due_date, b.total_amount, b.status " +
                   "FROM bills b " +
                   "JOIN apartments a ON b.apartment_id = a.apartment_id";

    List<Bill> billList = new ArrayList<>();

    try (Connection con = ConnectDB.getConnection();
         PreparedStatement pstm = con.prepareStatement(query);
         ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                billList.add(new Bill(
                    rs.getInt("bill_id"),
                    rs.getInt("apartment_id"),
                    rs.getDouble("total_amount"),
                    ScannerUtil.convertDateFormat2(rs.getString("bill_date")),
                    ScannerUtil.convertDateFormat2(rs.getString("due_date")),
                    rs.getString("status") 
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return billList;
    }
    public List<Bill> getFilteredBills(Bill bill, double totalAmoun, double to_totalAmount, String billDate, String dueDate) {
        List<Bill> bills = new ArrayList<>();
        String sql = ("SELECT b.bill_id, b.apartment_id, b.total_amount," +
                                                " b.bill_date, b.due_date, b.status " +
                                                "FROM bills b " +
                                                "JOIN apartments a ON b.apartment_id = a.apartment_id " +
                                                "WHERE 1=1");

        List<Object> parameters = new ArrayList<>();

        // Thêm điều kiện tìm kiếm từ bill
        if (bill.getBillId() > 0) {
            sql += " AND b.bill_id = ?";
            parameters.add(bill.getBillId());
        }
        if (bill.getApartmentId() > 0) {
            sql += " AND b.apartment_id = ?";
            parameters.add(bill.getApartmentId());
        }
        if (bill.getStatus() != null && !bill.getStatus().trim().isEmpty()) {
            sql += " AND b.status = ?";
            parameters.add(bill.getStatus().trim());
        }
        // Lọc theo khoảng ngày từ JDateChooser
        if (billDate != null) {
            sql += " AND cb.due_date >= ?";
            parameters.add(ScannerUtil.convertDateFormat1(billDate));
        }
        if (dueDate != null) {
            sql += " AND b.due_date <= ?";
            parameters.add(ScannerUtil.convertDateFormat1(dueDate));
        }
        
        // Lọc theo khoảng tổng tiền
        if (totalAmoun >= 0 && to_totalAmount > 0) {  
            sql += " AND b.total_amount BETWEEN ? AND ?";
            parameters.add(totalAmoun);
            parameters.add(to_totalAmount);
        }
        // Kết nối DB và thực thi truy vấn
        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                bills.add(new Bill(
                    rs.getInt("bill_id"),
                    rs.getInt("apartment_id"),
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
     public List<Bill> getFilteredBillsByKeyword(String keyword) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT b.bill_id, b.apartment_id, b.bill_date, b.due_date, " +
                     "b.total_amount, b.status " +
                     "FROM bills b " +
                     "WHERE b.bill_id LIKE ? OR b.apartment_id LIKE ?";

        try (Connection conn = ConnectDB.getConnection();
              PreparedStatement pstmt = conn.prepareStatement(sql)) {

             pstmt.setString(1, "%" + keyword + "%"); 
             pstmt.setString(2, "%" + keyword + "%"); 

             ResultSet rs = pstmt.executeQuery();
             while (rs.next()) {
                 bills.add(new Bill(
                     rs.getInt("bill_id"),
                     rs.getInt("apartment_id"),
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
