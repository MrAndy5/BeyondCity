package com.BC.BeyondCity.service;

import com.BC.BeyondCity.model.Restaurant;
import com.BC.BeyondCity.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository repo;

    public RestaurantService(RestaurantRepository repo) {
        this.repo = repo;
    }

    public List<Restaurant> findAll() {
        return repo.findAll();
    }

    public Restaurant findById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RuntimeException("Restaurante no encontrado: " + id));
    }

    public Restaurant create(Restaurant r) {
        return repo.save(r);
    }

    public Restaurant update(Long id, Restaurant r) {
        Restaurant existing = findById(id);
        existing.setName(r.getName());
        existing.setLatitude(r.getLatitude());
        existing.setLongitude(r.getLongitude());
        existing.setCuisine(r.getCuisine());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Restaurant> findByFilters(String cuisine, double lat, double lng, double radiusKm) {
        return repo.findAll().stream()
            .filter(r -> (cuisine == null || r.getCuisine().equalsIgnoreCase(cuisine)))
            .filter(r -> haversineDistance(lat, lng, r.getLatitude(), r.getLongitude()) <= radiusKm)
            .collect(Collectors.toList());
    }

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }
}
