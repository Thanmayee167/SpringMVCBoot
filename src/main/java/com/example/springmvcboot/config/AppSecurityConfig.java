package com.example.springmvcboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application.
 * This class provides the basic security setup including user authentication
 * and HTTP security configuration.
 *
 * @Configuration indicates that this class contains Spring bean definitions
 * @EnableWebSecurity enables Spring Security's web security support
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    /**
     * Configures the user details service for authentication.
     * Creates an in-memory user store with a single user for testing purposes.
     *
     * Note: This implementation is for demonstration purposes only.
     * In a production environment, you should:
     * - Use a secure password encoder (e.g., BCryptPasswordEncoder)
     * - Store user credentials in a database
     * - Implement proper user management
     *
     * @return UserDetailsService implementation with an in-memory user store
     */
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Configures HTTP security for the application.
     * This method defines:
     * - Which URLs require authentication
     * - How users should be authenticated (form login)
     * - Authorization requirements
     *
     * Current configuration:
     * - All requests require authentication
     * - Form-based login is enabled and accessible without authentication
     * - Default Spring Security login form is used
     *
     * @param http HttpSecurity object to configure
     * @return Configured SecurityFilterChain
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .permitAll()
            );
            
        return http.build();
    }
}
