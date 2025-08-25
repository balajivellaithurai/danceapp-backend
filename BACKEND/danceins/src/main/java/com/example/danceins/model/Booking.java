package com.example.danceins.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relation with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Relation with DanceClass
    @ManyToOne
    @JoinColumn(name = "dance_class_id")
    private DanceClass danceClass;

    // Booking status (e.g., PENDING, CONFIRMED, CANCELLED)
    private String status;

    // ✅ Explicit getters if Lombok @Data doesn't fix your IDE issue
    public User getUser() {
        return user;
    }

    public DanceClass getDanceClass() {
        return danceClass;
    }

    public String getStatus() {
        return status;
    }

    // ✅ Setters
    public void setUser(User user) {
        this.user = user;
    }

    public void setDanceClass(DanceClass danceClass) {
        this.danceClass = danceClass;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
