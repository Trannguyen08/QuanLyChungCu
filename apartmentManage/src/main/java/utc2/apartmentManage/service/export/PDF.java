package main.java.utc2.apartmentManage.service.export;

import com.itextpdf.layout.properties.TextAlignment;
import java.sql.*;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.io.font.PdfEncodings;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import main.java.utc2.apartmentManage.util.ConnectDB;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;



public class PDF {
    public static void exportContractToPDF(String fullFilePath, int contractId) {
        String query = """
                            SELECT 
                                c.contract_id, 
                                c.apartment_id, 
                                c.resident_id, 
                                c.contract_type, 
                                c.start_date, 
                                c.end_date, 
                                c.contract_value, 
                                pi.full_name AS buyer_name,
                                pi.phoneNum AS buyer_phone,
                                pi.email AS buyer_email,
                                pi.id_card AS buyer_cccd,          
                                a.building, 
                                a.area, 
                                a.purchase_price, 
                                a.floor, 
                                a.apartmentIndex
                            FROM 
                                contracts c
                            JOIN 
                                residents r ON c.resident_id = r.resident_id
                            JOIN 
                                apartments a ON c.apartment_id = a.apartment_id
                            JOIN 
                                personal_info pi ON r.person_id = pi.person_id  
                            WHERE 
                                c.contract_id = ?;
                       """;

        try (Connection conn = ConnectDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, contractId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                PdfWriter writer = new PdfWriter(fullFilePath);
                PdfDocument pdfDoc = new PdfDocument(writer);


                Document document = new Document(pdfDoc);

                // Nhúng font Unicode vào tài liệu PDF
                String fontPath = "C:/Windows/Fonts/arial.ttf";  // Đường dẫn font trên Windows
                PdfFont font = PdfFontFactory.createFont(fontPath, PdfEncodings.IDENTITY_H);

                // Quốc hiệu & Tiêu ngữ
                document.add(new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM")
                        .setFont(font)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold());
                document.add(new Paragraph("Độc lập - Tự do - Hạnh phúc")
                        .setFont(font)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setItalic());
                document.add(new Paragraph("\n"));

                // Tiêu đề hợp đồng
                String contractType = rs.getString("contract_type").equals("Mua bán") ? "HỢP ĐỒNG MUA BÁN CĂN HỘ" : "HỢP ĐỒNG CHO THUÊ CĂN HỘ";
                document.add(new Paragraph(contractType)
                        .setFont(font)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold()
                        .setFontSize(16));
                document.add(new Paragraph("\n"));

                // Thông tin hợp đồng
                document.add(new Paragraph("Số hợp đồng: " + rs.getInt("contract_id")).setFont(font).setBold());
                document.add(new Paragraph("Ngày ký hợp đồng: " + rs.getDate("start_date")).setFont(font));

                // BÊN BÁN (Chủ đầu tư)
                document.add(new Paragraph("\nBÊN BÁN:  CTTNHH NND").setFont(font).setBold());
                document.add(new Paragraph("Đại diện:  Trần Chí Nguyên").setFont(font));
                document.add(new Paragraph("Số điện thoại:  0829907738").setFont(font));
                document.add(new Paragraph("Email:  trannguyen08@gmail.com").setFont(font));
                document.add(new Paragraph("CCCD:  054205010738").setFont(font));
                document.add(new Paragraph("Địa chỉ:  Phú Hòa, Phú Yên").setFont(font));

                // BÊN MUA (Khách hàng)
                document.add(new Paragraph("\nBÊN MUA:  " + rs.getString("buyer_name")).setFont(font).setBold());
                document.add(new Paragraph("Số điện thoại:  " + rs.getString("buyer_phone")).setFont(font));
                document.add(new Paragraph("Email:  " + rs.getString("buyer_email")).setFont(font));
                document.add(new Paragraph("CCCD:  " + rs.getString("buyer_cccd")).setFont(font));
                document.add(new Paragraph("Địa chỉ:  Phú Hòa, Phú Yên").setFont(font));

                // Thông tin căn hộ
                document.add(new Paragraph("\nTHÔNG TIN CĂN HỘ").setFont(font).setBold());
                document.add(new Paragraph("Tòa nhà: " + rs.getString("building")).setFont(font));
                document.add(new Paragraph("Số căn hộ: " + rs.getString("apartmentIndex") + " - Tầng: " + rs.getInt("floor")).setFont(font));
                document.add(new Paragraph("Diện tích: " + rs.getDouble("area") + " m²").setFont(font));
                document.add(new Paragraph("Giá trị hợp đồng: " + rs.getDouble("contract_value") + " VNĐ").setFont(font));

                // Điều khoản hợp đồng
                document.add(new Paragraph("\nĐIỀU 1: BÀN GIAO CĂN HỘ").setFont(font).setBold());
                document.add(new Paragraph("Bên Bán cam kết bàn giao căn hộ đúng thời gian quy định. "
                                            + "Nếu chậm bàn giao, Bên Bán chịu trách nhiệm bồi thường "
                                            + "theo mức 0.05% giá trị hợp đồng mỗi ngày.").setFont(font));

                document.add(new Paragraph("\nĐIỀU 2: THANH TOÁN").setFont(font).setBold());
                document.add(new Paragraph("Bên Mua có trách nhiệm thanh toán số tiền " + 
                                            rs.getDouble("contract_value") + " VNĐ theo lịch trình sau:").setFont(font));
                document.add(new Paragraph("- 30% khi ký hợp đồng.").setFont(font));
                document.add(new Paragraph("- 70% khi bàn giao căn hộ.").setFont(font));
                
                document.add(new Paragraph("\nĐIỀU 3: BẢO TRÌ, BẢO HÀNH").setFont(font).setBold());
                document.add(new Paragraph("1. Bên Bán có trách nhiệm bảo hành căn hộ trong vòng 24 tháng "
                                            + "kể từ ngày bàn giao.").setFont(font));
                document.add(new Paragraph("2. Bảo trì khu vực chung sẽ do Ban Quản lý "
                        + "                 chung cư thực hiện theo quy định.").setFont(font));

                document.add(new Paragraph("\nĐIỀU 4: VI PHẠM HỢP ĐỒNG").setFont(font).setBold());
                document.add(new Paragraph("1. Nếu Bên Bán không bàn giao đúng hạn, Bên Mua có quyền hủy "
                                            + "hợp đồng và yêu cầu hoàn lại toàn bộ số tiền đã thanh toán.").setFont(font));
                document.add(new Paragraph("2. Nếu Bên Mua chậm thanh toán quá 30 ngày, Bên Bán "
                                            + "có quyền hủy hợp đồng và không hoàn lại số tiền đã đóng.").setFont(font));

                document.add(new Paragraph("\nĐIỀU 5: GIẢI QUYẾT TRANH CHẤP").setFont(font).setBold());
                document.add(new Paragraph("1. Hai bên ưu tiên giải quyết tranh chấp bằng thương lượng.").setFont(font));
                document.add(new Paragraph("2. Nếu không đạt được thỏa thuận, tranh chấp sẽ được "
                                            + "giải quyết tại Tòa án Nhân dân Thành phố nơi có căn hộ.").setFont(font));

                document.add(new Paragraph("\nĐIỀU 6: HIỆU LỰC HỢP ĐỒNG").setFont(font).setBold());
                document.add(new Paragraph("Hợp đồng có hiệu lực từ ngày " + rs.getDate("start_date") +
                                            " đến ngày " + (rs.getDate("end_date") != null ? 
                                             rs.getDate("end_date") : "Không xác định") + ".").setFont(font));

                // Ký kết hợp đồng
                Paragraph sellerPara = new Paragraph("\nBÊN BÁN")
                                                .setFont(font)
                                                .setBold()
                                                .setTextAlignment(TextAlignment.LEFT);
                Paragraph buyerPara = new Paragraph("BÊN MUA")
                                                .setFont(font)
                                                .setBold()
                                                .setTextAlignment(TextAlignment.RIGHT);

                // Thêm vào Document
                document.add(sellerPara);
                document.add(buyerPara);

                // Hàng thứ hai: Ký tên
                Paragraph sellerSignPara = new Paragraph("\n     Ký tên:")
                    .setTextAlignment(TextAlignment.LEFT);
                Paragraph buyerSignPara = new Paragraph("Ký tên   ")
                    .setTextAlignment(TextAlignment.RIGHT);

                // Thêm vào Document
                document.add(sellerSignPara);
                document.add(buyerSignPara);

                // Lưu file PDF
                document.close();
                System.out.println("Xuất hợp đồng thành công: " + fullFilePath);
            } else {
                System.out.println("Không tìm thấy hợp đồng với ID: " + contractId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void openPDF(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Không thể mở file: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "File không tồn tại!");
        }
    }

   
}




