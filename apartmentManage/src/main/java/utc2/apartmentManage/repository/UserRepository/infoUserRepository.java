package main.java.utc2.apartmentManage.repository.UserRepository;

import main.java.utc2.apartmentManage.model.Resident;
import main.java.utc2.apartmentManage.db.ConnectDB;
import main.java.utc2.apartmentManage.util.ScannerUtil;
import java.sql.*;
import main.java.utc2.apartmentManage.model.Contract;

public class infoUserRepository {
    public Resident getResidentByAccountID(int accId) {
        String query = """
            SELECT r.resident_id, r.apartment_id, r.user_id, r.person_id,
                   p.full_name, p.gender, p.dob, p.phoneNum, p.email, p.id_card, c.contract_status
            FROM residents r
            JOIN personal_info p ON r.person_id = p.person_id
            JOIN contracts c ON c.resident_id = r.resident_id
            WHERE r.user_id = ?
        """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, accId);
            ResultSet res = pstmt.executeQuery();

            if (res.next()) {
                return new Resident(
                            res.getInt("resident_id"),
                            res.getString("full_name"),
                            res.getString("gender"),
                            ScannerUtil.convertDateFormat2(res.getString("dob")),
                            res.getString("phoneNum"),
                            res.getString("email"),
                            res.getString("id_card"),
                            res.getInt("apartment_id"),
                            res.getInt("user_id"),
                            res.getInt("person_id"),
                            res.getString("contract_status")
                    );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public Contract getContractByResidentID(int resID) {
        String query = """
            SELECT ct.contract_id, p.full_name,
                   CONCAT(ap.apartmentIndex, '-', ap.floor, '-', ap.building) AS indexs,
                   ct.contract_type, ct.start_date, ct.end_date,
                   ct.contract_value, ct.contract_status
            FROM contracts ct
            JOIN apartments ap ON ct.apartment_id = ap.apartment_id
            JOIN residents r ON ct.resident_id = r.resident_id
            JOIN personal_info p ON r.person_id = p.person_id
            WHERE ct.resident_id = ?
        """;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement pstm = con.prepareStatement(query)) {
            
            pstm.setInt(1, resID);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                return new Contract(
                        rs.getInt("contract_id"),
                        rs.getString("full_name"),
                        rs.getString("indexs"),
                        rs.getString("contract_type"),
                        ScannerUtil.convertDateFormat2(rs.getString("start_date")),
                        ScannerUtil.convertDateFormat2(rs.getString("end_date")),
                        rs.getLong("contract_value"),
                        rs.getString("contract_status")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public boolean updateResident(Resident resident) {
        String sql = """
            UPDATE personal_info 
            SET email = ?, phoneNum = ?, id_card = ?
            WHERE person_id = ?
        """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, resident.getEmail());
            stmt.setString(2, resident.getPhoneNumber());
            stmt.setString(3, resident.getIdCard());
            stmt.setInt(4, resident.getInfoID());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

       
    
        
        
     
        
    

}
