package Model.ManagerDAO;

import DatabaseConnect.ConnectDB;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class Excel {

    public static void exportToExcel(String filePath, String tableName) {
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
            for (int col = 1; col <= columnCount; col++) {
                Cell cell = headerRow.createCell(col - 1);
                cell.setCellValue(metaData.getColumnName(col));
            }
            // Ghi dữ liệu
            int rowIndex = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowIndex++);
                for (int col = 1; col <= columnCount; col++) {
                    row.createCell(col - 1).setCellValue(rs.getString(col));
                }
            }
            // Ghi file
            try (FileOutputStream fileOut = new FileOutputStream(new File(filePath))) {
                workbook.write(fileOut);
                System.out.println("Xuất dữ liệu thành công vào: " + filePath);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void exportApartments(String filePath) {
        exportToExcel(filePath, "apartments");
    }
    
    public static void exportResidents(String filePath) {
        exportToExcel(filePath, "residents");
    }
    
    public static void exportContracts(String filePath) {
        exportToExcel(filePath, "contracts");
    }
    
    public static void exportServices(String filePath) {
        exportToExcel(filePath, "services");
    }
    
    public static void exportBills(String filePath) {
        exportToExcel(filePath, "bills");
    }
    
    public static void exportBillDetails(String filePath) {
        exportToExcel(filePath, "bill_details");
    }
    
    public static void exportEmployees(String filePath) {
        exportToExcel(filePath, "employees");
    }
}

