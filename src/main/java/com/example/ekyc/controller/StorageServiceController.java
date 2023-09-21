package com.example.ekyc.controller;

import com.example.ekyc.DTO.StorageDTO;
import com.example.ekyc.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
public class StorageServiceController {

	@Autowired
	private StorageService service;

	@PostMapping("/document")
	public ResponseEntity<?> uploadImageToFIleSystem(@ModelAttribute StorageDTO dto )throws IOException {

		String uploadImage = service.uploadImageToFileSystem(dto.getFile());
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("/document/{documentId}")
	public ResponseEntity<byte[]> downloadImageFromFileSystem(@PathVariable UUID documentId) throws IOException {
		byte[] fileBytes = service.downloadImageFromFileSystem(documentId);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileBytes);
	}

	@PutMapping("/document/{documentId}")
	public ResponseEntity<String> updateImageInFileSystem(@PathVariable UUID documentId, @ModelAttribute StorageDTO dto) throws IOException {
		String updateResult = service.updateImageInFileSystem(documentId, dto.getFile());
		return ResponseEntity.status(HttpStatus.OK).body(updateResult);
	}

	@DeleteMapping("/document/{documentId}")
	public ResponseEntity<String> deleteImageFromFileSystem(@PathVariable UUID documentId) {
		try {
			String deleteResult = service.deleteImageFromFileSystem(documentId);
			return ResponseEntity.status(HttpStatus.OK).body(deleteResult);
		}
		catch (Exception e)
		{
			return null;
		}

	}
}


