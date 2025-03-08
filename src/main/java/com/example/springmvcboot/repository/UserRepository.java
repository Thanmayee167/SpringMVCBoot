package com.example.springmvcboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springmvcboot.model.User;

/**
 * Repository interface for User entity.
 * Provides CRUD operations and custom queries for User entities.
 * Extends JpaRepository to inherit basic database operations.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Finds a user by their username.
     * 
     * @param username the username to search for
     * @return an Optional containing the user if found, or empty if not found
     */
    Optional<User> findByUsername(String username);
} 