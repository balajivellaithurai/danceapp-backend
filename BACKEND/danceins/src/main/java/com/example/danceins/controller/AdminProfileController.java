package com.example.danceins.controller;

import com.example.danceins.model.AdminProfile;
import com.example.danceins.service.AdminProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/profile")
public class AdminProfileController {

    private final AdminProfileService adminProfileService;

    public AdminProfileController(AdminProfileService adminProfileService) {
        this.adminProfileService = adminProfileService;
    }

    @GetMapping("/{email}")
    public ResponseEntity<AdminProfile> getProfile(@PathVariable String email) {
        return adminProfileService.getProfileByUserEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{email}")
    public AdminProfile createProfile(@PathVariable String email,
                                      @RequestBody AdminProfile profile) {
        return adminProfileService.saveOrUpdateProfile(email, profile);
    }

    @PutMapping("/{email}")
    public AdminProfile updateProfile(@PathVariable String email,
                                      @RequestBody AdminProfile profile) {
        return adminProfileService.saveOrUpdateProfile(email, profile);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteProfile(@PathVariable String email) {
        adminProfileService.deleteProfileByUserEmail(email);
        return ResponseEntity.ok("Admin profile deleted");
    }
}
