package service.export;

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
                       "r.full_name AS buyer_name, r.phone_number AS buyer_phone, r.email AS buyer_email, r.cccd AS buyer_cccd, r.address AS buyer_address, " +
                       "a.building, a.area, a.price, a.floor, a.apartment_number, " +
                       "o.company_name, o.representative, o.phone_number AS seller_phone, o.email AS seller_email, o.cccd AS seller_cccd, o.address AS seller_address " +
                       "FROM contracts c " +
                       "JOIN residents r ON c.resident_id = r.resident_id " +
                       "JOIN apartments a ON c.apartment_id = a.apartment_id " +
                       "JOIN owners o ON a.owner_id = o.owner_id " +
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
                document.add(new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold());
                document.add(new Paragraph("Độc lập - Tự do - Hạnh phúc")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setItalic());
                document.add(new Paragraph("\n"));

                // Tiêu đề hợp đồng
                String contractType = rs.getString("contract_type").equals("Mua bán") ? "HỢP ĐỒNG MUA BÁN CĂN HỘ" : "HỢP ĐỒNG CHO THUÊ CĂN HỘ";
                document.add(new Paragraph(contractType)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold()
                        .setFontSize(16));
                document.add(new Paragraph("\n"));

                // Thông tin hợp đồng
                document.add(new Paragraph("Số hợp đồng: " + rs.getInt("contract_id")).setBold());
                document.add(new Paragraph("Ngày ký hợp đồng: " + rs.getDate("start_date")));

                // BÊN BÁN (Chủ đầu tư)
                document.add(new Paragraph("\nBÊN BÁN: " + rs.getString("company_name"))
                        .setBold());
                document.add(new Paragraph("Đại diện: " + rs.getString("representative")));
                document.add(new Paragraph("Số điện thoại: " + rs.getString("seller_phone")));
                document.add(new Paragraph("Email: " + rs.getString("seller_email")));
                document.add(new Paragraph("CCCD: " + rs.getString("seller_cccd")));
                document.add(new Paragraph("Địa chỉ: " + rs.getString("seller_address")));

                // BÊN MUA (Khách hàng)
                document.add(new Paragraph("\nBÊN MUA: " + rs.getString("buyer_name"))
                        .setBold());
                document.add(new Paragraph("Số điện thoại: " + rs.getString("buyer_phone")));
                document.add(new Paragraph("Email: " + rs.getString("buyer_email")));
                document.add(new Paragraph("CCCD: " + rs.getString("buyer_cccd")));
                document.add(new Paragraph("Địa chỉ: " + rs.getString("buyer_address")));

                // Thông tin căn hộ
                document.add(new Paragraph("\nTHÔNG TIN CĂN HỘ")
                        .setBold());
                document.add(new Paragraph("Tòa nhà: " + rs.getString("building")));
                document.add(new Paragraph("Số căn hộ: " + rs.getString("apartment_number") + " - Tầng: " + rs.getInt("floor")));
                document.add(new Paragraph("Diện tích: " + rs.getDouble("area") + " m²"));
                document.add(new Paragraph("Giá trị hợp đồng: " + rs.getDouble("total_value") + " VNĐ"));

                // Điều khoản hợp đồng
                document.add(new Paragraph("\nĐIỀU 1: BÀN GIAO CĂN HỘ")
                        .setBold());
                document.add(new Paragraph("Bên Bán cam kết bàn giao căn hộ đúng thời gian quy định. Nếu chậm bàn giao, Bên Bán chịu trách nhiệm bồi thường theo mức 0.05% giá trị hợp đồng mỗi ngày."));

                document.add(new Paragraph("\nĐIỀU 2: THANH TOÁN")
                        .setBold());
                document.add(new Paragraph("Bên Mua có trách nhiệm thanh toán số tiền " + rs.getDouble("total_value") + " VNĐ theo lịch trình sau:"));
                document.add(new Paragraph("- 30% khi ký hợp đồng."));
                document.add(new Paragraph("- 50% khi hoàn thành xây dựng."));
                document.add(new Paragraph("- 20% khi bàn giao căn hộ."));

                document.add(new Paragraph("\nĐIỀU 3: BẢO TRÌ, BẢO HÀNH")
                        .setBold());
                document.add(new Paragraph("1. Bên Bán có trách nhiệm bảo hành căn hộ trong vòng 24 tháng kể từ ngày bàn giao."));
                document.add(new Paragraph("2. Bảo trì khu vực chung sẽ do Ban Quản lý chung cư thực hiện theo quy định."));

                document.add(new Paragraph("\nĐIỀU 4: VI PHẠM HỢP ĐỒNG")
                        .setBold());
                document.add(new Paragraph("1. Nếu Bên Bán không bàn giao đúng hạn, Bên Mua có quyền hủy hợp đồng và yêu cầu hoàn lại toàn bộ số tiền đã thanh toán."));
                document.add(new Paragraph("2. Nếu Bên Mua chậm thanh toán quá 30 ngày, Bên Bán có quyền hủy hợp đồng và không hoàn lại số tiền đã đóng."));

                document.add(new Paragraph("\nĐIỀU 5: GIẢI QUYẾT TRANH CHẤP")
                        .setBold());
                document.add(new Paragraph("1. Hai bên ưu tiên giải quyết tranh chấp bằng thương lượng."));
                document.add(new Paragraph("2. Nếu không đạt được thỏa thuận, tranh chấp sẽ được giải quyết tại Tòa án Nhân dân Thành phố nơi có căn hộ."));

                document.add(new Paragraph("\nĐIỀU 6: HIỆU LỰC HỢP ĐỒNG")
                        .setBold());
                document.add(new Paragraph("Hợp đồng có hiệu lực từ ngày " + rs.getDate("start_date") +
                        " đến ngày " + (rs.getDate("end_date") != null ? rs.getDate("end_date") : "Không xác định") + "."));

                // Ký kết hợp đồng
                document.add(new Paragraph("\nBÊN BÁN").setBold().setTextAlignment(TextAlignment.LEFT));
                document.add(new Paragraph("Ký tên: ________________________\n\n"));

                document.add(new Paragraph("BÊN MUA").setBold().setTextAlignment(TextAlignment.RIGHT));
                document.add(new Paragraph("Ký tên: ________________________\n\n"));

                // Lưu file PDF
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
        exportContractToPDF("C:\\data\\hopdong.pdf", 1);
    }
}




