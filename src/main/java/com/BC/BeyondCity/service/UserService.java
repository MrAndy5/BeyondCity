package com.BC.BeyondCity.service;

import com.BC.BeyondCity.model.User;
import com.BC.BeyondCity.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User u) {
        boolean exists = userRepo.findAll().stream()
            .anyMatch(existing -> existing.getUsername().equalsIgnoreCase(u.getUsername()));
        if (exists) {
            throw new IllegalArgumentException("Username ya en uso");
        }
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepo.save(u);
    }

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public User findById(Long id) {
        return userRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + id));
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}