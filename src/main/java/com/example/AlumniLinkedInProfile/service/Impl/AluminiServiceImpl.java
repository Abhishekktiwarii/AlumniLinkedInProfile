package com.example.AlumniLinkedInProfile.service.Impl;

import com.example.AlumniLinkedInProfile.DTO.RequestDTO;
import com.example.AlumniLinkedInProfile.entity.AlumniProfile;
import com.example.AlumniLinkedInProfile.repository.AlumniRepository;
import com.example.AlumniLinkedInProfile.service.AlumniService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AluminiServiceImpl implements AlumniService {


    private AlumniRepository alumniRepository;
    public AluminiServiceImpl(AlumniRepository alumniRepository) {
        this.alumniRepository = alumniRepository;
    }

    @Override
    public List<AlumniProfile> searchAll(RequestDTO requestDTO) {
        String university = requestDTO.getUniversity();
        String designation = requestDTO.getDesignation();
        Integer passOutYear = requestDTO.getPassOutYear();

        if(passOutYear!=null){
            return alumniRepository.findByUniversityAndDesignationAndPassOutYear(university
            , designation, passOutYear);
        }

        return alumniRepository.findAllByUniversityAndDesignation(university, designation);
    }

    @Override
    public List<AlumniProfile> findAll() {
        return alumniRepository.findAll();
    }

}
