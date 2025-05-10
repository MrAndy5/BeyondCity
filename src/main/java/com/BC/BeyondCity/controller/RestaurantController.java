package com.BC.BeyondCity.controller;

import com.BC.BeyondCity.model.Restaurant;
import com.BC.BeyondCity.service.RestaurantService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:5173")
public class RestaurantController {
    private final RestaurantService service;
    public RestaurantController(RestaurantService service) { this.service = service; }

    @GetMapping
    public List<Restaurant> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Restaurant getById(@PathVariable Long id) { return service.findById(id); }

    @GetMapping("/search")
    public List<Restaurant> search(
        @RequestParam(required = false) String cuisine,
        @RequestParam double lat,
        @RequestParam double lng,
        @RequestParam(defaultValue = "5") double radiusKm) {
        return service.findByFilters(cuisine, lat, lng, radiusKm);
    }

    @PostMapping
    @PreAuthorize("hasRole('RESTAURANT')")
    public Restaurant create(@RequestBody Restaurant r) {
        return service.create(r);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public Restaurant update(@PathVariable Long id, @RequestBody Restaurant r) {
        return service.update(id, r);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('RESTAURANT')")
    public void delete(@PathVariable Long id) { service.delete(id); }
}