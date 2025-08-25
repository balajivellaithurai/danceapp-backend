package com.example.danceins.controller;

import com.example.danceins.dto.AuthRequest;
import com.example.danceins.dto.RegisterRequest;
import com.example.danceins.model.User;
import com.example.danceins.security.JwtUtil;
import com.example.danceins.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
            UserService userService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest request) {
        System.out.println("AuthController - Login attempt for email: " + request.getEmail());
        System.out.println("AuthController - Request body: " + request.getEmail() + ", password length: "
                + (request.getPassword() != null ? request.getPassword().length() : "null"));

        try {
            System.out.println("AuthController - Attempting authentication...");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            System.out.println("AuthController - Authentication successful");

            System.out.println("AuthController - Generating JWT token...");
            String token = jwtUtil.generateToken(request.getEmail());
            System.out.println("AuthController - JWT token generated: " + token.substring(0, 20) + "...");

            System.out.println("AuthController - Looking up user in database...");
            User user = userService.getUserByEmail(request.getEmail())
                    .orElse(null);
            if (user == null) {
                System.out.println("AuthController - User not found in database");
                return ResponseEntity.status(404).body("User not found");
            }

            System.out.println("AuthController - User found: " + user.getName() + " with role: " + user.getRole());

            Map<String, Object> responseBody = Map.of(
                    "token", token,
                    "id", user.getId(),
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "role", user.getRole());

            System.out.println("AuthController - Login successful, returning response with user ID: " + user.getId());
            return ResponseEntity.ok(responseBody);
        } catch (BadCredentialsException e) {
            System.out.println("AuthController - Authentication failed: " + e.getMessage());
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            System.out.println("AuthController - Unexpected error during login: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        System.out.println("AuthController - Registration attempt for email: " + request.getEmail());
        System.out.println("AuthController - Registration data: " + request.getName() + ", role: " + request.getRole());

        try {
            if (userService.getUserByEmail(request.getEmail()).isPresent()) {
                System.out.println("AuthController - Email already taken: " + request.getEmail());
                return ResponseEntity.badRequest().body("Email is already taken");
            }

            System.out.println("AuthController - Creating new user...");
            User user = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(request.getRole())
                    .build();

            User savedUser = userService.createUser(user);
            System.out.println("AuthController - User registered successfully: " + savedUser.getName() + " with ID: "
                    + savedUser.getId());

            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            System.out.println("AuthController - Registration failed: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).body("Registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Backend is running!");
    }
}
