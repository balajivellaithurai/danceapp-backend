package com.example.danceins.repository;

import com.example.danceins.model.InstructorProfile;
import com.example.danceins.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InstructorProfileRepository extends JpaRepository<InstructorProfile, Long> {
    Optional<InstructorProfile> findByUser(User user);
}
