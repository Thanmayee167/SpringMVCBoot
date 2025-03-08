package com.example.springmvcboot.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springmvcboot.repository.UserRepository;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 * This service loads user-specific data for authentication.
 * It bridges the gap between Spring Security's authentication system
 * and our custom user data storage.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repository for accessing user data.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads a user's details by username for authentication purposes.
     * This method is called by Spring Security during the authentication process.
     *
     * @param username the username to load
     * @return UserDetails object containing the user's authentication information
     * @throws UsernameNotFoundException if no user is found with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .map(user -> new User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
            ))
            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
} 