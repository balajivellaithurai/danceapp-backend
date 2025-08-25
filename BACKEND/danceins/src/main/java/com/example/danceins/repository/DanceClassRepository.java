package com.example.danceins.repository;

import com.example.danceins.model.DanceClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanceClassRepository extends JpaRepository<DanceClass, Long> {
    // Example: find classes by instructor
    // List<DanceClass> findByInstructorId(Long instructorId);
}
