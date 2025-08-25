package com.example.danceins.controller;

import com.example.danceins.model.StudentProfile;
import com.example.danceins.service.StudentProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student/profile")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<StudentProfile> getProfile(@PathVariable String email) {
        return studentProfileService.getProfileByUserEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{email}")
    public StudentProfile createProfile(@PathVariable String email,
                                        @RequestBody StudentProfile profile) {
        return studentProfileService.saveOrUpdateProfile(email, profile);
    }

    @PutMapping("/{email}")
    public StudentProfile updateProfile(@PathVariable String email,
                                        @RequestBody StudentProfile profile) {
        return studentProfileService.saveOrUpdateProfile(email, profile);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteProfile(@PathVariable String email) {
        studentProfileService.deleteProfileByUserEmail(email);
        return ResponseEntity.ok("Student profile deleted");
    }
}
