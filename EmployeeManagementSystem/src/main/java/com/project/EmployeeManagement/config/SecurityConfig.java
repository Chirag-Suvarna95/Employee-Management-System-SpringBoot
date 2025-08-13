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

    // Security rules for HTTP requests
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/employees/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE") // accessible to all roles
                .requestMatchers("/employees/**").hasAnyRole("ADMIN", "MANAGER") // write actions require ADMIN or MANAGER
                .anyRequest().authenticated()
            )
            .httpBasic(); // Enable HTTP Basic auth

        return http.build();
    }

    // In-memory user details for testing
    @Bean
    public UserDetailsService users() {
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
