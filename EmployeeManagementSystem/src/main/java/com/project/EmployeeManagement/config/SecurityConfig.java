package com.project.EmployeeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for REST API
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()  // Require authentication for all endpoints
            )
            .httpBasic();  // Enable HTTP Basic authentication

        return http.build();
    }

    // In-memory users for testing with different roles
    @Bean
    UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
            User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminpass")
                .roles("ADMIN")
                .build(),
            User.withDefaultPasswordEncoder()
                .username("manager")
                .password("managerpass")
                .roles("MANAGER")
                .build(),
            User.withDefaultPasswordEncoder()
                .username("employee")
                .password("employeepass")
                .roles("EMPLOYEE")
                .build()
        );
    }
}

