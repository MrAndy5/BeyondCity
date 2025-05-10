package com.BC.BeyondCity.controller;

import com.BC.BeyondCity.model.Favorite;
import com.BC.BeyondCity.service.FavoriteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:5173")
public class FavoriteController {
    private final FavoriteService service;
    public FavoriteController(FavoriteService service) { this.service = service; }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Favorite addFavorite(@RequestBody Favorite fav, Principal principal) {
        return service.create(fav, principal.getName());
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<Favorite> getFavorites(Principal principal) {
        return service.findByUser(principal.getName());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteFavorite(@PathVariable Long id, Principal principal) {
        service.delete(id, principal.getName());
    }
}
