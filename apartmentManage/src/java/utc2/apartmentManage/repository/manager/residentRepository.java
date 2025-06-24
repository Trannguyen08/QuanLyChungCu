package java.utc2.apartmentManage.repository.manager;

import utc2.apartmentManage.db.databaseConnect;
import utc2.apartmentManage.model.Resident;
import utc2.apartmentManage.util.ScannerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class residentRepository {
    
    public int getIDMinNotExist() {
        String query = """
                       SELECT MIN(a1.resident_id) + 1 AS next_id
                       FROM residents a1
                       WHERE NOT EXISTS (
                           SELECT 1 FROM residents a2 WHERE a2.resident_id = a1.resident_id + 1
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
    
    public boolean isStillContract(int id) {
        String query = "SELECT contract_status FROM contracts WHERE resident_id = ?";
        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if(rs.getString("contract_status").equals("Hiệu lực")) {
                    return true;
                }
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
    
    public int isDuplicateResident(Resident resident) {
        String personalInfoSql = """
            SELECT phoneNum, email, id_card
            FROM personal_info
            WHERE person_id = ?
        """;

        String residentSql = """
            SELECT COUNT(*)
            FROM residents
            WHERE apartment_id = ? AND resident_id <> ?
        """;

        try (Connection conn = databaseConnect.getConnection()) {
            // Check personal info trùng
            try (PreparedStatement stmt = conn.prepareStatement(personalInfoSql)) {
                stmt.setInt(1, resident.getInfoID());

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    if (rs.getString("email").equals(resident.getEmail())) return 2;
                    if (rs.getString("phoneNum").equals(resident.getPhoneNumber())) return 3;
                    if (rs.getString("id_card").equals(resident.getIdCard())) return 4;
                }
            }

            // Check resident có trùng căn hộ không
            try (PreparedStatement stmt2 = conn.prepareStatement(residentSql)) {
                stmt2.setInt(1, resident.getApartmentID());
                stmt2.setInt(2, resident.getResidentID());

                ResultSet rs2 = stmt2.executeQuery();
                if (rs2.next() && rs2.getInt(1) > 0) {
                    return 1; 
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; 
    }

    public boolean deleteResident(int id) {
        String selectPersonIDSql = "SELECT person_id FROM residents WHERE resident_id = ?";
        String deleteResidentSql = "DELETE FROM residents WHERE resident_id = ?";
        String deletePersonalInfoSql = "DELETE FROM personal_info WHERE person_id = ?";

        try (Connection con = databaseConnect.getConnection();
             PreparedStatement selectStmt = con.prepareStatement(selectPersonIDSql);
             PreparedStatement deleteResidentStmt = con.prepareStatement(deleteResidentSql);
             PreparedStatement deletePersonalInfoStmt = con.prepareStatement(deletePersonalInfoSql)) {

            selectStmt.setInt(1, id);
            ResultSet rs = selectStmt.executeQuery();
            if (!rs.next()) {
                System.err.println("Không tìm thấy cư dân với ID: " + id);
                return false;
            }
            int personID = rs.getInt("person_id");

            deleteResidentStmt.setInt(1, id);
            int rowsDeletedResident = deleteResidentStmt.executeUpdate();

            deletePersonalInfoStmt.setInt(1, personID);
            int rowsDeletedPersonalInfo = deletePersonalInfoStmt.executeUpdate();

            return rowsDeletedResident > 0 && rowsDeletedPersonalInfo > 0;
        } catch (SQLException e) {
            System.err.println("Lỗi xóa cư dân và thông tin: " + e.getMessage());
        }
        return false;
    }


    
    public boolean addResident(Resident resident) {
        String insertPersonalInfoSQL = "INSERT INTO personal_info (person_id, full_name, gender, dob, phoneNum, email, id_card) " +
                                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertResidentSQL = "INSERT INTO residents (resident_id, apartment_id, user_id, person_id) " +
                                    "VALUES (?, ?, ?, ?)";

        try (Connection con = databaseConnect.getConnection();
             PreparedStatement pstmtPersonal = con.prepareStatement(insertPersonalInfoSQL);
             PreparedStatement pstmtResident = con.prepareStatement(insertResidentSQL)) {

            // Thêm vào personal_info
            pstmtPersonal.setInt(1, resident.getInfoID()); 
            pstmtPersonal.setString(2, resident.getName());
            pstmtPersonal.setString(3, resident.getGender());
            pstmtPersonal.setString(4, ScannerUtil.convertDateFormat1(resident.getBirthDate()));
            pstmtPersonal.setString(5, resident.getPhoneNumber());
            pstmtPersonal.setString(6, resident.getEmail());
            pstmtPersonal.setString(7, resident.getIdCard());

            int personalRows = pstmtPersonal.executeUpdate();

            if (personalRows > 0) {
                // Thêm vào residents
                pstmtResident.setInt(1, resident.getResidentID());
                pstmtResident.setInt(2, resident.getApartmentID());
                pstmtResident.setInt(3, resident.getUserID());
                pstmtResident.setInt(4, resident.getInfoID()); 

                int residentRows = pstmtResident.executeUpdate();
                return residentRows > 0;
            }
        } catch (SQLException e) {
            System.err.println("Lỗi thêm cư dân: " + e.getMessage());
        }
        return false;
    }
    
    public List<Resident> getAllResident() {
        String query = """
                SELECT r.resident_id, pi.full_name, pi.gender, pi.dob, c.contract_status,
                        CONCAT(ap.apartmentIndex, '-', ap.floor, '-', ap.building) AS indexs, 
                       pi.phoneNum, pi.email, pi.id_card, r.apartment_id, r.user_id, r.person_id 
                FROM residents r
                JOIN personal_info pi ON r.person_id = pi.person_id
                JOIN contracts c ON c.resident_id = r.resident_id
                JOIN apartments ap ON r.apartment_id = ap.apartment_id
                """;
        List<Resident> residentList = new ArrayList<>();

        try (Connection con = databaseConnect.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet res = pstmt.executeQuery()) {

            while (res.next()) {
                residentList.add(new Resident(
                        res.getInt("resident_id"),
                        res.getString("full_name"),
                        res.getString("indexs"),
                        ScannerUtil.convertDateFormat2(res.getString("dob")),
                        res.getString("phoneNum"),
                        res.getString("email"),
                        res.getString("id_card"),
                        res.getInt("apartment_id"),
                        res.getInt("user_id"),
                        res.getInt("person_id"),
                        res.getString("contract_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return residentList;
    }
    
    public List<Resident> getAllFilterResident(Resident resident, String toDate) {
        List<Resident> residentList = new ArrayList<>();
        StringBuilder sql = new StringBuilder("""
                SELECT r.resident_id, pi.full_name, pi.gender, pi.dob, pi.phoneNum,
                        CONCAT(ap.apartmentIndex, '-', ap.floor, '-', ap.building) AS indexs, 
                       pi.email, pi.id_card, r.apartment_id, r.user_id, r.person_id , c.contract_status
                FROM residents r
                JOIN personal_info pi ON r.person_id = pi.person_id
                JOIN contracts c ON c.resident_id = r.resident_id
                JOIN apartments ap ON r.apartment_id = ap.apartment_id
                WHERE 1=1
                """);

        List<Object> params = new ArrayList<>();

        if (resident.getName() != null && !resident.getName().trim().isEmpty()) {
            sql.append(" AND pi.full_name LIKE ?");
            params.add("%" + resident.getName().trim() + "%");
        }
        if (resident.getContractStatus() != null && !resident.getContractStatus().trim().isEmpty()) {
            sql.append(" AND c.contract_status = ?");
            params.add(resident.getContractStatus().trim());
        }
        if (resident.getBirthDate() != null) {
            sql.append(" AND pi.dob >= ?");
            params.add(ScannerUtil.convertDateFormat1(resident.getBirthDate()));
        }
        if (toDate != null) {
            sql.append(" AND pi.dob <= ?");
            params.add(ScannerUtil.convertDateFormat1(toDate));
        }

        try (Connection conn = databaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                stmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                residentList.add(new Resident(
                        rs.getInt("resident_id"),
                        rs.getString("full_name"),
                        rs.getString("indexs"),
                        ScannerUtil.convertDateFormat2(rs.getString("dob")),
                        rs.getString("phoneNum"),
                        rs.getString("email"),
                        rs.getString("id_card"),
                        rs.getInt("apartment_id"),
                        rs.getInt("user_id"),
                        rs.getInt("person_id"),
                        rs.getString("contract_status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return residentList;
    }

    public String getApartmentInfo(int aptId) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT CONCAT(ap.apartmentIndex, '-', ap.floor, '-', ap.building) AS indexs "
                + "FROM apartments ap "
                + "WHERE apartment_id = ?";

        try (Connection con = databaseConnect.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, aptId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("indexs");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
