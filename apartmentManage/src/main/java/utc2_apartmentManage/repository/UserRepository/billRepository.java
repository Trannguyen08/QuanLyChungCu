
package main.java.utc2_apartmentManage.repository.UserRepository;

import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import main.java.utc2_apartmentManage.model.Bill;
import main.java.utc2_apartmentManage.databaseConnect.ConnectDB;


public class billRepository {
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
                    rs.getString("bill_date"), 
                    rs.getString("due_date"), 
                    rs.getString("status") 
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return billList;
    }
    public List<Bill> getFilteredBills(Bill bill, double totalAmoun, double to_totalAmount, 
                                     JDateChooser billDate, JDateChooser dueDate) {
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (billDate.getDate() != null) {
            sql += " AND cb.due_date >= ?";
            parameters.add(dateFormat.format(billDate.getDate()));
        }
        if (dueDate.getDate() != null) {
            sql += " AND b.due_date <= ?";
            parameters.add(dateFormat.format(dueDate.getDate()));
        }
        // Lọc theo khoảng giá trị hợp đồng
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
                    rs.getString("bill_date"),
                    rs.getString("due_date"),
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
                     rs.getString("bill_date"),
                     rs.getString("due_date"),
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
