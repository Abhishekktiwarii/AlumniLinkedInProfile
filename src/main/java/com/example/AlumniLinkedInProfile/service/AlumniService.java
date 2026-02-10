package com.example.AlumniLinkedInProfile.service;

import com.example.AlumniLinkedInProfile.DTO.RequestDTO;
import com.example.AlumniLinkedInProfile.entity.AlumniProfile;

import java.util.List;

public interface AlumniService {
    List<AlumniProfile> searchAll(RequestDTO requestDTO);
    List<AlumniProfile> findAll();
}
