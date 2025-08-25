package com.example.danceins.repository;

import com.example.danceins.model.AdminProfile;
import com.example.danceins.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminProfileRepository extends JpaRepository<AdminProfile, Long> {
    Optional<AdminProfile> findByUser(User user);
}
