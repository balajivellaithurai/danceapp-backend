package com.example.danceins.controller;

import com.example.danceins.model.DanceClass;
import com.example.danceins.service.DanceClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class DanceClassController {

    private final DanceClassService danceClassService;

    public DanceClassController(DanceClassService danceClassService) {
        this.danceClassService = danceClassService;
    }

    @GetMapping
    public List<DanceClass> getAllClasses() {
        return danceClassService.getAllClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DanceClass> getClassById(@PathVariable Long id) {
        return danceClassService.getClassById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DanceClass createClass(@RequestBody DanceClass danceClass) {
        return danceClassService.createClass(danceClass);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DanceClass> updateClass(@PathVariable Long id, @RequestBody DanceClass danceClass) {
        try {
            DanceClass updatedClass = danceClassService.updateClass(id, danceClass);
            return ResponseEntity.ok(updatedClass);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        danceClassService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }
}
