package com.example.ekyc.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseDTO {
    String code;
    String message;
    String nid;
    String name;
    String nameBan;
    String father;
    String mother;
    String presentAddress;
    String permanentAddress;
    String dob;
    String gender;
    String photo;


}
