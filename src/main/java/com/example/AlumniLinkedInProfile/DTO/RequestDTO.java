package com.example.AlumniLinkedInProfile.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestDTO {
    @NotBlank
    private String university;
    @NotBlank
    private String designation;
    private Integer passOutYear;
//    if we put int it receives passOutYear = 0 in case we left it empty.
}
