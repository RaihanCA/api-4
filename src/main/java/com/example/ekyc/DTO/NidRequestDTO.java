package com.example.ekyc.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NidRequestDTO {
    private String username;
    private String password;
    private String nid;
    private String dob;
}
