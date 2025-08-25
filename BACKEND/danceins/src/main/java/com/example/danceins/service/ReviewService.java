package com.example.danceins.service;

import com.example.danceins.model.Review;
import com.example.danceins.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    public Review updateReview(Long id, Review details) {
        return reviewRepository.findById(id).map(r -> {
            r.setUser(details.getUser());
            r.setDanceClass(details.getDanceClass());
            r.setRating(details.getRating());
            r.setComment(details.getComment());
            r.setReviewDate(details.getReviewDate());
            return reviewRepository.save(r);
        }).orElseThrow(() -> new RuntimeException("Review not found with id " + id));
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
