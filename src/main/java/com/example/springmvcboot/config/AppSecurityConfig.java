package com.example.springmvcboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.DispatcherType;

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
     * This method sets up various security configurations including:
     * 
     * <ul>
     *   <li>CSRF protection (disabled in this configuration)</li>
     *   <li>URL authorization rules:
     *     <ul>
     *       <li>Permits access to forward dispatcher types</li>
     *       <li>Allows public access to /login and /api/users/register endpoints</li>
     *       <li>Requires authentication for all other requests</li>
     *     </ul>
     *   </li>
     *   <li>Form-based login configuration:
     *     <ul>
     *       <li>Custom login page at /login</li>
     *       <li>Redirect to /home after successful login</li>
     *     </ul>
     *   </li>
     *   <li>Logout handling:
     *     <ul>
     *       <li>Logout URL at /logout</li>
     *       <li>Redirect to /logout-success after logout</li>
     *       <li>Invalidates HTTP session</li>
     *       <li>Clears authentication</li>
     *     </ul>
     *   </li>
     * </ul>
     *
     * @param http HttpSecurity object to configure
     * @return Configured SecurityFilterChain
     * @throws Exception if an error occurs during security configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                    .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                    .requestMatchers("/login","/api/users/register").permitAll()  // Allow login page and static resources
                    .anyRequest().authenticated()  // Protect all other pages
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/logout-success")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            );

        return http.build();
    }
}
