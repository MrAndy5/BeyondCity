package com.BC.BeyondCity.controller;

import com.BC.BeyondCity.model.User;
import com.BC.BeyondCity.model.Role;
import com.BC.BeyondCity.payload.LoginRequest;
import com.BC.BeyondCity.payload.SignupRequest;
import com.BC.BeyondCity.payload.JwtResponse;
import com.BC.BeyondCity.security.JwtUtils;
import com.BC.BeyondCity.security.UserDetailsImpl;
import com.BC.BeyondCity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUp) {
        User user = new User();
        user.setUsername(signUp.getUsername());
        user.setPassword(signUp.getPassword());
        user.setRole(signUp.isRestaurant() ? Role.ROLE_RESTAURANT : Role.ROLE_USER);
        userService.register(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest login) {
        Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        JwtResponse body = new JwtResponse(
            jwt,
            userDetails.getUsername(),
            userDetails.getAuthorities().iterator().next().getAuthority()
        );
        return ResponseEntity.ok(body);
    }
}
