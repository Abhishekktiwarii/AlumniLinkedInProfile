package com.example.AlumniLinkedInProfile.controller;

import com.example.AlumniLinkedInProfile.DTO.RequestDTO;
import com.example.AlumniLinkedInProfile.DTO.ResponseDTO;
import com.example.AlumniLinkedInProfile.entity.AlumniProfile;
import com.example.AlumniLinkedInProfile.service.AlumniService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {


    AlumniService alumniService;

    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }
    @PostMapping("/search")
    public ResponseEntity<ResponseDTO<List<AlumniProfile>>> searchAlumni(
            @Valid @RequestBody RequestDTO requestDTO
    ) {
        List<AlumniProfile> result =
                alumniService.searchAll(requestDTO);

        return ResponseEntity.ok(
                new ResponseDTO<>("success", result));
    }
}
