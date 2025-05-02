package com.BC.BeyondCity.repository;

import com.BC.BeyondCity.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findByRestaurantId(Long restaurantId);
}