package com.example.springmvcboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmvcboot.model.User;
import com.example.springmvcboot.service.UserService;

/**
 * REST Controller for handling user-related operations.
 * Provides endpoints for user registration and management.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * Service layer component handling user business logic.
     */
    @Autowired
    private UserService userService;
    
    /**
     * Handles user registration requests.
     * Creates a new user account in the system.
     *
     * @param user the user details from the registration form
     * @return ResponseEntity containing the registered user (with password removed) if successful,
     *         or an error message if registration fails
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            // Don't send the encoded password back in the response
            registeredUser.setPassword(null);
            return ResponseEntity.ok(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 