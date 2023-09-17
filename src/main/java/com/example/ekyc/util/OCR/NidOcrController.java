package com.example.ekyc.util.OCR;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/nid/ocr")
public class NidOcrController {

    @Value("${tesseract.datapath}")
    private String tesseractPath; // Set the path to the Tess4J tessdata folder in application.properties

    @PostMapping
    public ResponseEntity<NidOcrResponse> performOcr(@RequestParam("image") MultipartFile imageFile) {
        try {
            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath(tesseractPath);

            // Save the MultipartFile to a temporary file
            File tempFile = File.createTempFile("temp_image", null);
            imageFile.transferTo(tempFile);

            // Perform OCR on the temporary file
            String extractedText = tesseract.doOCR(tempFile);

            // Define regular expressions to match Date of Birth and NID/ID number patterns
            Pattern dobPattern = Pattern.compile("Date of Birth: ([0-9]{2} [A-Za-z]{3} [0-9]{4})");
            Pattern nidPattern = Pattern.compile("NID No: ([0-9]+)|ID NO: ([0-9]+)");

            // Match Date of Birth
            Matcher dobMatcher = dobPattern.matcher(extractedText);
            String dob = dobMatcher.find() ? dobMatcher.group(1) : null;

            // Match NID/ID number
            Matcher nidMatcher = nidPattern.matcher(extractedText);
            String nid = nidMatcher.find() ? (nidMatcher.group(1) != null ? nidMatcher.group(1) : nidMatcher.group(2)) : null;

            NidOcrResponse response = new NidOcrResponse(nid, dob);

            // Delete the temporary file
            tempFile.delete();

            return ResponseEntity.ok(response);
        } catch (TesseractException | IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }



    // Helper method to convert MultipartFile to File
    private File convert(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        file.transferTo(convertedFile);
        return convertedFile;
    }
}

