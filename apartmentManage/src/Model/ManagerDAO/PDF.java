package Model.ManagerDAO;

import com.itextpdf.kernel.pdf.PdfDocument;
import DatabaseConnect.ConnectDB;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.sql.*;



public class PDF {
    public static void exportContractToPDF(String filePath, int contractId) {
        String query = "SELECT c.contract_id, c.apartment_id, c.resident_id, c.contract_type, c.start_date, c.end_date, c.total_value, " +
                       "r.full_name, r.phone_number " +
                       "FROM contracts c " +
                       "JOIN residents r ON c.resident_id = r.resident_id " +
                       "WHERE c.contract_id = ?";

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setInt(1, contractId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                PdfWriter writer = new PdfWriter(filePath);
                PdfDocument pdfDoc = new PdfDocument(writer);
                Document document = new Document(pdfDoc);

                // Quốc hiệu & Tiêu ngữ
                Paragraph title = new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold();
                Paragraph subtitle = new Paragraph("Độc lập - Tự do - Hạnh phúc")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setItalic();
                document.add(title);
                document.add(subtitle);
                document.add(new Paragraph("\n"));

                // Tiêu đề hợp đồng
                String contractType = rs.getString("contract_type").equals("Mua bán") ? "HỢP ĐỒNG MUA BÁN CĂN HỘ" : "HỢP ĐỒNG CHO THUÊ CĂN HỘ";
                Paragraph contractTitle = new Paragraph(contractType)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold()
                        .setFontSize(16);
                document.add(contractTitle);
                document.add(new Paragraph("\n"));

                // Thông tin hợp đồng
                document.add(new Paragraph("Số hợp đồng: " + rs.getInt("contract_id"))
                        .setBold());

                // Bên A (Người bán / cho thuê)
                document.add(new Paragraph("\nBÊN A: Chủ Đầu Tư Căn Hộ")
                        .setBold());
                document.add(new Paragraph("Tên tòa nhà: " + rs.getString("building")));
                document.add(new Paragraph("Diện tích: " + rs.getDouble("area") + " m²"));
                document.add(new Paragraph("Giá trị hợp đồng: " + rs.getDouble("total_value") + " VNĐ"));

                // Bên B (Người mua / thuê)
                document.add(new Paragraph("\nBÊN B: Khách hàng")
                        .setBold());
                document.add(new Paragraph("Họ và tên: " + rs.getString("full_name")));
                document.add(new Paragraph("Số điện thoại: " + rs.getString("phone_number")));

                // Điều khoản hợp đồng
                document.add(new Paragraph("\nĐIỀU 1: THỜI HẠN HỢP ĐỒNG")
                        .setBold());
                document.add(new Paragraph("Hợp đồng có hiệu lực từ ngày " + rs.getDate("start_date") +
                        " đến ngày " + (rs.getDate("end_date") != null ? rs.getDate("end_date") : "Không xác định")));

                document.add(new Paragraph("\nĐIỀU 2: THANH TOÁN")
                        .setBold());
                document.add(new Paragraph("Bên B đồng ý thanh toán số tiền " + rs.getDouble("total_value") + " VNĐ theo thỏa thuận giữa hai bên."));

                document.add(new Paragraph("\nĐIỀU 3: ĐIỀU KHOẢN CHUNG")
                        .setBold());
                document.add(new Paragraph("Hai bên cam kết thực hiện đúng nội dung hợp đồng, nếu có tranh chấp sẽ giải quyết theo pháp luật Việt Nam."));

                document.add(new Paragraph("\nBÊN A").setBold().setTextAlignment(TextAlignment.LEFT));
                document.add(new Paragraph("Ký tên: ________________________\n\n"));

                document.add(new Paragraph("BÊN B").setBold().setTextAlignment(TextAlignment.RIGHT));
                document.add(new Paragraph("Ký tên: ________________________\n\n"));

                // Lưu file
                document.close();
                System.out.println("Xuất hợp đồng thành công: " + filePath);
            } else {
                System.out.println("Không tìm thấy hợp đồng với ID: " + contractId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        exportContractToPDF("C:\\data\\hopdong.pdf", 0);
    }
}


