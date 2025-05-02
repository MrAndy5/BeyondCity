package com.BC.BeyondCity.service;

import com.BC.BeyondCity.model.Restaurant;
import com.BC.BeyondCity.repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public List<Restaurant> findAll() {
        return repo.findAll();
    }

    public Restaurant save(Restaurant r) {
        return repo.save(r);
    }

    public List<Restaurant> findByFilters(String cuisine, double lat, double lng, double radiusKm) {
        return repo.findByCuisineAndDistance(cuisine, lat, lng, radiusKm);
    }

    @GetMapping("/search")
    public List<Restaurant> search(
            @RequestParam(required = false) String cuisine,
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam(defaultValue = "5") double radiusKm
    ) {
        return this.findByFilters(cuisine, lat, lng, radiusKm);
    }
}
