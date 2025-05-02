package com.BC.BeyondCity.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")   // evita colisión con palabra reservada
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;      // en producción, almacena el hash

    // Relación con reseñas y favoritos
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Favorite> favorites;

    // getters/setters, constructor vacío...
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Review> getReviews() {
        return reviews;
    }
    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
    public Set<Favorite> getFavorites() {
        return favorites;
    }
    public void setFavorites(Set<Favorite> favorites) {
        this.favorites = favorites;
    }

}