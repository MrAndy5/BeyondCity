package com.BC.BeyondCity.service;

import com.BC.BeyondCity.model.Restaurant;
import com.BC.BeyondCity.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

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
}
