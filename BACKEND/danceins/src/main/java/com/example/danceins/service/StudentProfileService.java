package com.example.danceins.service;

import com.example.danceins.model.StudentProfile;
import com.example.danceins.model.User;
import com.example.danceins.repository.StudentProfileRepository;
import com.example.danceins.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;

    public StudentProfileService(StudentProfileRepository studentProfileRepository, UserRepository userRepository) {
        this.studentProfileRepository = studentProfileRepository;
        this.userRepository = userRepository;
    }

    public Optional<StudentProfile> getProfileByUserEmail(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty())
            return Optional.empty();
        return studentProfileRepository.findByUser(userOpt.get());
    }

    public StudentProfile saveOrUpdateProfile(String email, StudentProfile profile) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Optional<StudentProfile> existingProfileOpt = studentProfileRepository.findByUser(user);

        StudentProfile profileToSave;
        if (existingProfileOpt.isPresent()) {
            profileToSave = existingProfileOpt.get();
            profileToSave.setLearningPreferences(profile.getLearningPreferences());
            profileToSave.setProgressJson(profile.getProgressJson());
        } else {
            profileToSave = profile;
            profileToSave.setUser(user);
        }

        return studentProfileRepository.save(profileToSave);
    }

    public void deleteProfileByUserEmail(String email) {
        getProfileByUserEmail(email).ifPresent(studentProfileRepository::delete);
    }
}
