package com.example.danceins.service;

import com.example.danceins.model.InstructorProfile;
import com.example.danceins.model.User;
import com.example.danceins.repository.InstructorProfileRepository;
import com.example.danceins.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorProfileService {

    private final InstructorProfileRepository profileRepository;
    private final UserRepository userRepository;

    public InstructorProfileService(InstructorProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    public Optional<InstructorProfile> getProfileByUserEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return Optional.empty();
        return profileRepository.findByUser(userOpt.get());
    }

    public InstructorProfile saveOrUpdateProfile(String email, InstructorProfile profile) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<InstructorProfile> existingProfileOpt = profileRepository.findByUser(user);

        InstructorProfile profileToSave = existingProfileOpt.orElseGet(InstructorProfile::new);
        profileToSave.setUser(user);
        profileToSave.setExpertise(profile.getExpertise());
        profileToSave.setDanceStyles(profile.getDanceStyles());
        profileToSave.setYearsExperience(profile.getYearsExperience());
        profileToSave.setCertifications(profile.getCertifications());
        profileToSave.setProfilePhotoUrl(profile.getProfilePhotoUrl());
        profileToSave.setDemoVideoUrl(profile.getDemoVideoUrl());
        profileToSave.setAvailabilityJson(profile.getAvailabilityJson());

        return profileRepository.save(profileToSave);
    }

    public void deleteProfileByUserEmail(String email) {
        getProfileByUserEmail(email).ifPresent(profileRepository::delete);
    }
}
