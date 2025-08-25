package com.example.danceins.dto;

public class BookingDTO {
    private Long id;
    private Long userId;
    private Long danceClassId;
    private String status;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDanceClassId() {
        return danceClassId;
    }

    public void setDanceClassId(Long danceClassId) {
        this.danceClassId = danceClassId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
