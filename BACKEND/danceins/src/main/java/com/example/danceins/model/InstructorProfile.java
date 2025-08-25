package com.example.danceins.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "instructor_profiles")
public class InstructorProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    private String expertise;
    private String danceStyles;
    private Integer yearsExperience;
    private String certifications;
    private String profilePhotoUrl;
    private String demoVideoUrl;

    @Lob
    private String availabilityJson;
}
