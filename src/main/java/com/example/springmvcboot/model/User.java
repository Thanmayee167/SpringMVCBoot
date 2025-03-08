package com.example.springmvcboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a user in the system.
 * This class maps to the 'users' table in the database and contains
 * basic user information including authentication and authorization details.
 */
@Entity
@Table(name = "users")
public class User {
    
    /**
     * The unique identifier for the user.
     * This field is automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * The username used for authentication.
     * Must be unique within the system.
     */
    private String username;

    /**
     * The user's password.
     * This field stores the encrypted password, never the plain text.
     */
    private String password;

    /**
     * The user's role in the system.
     * Used for authorization and access control.
     */
    private String role;

    /**
     * Flag indicating whether the user account is enabled.
     * Disabled accounts cannot authenticate.
     */
    private boolean enabled = true;

    /**
     * Default constructor required by JPA.
     */
    public User() {}

    /**
     * Gets the user's unique identifier.
     * @return the user's ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     * @param id the ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the username.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the encrypted password.
     * @return the encrypted password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     * @param password the password to set (will be encrypted before storage)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's role.
     * @return the user's role
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Checks if the user account is enabled.
     * @return true if the account is enabled, false otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the enabled status of the user account.
     * @param enabled true to enable the account, false to disable it
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
} 