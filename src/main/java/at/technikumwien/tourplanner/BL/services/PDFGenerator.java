package at.technikumwien.tourplanner.BL.services;

import at.technikumwien.tourplanner.TourPlannerApplication;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.io.IOException;
import java.net.URL;

public class PDFGenerator {

    private static String DEST = "Tour.pdf";
    public static void main(String[] args) throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdfDoc);
        Paragraph loremIpsumHeader = new Paragraph("Lorem Ipsum header...")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.RED);
        Paragraph paragraph = new Paragraph();
        Table table = new Table(3).useAllAvailableWidth();
        for(int i = 0; i <= 5; i++) {
            table.addCell("Hallo");
        }
        Paragraph imageHeader = new Paragraph("Lorem Ipsum Image ...")
                .setFont(PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN))
                .setFontSize(18)
                .setBold()
                .setFontColor(ColorConstants.GREEN);
        document.add(imageHeader);
        URL resource = TourPlannerApplication.class.getResource("img/menu_close.png");
        ImageData imageData = ImageDataFactory.create(resource);
        document.add(new Image(imageData));
        document.add(loremIpsumHeader);
        document.add(new Paragraph("LOREM_IPSUM_TEXT"));
        paragraph.add(table);
        document.add(paragraph);
        document.close();
    }
}
