package com.example.ekyc.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NidResponseDTO {
    private int code;
    private String message;
    private String nid;
    private String name;
    private String nameBan;
    private String father;
    private String mother;
    private String presentAddress;
    private String permanentAddress;
    private String dob;
    private String gender;
    private byte[] photo;
}
