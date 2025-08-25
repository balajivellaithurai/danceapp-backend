package com.example.danceins.dto;

import java.time.LocalDate;

public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long danceClassId;
    private Integer rating;
    private String comment;
    private LocalDate reviewDate;

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
