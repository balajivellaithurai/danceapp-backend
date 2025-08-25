package com.example.danceins.controller;

import com.example.danceins.model.InstructorProfile;
import com.example.danceins.service.InstructorProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instructor/profile")
public class InstructorProfileController {

    private final InstructorProfileService profileService;

    public InstructorProfileController(InstructorProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<InstructorProfile> getProfile(@PathVariable String email) {
        return profileService.getProfileByUserEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{email}")
    public InstructorProfile createProfile(@PathVariable String email,
                                           @RequestBody InstructorProfile profile) {
        return profileService.saveOrUpdateProfile(email, profile);
    }

    @PutMapping("/{email}")
    public InstructorProfile updateProfile(@PathVariable String email,
                                           @RequestBody InstructorProfile profile) {
        return profileService.saveOrUpdateProfile(email, profile);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteProfile(@PathVariable String email) {
        profileService.deleteProfileByUserEmail(email);
        return ResponseEntity.ok("Instructor profile deleted");
    }
}
