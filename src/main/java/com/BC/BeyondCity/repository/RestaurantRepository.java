package com.BC.BeyondCity.repository;

import com.BC.BeyondCity.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Más adelante añadiremos consultas personalizadas por filtros
}
