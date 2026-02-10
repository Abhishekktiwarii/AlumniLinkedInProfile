package com.example.AlumniLinkedInProfile.controller;

import com.example.AlumniLinkedInProfile.DTO.RequestDTO;
import com.example.AlumniLinkedInProfile.entity.AlumniProfile;
import com.example.AlumniLinkedInProfile.service.AlumniService;
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
    @PostMapping("search")
    ResponseEntity<List<AlumniProfile>> getAllAlumniProfiles(@RequestBody RequestDTO requestDTO) {

        List<AlumniProfile> result =alumniService.searchAll(requestDTO);

        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);

    }
}
