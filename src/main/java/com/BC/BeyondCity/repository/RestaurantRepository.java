package com.BC.BeyondCity.repository;

import com.BC.BeyondCity.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Busca restaurantes de un tipo de cocina dentro de un radio (km) de unas coordenadas.
     */
    @Query("""
    SELECT r FROM Restaurant r
    WHERE (:cuisine IS NULL OR LOWER(r.cuisine) = LOWER(:cuisine))
      AND (6371 * acos(
            cos(radians(:lat)) *
            cos(radians(r.latitude)) *
            cos(radians(r.longitude) - radians(:lng)) +
            sin(radians(:lat)) *
            sin(radians(r.latitude))
          )) <= :radius
    """)
    List<Restaurant> findByCuisineAndDistance(
            @Param("cuisine") String cuisine,
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("radius") double radiusKm
    );
}