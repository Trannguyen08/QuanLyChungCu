package main.java.utc2.apartmentManage.service.export;


import java.awt.Desktop;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import main.java.utc2.apartmentManage.model.Contract;
import main.java.utc2.apartmentManage.repository.ManagerRepository.contractRepository;
import main.java.utc2.apartmentManage.db.ConnectDB;
import main.java.utc2.apartmentManage.model.Bill;
import main.java.utc2.apartmentManage.model.Notification;
import main.java.utc2.apartmentManage.repository.ManagerRepository.notificationRepository;
import main.java.utc2.apartmentManage.repository.UserRepository.billRepository;


public class Excel {

    public static void exportToExcel(String directoryPath, String tableName) {
        // tạo file
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        String filePath = directoryPath + File.separator + tableName + ".xlsx"; // tên file
        String query = "SELECT * FROM " + tableName;

        try (Connection conn = ConnectDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query);
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet(tableName);
            Row headerRow = sheet.createRow(0);
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Ghi tiêu đề cột
            for( int col = 1; col <= columnCount; col++ ) {
                Cell cell = headerRow.createCell(col - 1);
                cell.setCellValue(metaData.getColumnName(col));
            }

            // Ghi dữ liệu
            int rowIndex = 1;
            while( rs.next() ) {
                Row row = sheet.createRow(rowIndex++);
                for( int col = 1; col <= columnCount; col++ ) {
                    row.createCell(col - 1).setCellValue(rs.getString(col));
                }
            }

            // Ghi file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Xuất dữ liệu thành công vào: " + filePath);
            }
            
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            } else {
                System.out.println("Mở file không được hỗ trợ trên hệ thống này.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportApartments(String filePath) {
        exportToExcel(filePath, "apartments");
    }
    
    public static void exportResidents(String directoryPath) {
        String sql = """
            SELECT r.resident_id, r.apartment_id, r.user_id,
                   p.full_name, p.gender, p.dob, p.phoneNum, p.email, p.id_card
            FROM residents r
            JOIN personal_info p ON r.person_id = p.person_id
        """;

        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + File.separator + "resident.xlsx";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery();
             Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Residents");

            // Tạo tiêu đề
            String[] headers = {
                "Mã Cư Dân", "Mã Căn Hộ", "Mã Tài Khoản",
                "Họ Tên", "Giới Tính", "Ngày Sinh",
                "Số Điện Thoại", "Email", "CMND/CCCD"
            };

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Ghi dữ liệu từ ResultSet
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt("resident_id"));
                row.createCell(1).setCellValue(rs.getInt("apartment_id"));
                row.createCell(2).setCellValue(rs.getInt("user_id"));
                row.createCell(3).setCellValue(rs.getString("full_name"));
                row.createCell(4).setCellValue(rs.getString("gender"));
                row.createCell(5).setCellValue(rs.getDate("dob").toString());
                row.createCell(6).setCellValue(rs.getString("phoneNum"));
                row.createCell(7).setCellValue(rs.getString("email"));
                row.createCell(8).setCellValue(rs.getString("id_card"));
            }

            // Tự động điều chỉnh cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi ra file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Xuất dữ liệu thành công vào: " + filePath);
            }

            // Mở file nếu hệ thống hỗ trợ
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            } else {
                System.out.println("Mở file không được hỗ trợ trên hệ thống này.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void exportContracts(String directoryPath) {
        List<Contract> contractList = new contractRepository().getAllContract();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }
        String filePath = directoryPath + File.separator + "contract.xlsx";
        
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Contracts");
            
            // Tạo hàng tiêu đề
            String[] headers = {"ID", "Tên Chủ Sở Hữu", "Căn Hộ", "Loại Hợp Đồng", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Giá Trị Hợp Đồng", "Tình Trạng Hợp Đồng"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            
            // Ghi dữ liệu từ danh sách hợp đồng
            int rowNum = 1;
            for( Contract contract : contractList ) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(contract.getId());
                row.createCell(1).setCellValue(contract.getOwnerName());
                row.createCell(2).setCellValue(contract.getApartmentIndex() );
                row.createCell(3).setCellValue(contract.getContractType());
                row.createCell(4).setCellValue(contract.getStartDate());
                row.createCell(5).setCellValue(contract.getEndDate());
                row.createCell(6).setCellValue(contract.getContractValue());
                row.createCell(7).setCellValue(contract.getContractStatus());
            }
            
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Xuất dữ liệu thành công vào: " + filePath);
            }
            
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            } else {
                System.out.println("Mở file không được hỗ trợ trên hệ thống này.");
            }
            
            System.out.println("Xuất hợp đồng thành công: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public static void exportServices(String filePath) {
        exportToExcel(filePath, "services");
    }
    
    public static void exportBills(String directoryPath, int resID) {
        List<Bill> billList = new billRepository().getAllBills(resID);  
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + File.separator + "resident.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Bills");

            // Tạo hàng tiêu đề
            String[] headers = {"Mã Hóa Đơn", "Ngày Tạo", "Hạn Thanh Toán", "Tổng Tiền", "Trạng Thái"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Ghi dữ liệu hóa đơn
            int rowNum = 1;
            for (Bill bill : billList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(bill.getBillId());
                row.createCell(1).setCellValue(bill.getBillDate());    
                row.createCell(2).setCellValue(bill.getDueDate());
                row.createCell(3).setCellValue(bill.getTotalAmount());
                row.createCell(4).setCellValue(bill.getStatus());
            }

            // Tự động giãn cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi ra file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Xuất hóa đơn thành công vào: " + filePath);
            }

            // Mở file sau khi xuất nếu hệ thống hỗ trợ
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            } else {
                System.out.println("Hệ thống không hỗ trợ mở file tự động.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    public static void exportBillDetails(String filePath) {
        exportToExcel(filePath, "bill_details");
    }
    
    public static void exportEmployees(String filePath) {
        exportToExcel(filePath, "employees");
    }
    
    public static void exportNotification(String directoryPath) {
        List<Notification> list = new notificationRepository().getAllNotifications(); 
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        String filePath = directoryPath + File.separator + "notification.xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Notifications");

            // Tiêu đề cột
            String[] headers = {"ID", "Người Nhận", "Tiêu Đề", "Nội Dung", "Loại", "Ngày Gửi", "Trạng Thái"};
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Ghi dữ liệu từ danh sách notification
            int rowIndex = 1;
            for (Notification noti : list) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(noti.getID());
                row.createCell(1).setCellValue(noti.getRecipant());
                row.createCell(2).setCellValue(noti.getTitle());
                row.createCell(3).setCellValue(noti.getMess());
                row.createCell(4).setCellValue(noti.getType());
                row.createCell(5).setCellValue(noti.getSentDate());
                row.createCell(6).setCellValue(noti.getSeen());
            }

            // Căn chỉnh độ rộng cột
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Xuất dữ liệu thành công vào: " + filePath);
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            } else {
                System.out.println("Mở file không được hỗ trợ trên hệ thống này.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void exportTableToExcelWithDirectory(String directoryPath, JTable table, String fileName) {
        // Kiểm tra hoặc tạo thư mục
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + File.separator + fileName +".xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("New Sheet");
            TableModel model = table.getModel();

            // Tạo hàng tiêu đề
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

            // Ghi dữ liệu từ JTable
            for (int row = 0; row < model.getRowCount(); row++) {
                Row excelRow = sheet.createRow(row + 1);
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Cell cell = excelRow.createCell(col);
                    Object value = model.getValueAt(row, col);
                    if (value instanceof Number) {
                        cell.setCellValue(((Number) value).doubleValue());
                    } else {
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
                }
            }

            // Tự động giãn cột
            for (int i = 0; i < model.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi file
            try (FileOutputStream out = new FileOutputStream(filePath)) {
                workbook.write(out);
                System.out.println("Xuất file thành công: " + filePath);
            }

            // Mở file nếu hỗ trợ
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File(filePath));
            } else {
                JOptionPane.showMessageDialog(null, "Xuất Excel thành công: " + filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất Excel: " + e.getMessage());
        }
    }


}

