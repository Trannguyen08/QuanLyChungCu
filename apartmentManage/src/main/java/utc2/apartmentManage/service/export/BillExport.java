package main.java.utc2.apartmentManage.service.export;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.awt.Desktop;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import java.text.NumberFormat;
import java.util.Locale;



import main.java.utc2.apartmentManage.model.BillDetail;
import main.java.utc2.apartmentManage.model.BillInfo;
import main.java.utc2.apartmentManage.repository.UserRepository.billRepository;

public class BillExport {
    private final NumberFormat df = NumberFormat.getInstance(new Locale("vi", "VN"));
    private final billRepository billRepo = new billRepository();

    public void exportBillToPDF(int billId, String filePath) {
        BillInfo billInfo = billRepo.fetchBillInfo(billId);
        if (billInfo == null) {
            System.out.println("Không tìm thấy hóa đơn với ID: " + billId);
            return;
        }

        List<BillDetail> details = billRepo.getAllDetailByBillID(billId);
        generatePdf(filePath, billInfo, details);
    }

    public void generatePdf(String filePath, BillInfo bill, List<BillDetail> details) {
        try {
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Font hỗ trợ tiếng Việt
            String fontPath = "C:/Windows/Fonts/arial.ttf";  // Đường dẫn font trên Windows
            PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);
            document.setFont(font);

            // Tiêu đề
            Paragraph header = new Paragraph("Ban quản lý chung cư NND")
                    .setFontSize(12)
                    .setBold()
                    .setTextAlignment(TextAlignment.LEFT);
            document.add(header);
            
            Paragraph title = new Paragraph("HÓA ĐƠN HÀNG THÁNG")
                    .setFontSize(18)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER);
            document.add(title);
            document.add(new Paragraph("\n"));

            // Thông tin hóa đơn
            document.add(new Paragraph("Căn hộ ID: " + bill.getApartmentId()));
            document.add(new Paragraph("Chủ hộ: " + bill.getFullName()));
            document.add(new Paragraph("Hạn thanh toán: " + bill.getDueDate()));
            document.add(new Paragraph("\n"));

            // Bảng chi tiết
            float[] columnWidths = {200F, 100F, 100F, 100F};
            Table table = new Table(columnWidths);
            table.addCell("Tên dịch vụ");
            table.addCell("Đơn giá");
            table.addCell("Số lượng");
            table.addCell("Thành tiền");

            for (BillDetail d : details) {
                table.addCell(d.getName());
                table.addCell(String.valueOf(df.format(d.getPrice())));
                table.addCell(String.valueOf(d.getNum()));
                table.addCell(String.format(df.format(d.getAmount())));
            }

            document.add(table);

            // Tổng tiền
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Tổng tiền: " + String.format(df.format(bill.getTotalAmount())) + " VND"));

            // Ký tên
            Paragraph sign = new Paragraph("Ký xác nhận của quản lý")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setMarginTop(40);
            document.add(sign);

            document.close();

            System.out.println("Đã tạo file PDF tại: " + filePath);

            // Mở file sau khi tạo (tùy chọn)
            Desktop.getDesktop().open(new File(filePath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
