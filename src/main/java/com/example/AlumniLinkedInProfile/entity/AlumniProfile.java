package com.example.AlumniLinkedInProfile.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "alumni_profiles")
public class AlumniProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "designation")
    private String designation;

    @Column(name = "university")
    private String university;

    @Column(name = "location")
    private String location;

    @Column(name = "linkedInHeadline")
    private String linkedInHeadLine;

    @Column(name = "passOutYear")
    private Integer passOutYear;
}
