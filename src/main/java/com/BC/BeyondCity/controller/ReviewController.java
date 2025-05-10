package com.BC.BeyondCity.controller;

import com.BC.BeyondCity.model.Review;
import com.BC.BeyondCity.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173")
public class ReviewController {
    private final ReviewService service;
    public ReviewController(ReviewService service) { this.service = service; }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Review addReview(@RequestBody Review review, Principal principal) {
        return service.create(review, principal.getName());
    }

    @GetMapping("/restaurant/{id}")
    public List<Review> getByRestaurant(@PathVariable Long id) {
        return service.findByRestaurant(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteReview(@PathVariable Long id, Principal principal) {
        service.delete(id, principal.getName());
    }
}
