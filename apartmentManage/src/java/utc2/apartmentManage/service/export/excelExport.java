package java.utc2.apartmentManage.service.export;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class excelExport {
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

