package com.example.danceins.service;

import com.example.danceins.model.AdminProfile;
import com.example.danceins.model.User;
import com.example.danceins.repository.AdminProfileRepository;
import com.example.danceins.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminProfileService {

    private final AdminProfileRepository adminProfileRepository;
    private final UserRepository userRepository;

    public AdminProfileService(AdminProfileRepository adminProfileRepository, UserRepository userRepository) {
        this.adminProfileRepository = adminProfileRepository;
        this.userRepository = userRepository;
    }

    public Optional<AdminProfile> getProfileByUserEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return Optional.empty();
        return adminProfileRepository.findByUser(userOpt.get());
    }

    public AdminProfile saveOrUpdateProfile(String email, AdminProfile profile) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<AdminProfile> existingProfileOpt = adminProfileRepository.findByUser(user);

        AdminProfile profileToSave;
        if (existingProfileOpt.isPresent()) {
            profileToSave = existingProfileOpt.get();
            profileToSave.setAdminNotes(profile.getAdminNotes());
        } else {
            profileToSave = profile;
            profileToSave.setUser(user);
        }

        return adminProfileRepository.save(profileToSave);
    }

    public void deleteProfileByUserEmail(String email) {
        getProfileByUserEmail(email).ifPresent(adminProfileRepository::delete);
    }
}
