package com.example.danceins.service;

import com.example.danceins.model.DanceClass;
import com.example.danceins.repository.DanceClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DanceClassService {

    private final DanceClassRepository danceClassRepository;

    public DanceClassService(DanceClassRepository danceClassRepository) {
        this.danceClassRepository = danceClassRepository;
    }

    public List<DanceClass> getAllClasses() {
        return danceClassRepository.findAll();
    }

    public Optional<DanceClass> getClassById(Long id) {
        return danceClassRepository.findById(id);
    }

    public DanceClass createClass(DanceClass danceClass) {
        return danceClassRepository.save(danceClass);
    }

    public DanceClass updateClass(Long id, DanceClass updatedClass) {
        return danceClassRepository.findById(id).map(existingClass -> {
            existingClass.setName(updatedClass.getName());
            existingClass.setInstructor(updatedClass.getInstructor());
            existingClass.setSchedule(updatedClass.getSchedule());
            existingClass.setCapacity(updatedClass.getCapacity());
            return danceClassRepository.save(existingClass);
        }).orElseThrow(() -> new RuntimeException("Dance Class not found with id " + id));
    }

    public void deleteClass(Long id) {
        danceClassRepository.deleteById(id);
    }
}
