package com.BC.BeyondCity.service;

import com.BC.BeyondCity.model.Review;
import com.BC.BeyondCity.model.Restaurant;
import com.BC.BeyondCity.model.User;
import com.BC.BeyondCity.repository.ReviewRepository;
import com.BC.BeyondCity.repository.RestaurantRepository;
import com.BC.BeyondCity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private final ReviewRepository repo;
    private final UserRepository userRepo;
    private final RestaurantRepository restRepo;

    public ReviewService(ReviewRepository repo, UserRepository userRepo, RestaurantRepository restRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.restRepo = restRepo;
    }

    public Review create(Review r, String username) {
        User u = userRepo.findAll().stream()
                      .filter(x -> x.getUsername().equals(username))
                      .findFirst().orElseThrow();
        Restaurant rest = restRepo.findById(r.getRestaurant().getId())                            .orElseThrow();
        r.setUser(u);
        r.setRestaurant(rest);
        return repo.save(r);
    }

    public List<Review> findByRestaurant(Long restaurantId) {
        return repo.findAll().stream()
            .filter(x -> x.getRestaurant().getId().equals(restaurantId))
            .collect(Collectors.toList());
    }

    public void delete(Long id, String username) {
        Review r = repo.findById(id).orElseThrow();
        if (!r.getUser().getUsername().equals(username)) {
            throw new RuntimeException("No autorizado");
        }
        repo.delete(r);
    }
}