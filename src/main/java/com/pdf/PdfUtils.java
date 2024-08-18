package com.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfUtils {

    public static FileOutputStream generatePdfStream(String content, FileOutputStream outputStream) throws DocumentException, IOException {
        Document document = new Document();
        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Split the content by lines
        String[] lines = content.split("\n");

        // Write each line to the PDF
        for (String line : lines) {
            // Apply different fonts/styles based on content type
            Font font;
            if (line.startsWith("java") || line.startsWith("import") || line.contains("@")) {
                font = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL); // Java code styling
            } else if (line.startsWith("1.") || line.startsWith("2.") || line.startsWith("Key Points:") || line.startsWith("Example:")) {
                font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD); // Heading styling
            } else {
                font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL); // Normal text styling
            }

            Paragraph paragraph = new Paragraph(line, font);
            document.add(paragraph);
        }

        document.close();
        return outputStream;
    }
}
