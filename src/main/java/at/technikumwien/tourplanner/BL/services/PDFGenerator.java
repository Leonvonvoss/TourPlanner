package at.technikumwien.tourplanner.BL.services;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

import java.io.FileNotFoundException;
import java.io.IOException;

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
        document.add(loremIpsumHeader);
        document.add(new Paragraph("LOREM_IPSUM_TEXT"));
        Paragraph paragraph = new Paragraph();
        Table table = new Table(3).useAllAvailableWidth();
        for(int i = 0; i <= 5; i++) {
            table.addCell("Hallo");
        }
        paragraph.add(table);
        document.add(paragraph);
        document.close();
    }
}
