package com.example.ekyc.controller;

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
@RequestMapping(value = "/api", produces = "application/json;charset=UTF-8")
public class StorageServiceController {

	@Autowired
	private StorageService service;

	@PostMapping("/document")
	public ResponseEntity<?> uploadImageToFIleSystem(@RequestParam("image") MultipartFile file) throws IOException {
		String uploadImage = service.uploadImageToFileSystem(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

//	@GetMapping("/document/{documentId}")
//	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable UUID documentId) throws IOException {
//		byte[] imageData = service.downloadImageFromFileSystem(fileName);
//		return ResponseEntity.status(HttpStatus.OK)
//				.contentType(MediaType.valueOf("image/png"))
//				.body(imageData);
//
//	}
}


