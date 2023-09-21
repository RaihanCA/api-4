package com.example.ekyc.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class StorageDTO {
    private MultipartFile file;
}
