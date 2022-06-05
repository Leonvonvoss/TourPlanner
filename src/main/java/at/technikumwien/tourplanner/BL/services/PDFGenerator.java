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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class PDFGenerator {

    private static String DEST = "TourReport.pdf";
    public void generateReport() throws IOException {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdfDoc);

        Paragraph headline = new Paragraph("Report : Tours")
                .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                .setFontSize(14)
                .setBold()
                .setFontColor(ColorConstants.GRAY);

        document.add(headline);
        document.add(new Paragraph("This report doesn't contain any information about tours, because we didn't have time to implement it."));
        document.add(new Paragraph("Leon-Vincent von Voss & Klemens Hamburger"));
        document.close();
    }
}
