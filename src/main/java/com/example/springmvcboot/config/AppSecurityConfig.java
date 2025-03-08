package com.example.springmvcboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
     * Configures the password encoder for the application.
     * Uses BCrypt password hashing algorithm.
     *
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures and exposes the authentication manager.
     * The authentication manager is responsible for processing authentication requests.
     *
     * @param authConfig the authentication configuration to use
     * @return configured AuthenticationManager instance
     * @throws Exception if there's an error creating the authentication manager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures HTTP security for the application.
     * This method defines:
     * - Which URLs require authentication
     * - How users should be authenticated (form login)
     * - Authorization requirements
     *
     * Current configuration:
     * - All requests require authentication except user registration
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
                .requestMatchers("/api/users/register").permitAll()  // Allow registration without auth
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .permitAll()
            );
            
        return http.build();
    }
}
