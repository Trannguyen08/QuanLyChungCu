package Model.ManagerDAO;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;



public class nhapPDF {
    public static void exportContractToPDF(String filePath) {
        try {
            // Khởi tạo PDF
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Load font hỗ trợ tiếng Việt (Arial hoặc Times New Roman)
            String fontPath = "C:\\Windows\\Fonts\\times.ttf"; // Đường dẫn đến font
            PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

            // Quốc hiệu & Tiêu ngữ
            document.add(new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM")
                    .setFont(font).setTextAlignment(TextAlignment.CENTER).setBold());
            document.add(new Paragraph("Độc lập - Tự do - Hạnh phúc")
                    .setFont(font).setTextAlignment(TextAlignment.CENTER).setItalic());
            document.add(new Paragraph("\n"));

            // Tiêu đề hợp đồng
            String contractType = "HỢP ĐỒNG MUA BÁN CĂN HỘ";
            document.add(new Paragraph(contractType)
                    .setFont(font).setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(16));
            document.add(new Paragraph("\n"));

            // Thông tin hợp đồng (Thay thế bằng giá trị cụ thể)
            document.add(new Paragraph("Số hợp đồng: 1001").setFont(font).setBold());
            document.add(new Paragraph("\nBÊN A: Chủ Đầu Tư Căn Hộ").setFont(font).setBold());
            document.add(new Paragraph("Tên tòa nhà: Sunshine Apartment").setFont(font));
            document.add(new Paragraph("Diện tích: 80 m²").setFont(font));
            document.add(new Paragraph("Giá trị hợp đồng: 1.500.000.000 VNĐ").setFont(font));

            // Bên B (Người mua)
            document.add(new Paragraph("\nBÊN B: Khách hàng").setFont(font).setBold());
            document.add(new Paragraph("Họ và tên: Nguyễn Văn A").setFont(font));
            document.add(new Paragraph("Số điện thoại: 0987654321").setFont(font));

            // Điều khoản hợp đồng
            document.add(new Paragraph("\nĐIỀU 1: THỜI HẠN HỢP ĐỒNG").setFont(font).setBold());
            document.add(new Paragraph("Hợp đồng có hiệu lực từ ngày 01/03/2024 đến ngày 01/03/2029.").setFont(font));

            document.add(new Paragraph("\nĐIỀU 2: THANH TOÁN").setFont(font).setBold());
            document.add(new Paragraph("Bên B đồng ý thanh toán số tiền 1.500.000.000 VNĐ theo thỏa thuận giữa hai bên.").setFont(font));

            document.add(new Paragraph("\nĐIỀU 3: ĐIỀU KHOẢN CHUNG").setFont(font).setBold());
            document.add(new Paragraph("Hai bên cam kết thực hiện đúng nội dung hợp đồng, nếu có tranh chấp sẽ giải quyết theo pháp luật Việt Nam.").setFont(font));

            // Ký kết hợp đồng
            document.add(new Paragraph("\nBÊN A").setFont(font).setBold().setTextAlignment(TextAlignment.LEFT));
            document.add(new Paragraph("Ký tên: ________________________\n\n").setFont(font));

            document.add(new Paragraph("BÊN B").setFont(font).setBold().setTextAlignment(TextAlignment.RIGHT));
            document.add(new Paragraph("Ký tên: ________________________\n\n").setFont(font));

            // Lưu file
            document.close();
            System.out.println("Xuất hợp đồng thành công: " + filePath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        exportContractToPDF("C:\\data\\hopdong.pdf");
    }
}
