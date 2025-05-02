package com.BC.BeyondCity.entity;

import com.BC.BeyondCity.model.Restaurant;
import jakarta.persistence.*;

@Entity
public class Favorite {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    // getters/setters, constructor vacío...
    public Favorite(User user, Restaurant restaurant) {
        this.user = user;
        this.restaurant = restaurant;
    }
    public Favorite() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


}