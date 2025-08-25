package com.example.danceins.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorProfileDTO {
    private String expertise;
    private String danceStyles;
    private Integer yearsExperience;
    private String certifications;
    private String profilePhotoUrl;
    private String demoVideoUrl;
    private String availabilityJson;
}
