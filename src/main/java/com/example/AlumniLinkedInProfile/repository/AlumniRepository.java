package com.example.AlumniLinkedInProfile.repository;

import com.example.AlumniLinkedInProfile.entity.AlumniProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumniRepository extends JpaRepository<AlumniProfile, Long> {

    List<AlumniProfile> findByUniversityAndDesignationAndPassOutYear(String university, String designation, Integer passOutYear);

    List<AlumniProfile> findAllByUniversityAndDesignation(String university, String designation);
}
