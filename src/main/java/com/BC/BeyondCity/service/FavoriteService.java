package com.BC.BeyondCity.service;

import com.BC.BeyondCity.model.Favorite;
import com.BC.BeyondCity.model.Restaurant;
import com.BC.BeyondCity.model.User;
import com.BC.BeyondCity.repository.FavoriteRepository;
import com.BC.BeyondCity.repository.RestaurantRepository;
import com.BC.BeyondCity.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    private final FavoriteRepository repo;
    private final UserRepository userRepo;
    private final RestaurantRepository restRepo;

    public FavoriteService(FavoriteRepository repo, UserRepository userRepo, RestaurantRepository restRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
        this.restRepo = restRepo;
    }

    public Favorite create(Favorite f, String username) {
        User u = userRepo.findAll().stream()
                      .filter(x -> x.getUsername().equals(username))
                      .findFirst().orElseThrow();
        Restaurant rest = restRepo.findById(f.getRestaurant().getId())                           .orElseThrow();
        f.setUser(u);
        f.setRestaurant(rest);
        return repo.save(f);
    }

    public List<Favorite> findByUser(String username) {
        User u = userRepo.findAll().stream()
                      .filter(x -> x.getUsername().equals(username))
                      .findFirst().orElseThrow();
        return repo.findAll().stream()
            .filter(x -> x.getUser().getId().equals(u.getId()))
            .collect(Collectors.toList());
    }

    public void delete(Long id, String username) {
        Favorite f = repo.findById(id).orElseThrow();
        if (!f.getUser().getUsername().equals(username)) {
            throw new RuntimeException("No autorizado");
        }
        repo.delete(f);
    }
}