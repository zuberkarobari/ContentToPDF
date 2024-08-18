package com.pdf;

import com.itextpdf.text.DocumentException;
import com.pdf.PdfUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
public class MyController {

    @PostMapping("/pdf")
    public ResponseEntity<String> exportPdf(
            @RequestParam String fileName,
            @RequestBody String content) throws IOException, DocumentException {

        // Define the file path where the PDF will be saved
        Path filePath = Paths.get("C:/Users/rafee/OneDrive/Desktop/NOTES/" + fileName + ".pdf");

        // Generate the PDF and save it to the specified location
        try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
            PdfUtils.generatePdfStream(content, outputStream);
        }
        System.out.println("****************************************");
        System.out.println();
        System.out.println(("PDF saved at: " + filePath.toString()));
        System.out.println();
        System.out.println("****************************************");
        System.out.println();

        // Return the file path as the response
        return new ResponseEntity<>("PDF saved at: " + filePath.toString(), HttpStatus.OK);
    }
}
