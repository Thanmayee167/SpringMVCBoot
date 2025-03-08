package com.example.springmvcboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springmvcboot.model.User;
import com.example.springmvcboot.repository.UserRepository;

/**
 * Service class handling user-related business logic.
 * Provides methods for user registration and management.
 */
@Service
public class UserService {

    /**
     * Repository for user data persistence.
     */
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Password encoder for secure password storage.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * Registers a new user in the system.
     * This method performs the following operations:
     * - Validates that the username is unique
     * - Ensures the password is not empty
     * - Sets a default role if none is provided
     * - Encrypts the password before storage
     *
     * @param user the user to register
     * @return the registered user with encrypted password
     * @throws RuntimeException if username already exists or password is empty
     */
    public User registerUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        
        // Validate password is not empty
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new RuntimeException("Password cannot be empty");
        }

        // Set default role if not provided
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            user.setRole("USER");
        }
        
        // Encode the password before saving
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        return userRepository.save(user);
    }
}