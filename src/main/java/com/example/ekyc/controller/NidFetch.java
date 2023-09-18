package com.example.ekyc.controller;

import com.example.ekyc.DTO.ApiResponseDTO;
import com.example.ekyc.DTO.NidRequestDTO;
import com.example.ekyc.DTO.UserDTO;
import com.example.ekyc.service.NidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController()
public class NidFetch {

    private final NidService nidService;

    @Autowired
    public NidFetch(NidService nidService) {
        this.nidService = nidService;
    }

    @PostMapping("/api-v1/getNIDInfo")
    public ResponseEntity<String> getNidInfo(@RequestBody NidRequestDTO nidRequestDTO) {

        nidRequestDTO.setUsername("Farhana.Nid");
        nidRequestDTO.setPassword("Farhana#2018@Nid");
        nidRequestDTO.setNid("3304067592");
        nidRequestDTO.setDob("1999-04-15");
        String apiUrl = "202.84.43.87:80/api-v1/getNIDInfo/";  // Replace with the actual API URL
//            ApiResponseDTO apiResponseDTO = NidService.postToExternalApi(apiUrl, nidRequestDTO);
        return ResponseEntity.ok(nidService.postToExternalApi(apiUrl, nidRequestDTO));
    }
}
