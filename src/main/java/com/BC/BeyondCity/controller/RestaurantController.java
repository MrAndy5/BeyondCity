package com.BC.BeyondCity.controller;

import com.BC.BeyondCity.model.Restaurant;
import com.BC.BeyondCity.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@CrossOrigin(origins = "http://localhost:5173") // ajusta al puerto de tu frontend
public class RestaurantController {
    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return service.findAll();
    }

    @PostMapping
    public Restaurant create(@RequestBody Restaurant r) {
        return service.save(r);
    }
}
