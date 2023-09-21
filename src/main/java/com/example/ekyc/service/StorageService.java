package com.example.ekyc.service;

import com.example.ekyc.entity.FileData;
//import com.example.ekyc.entity.ImageData;
//import com.example.ekyc.respository.FileDataRepository;
//import com.example.ekyc.respository.StorageRepository;
//import com.example.ekyc.util.ImageUtils;
//import com.example.ekyc.repository.FileDataRepository;
import com.example.ekyc.repository.FileDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageService {


    @Autowired
    private FileDataRepository fileDataRepository;

    private final String FOLDER_PATH="D:\\TestImage\\NID_Cropped\\file_uploaded\\";

    public String uploadImageToFileSystem(MultipartFile file) throws IOException {
        FileData fileData = new FileData();

        UUID documentId = UUID.randomUUID();
        String filePath = FOLDER_PATH + documentId;

        fileData.setDocumentId(documentId);  // Set the UUID
        fileData.setDocumentLocation(filePath);

        fileData = fileDataRepository.save(fileData);
        System.out.println("File path: " + filePath);
        file.transferTo(new File(filePath));

        if (fileData != null) {
            return "file uploaded successfully : " + filePath;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(UUID documentId) throws IOException {
        Optional<FileData> optionalFileData = fileDataRepository.findById(documentId);
        if (optionalFileData.isPresent()) {
            String filePath = optionalFileData.get().getDocumentLocation();
            return Files.readAllBytes(new File(filePath).toPath());
        } else {
            throw new FileNotFoundException("File not found");
        }
    }

    public String updateImageInFileSystem(UUID documentId, MultipartFile file) throws IOException, FileNotFoundException {
        Optional<FileData> optionalFileData = fileDataRepository.findById(documentId);
        if (optionalFileData.isPresent()) {
            FileData fileData = optionalFileData.get();
            String filePath = fileData.getDocumentLocation();
            file.transferTo(new File(filePath));
            return "File updated successfully: " + filePath;
        } else {
            throw new FileNotFoundException("File not found");
        }
    }

    public String deleteImageFromFileSystem(UUID documentId) throws FileNotFoundException {
        Optional<FileData> optionalFileData = fileDataRepository.findById(documentId);
        if (optionalFileData.isPresent()) {
            FileData fileData = optionalFileData.get();
            String filePath = fileData.getDocumentLocation();
            File file = new File(filePath);
            if (file.delete()) {
                fileDataRepository.deleteById(documentId);
                return "File deleted successfully: " + filePath;
            } else {
                return "Failed to delete file.";
            }
        } else {
            throw new FileNotFoundException("File not found");
        }
    }



}
