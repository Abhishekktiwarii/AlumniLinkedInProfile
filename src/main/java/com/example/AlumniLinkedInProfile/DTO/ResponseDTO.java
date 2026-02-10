package com.example.AlumniLinkedInProfile.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private String status;
    private T data;


}