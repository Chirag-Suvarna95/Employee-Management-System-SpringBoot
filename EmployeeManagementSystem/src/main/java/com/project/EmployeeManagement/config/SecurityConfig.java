package com.project.EmployeeManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF for APIs
				.authorizeHttpRequests(auth -> auth
						// Reports access for all roles
						.requestMatchers(HttpMethod.GET, "/employees/reports/**")
						.hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")

						// Read access for all roles
						.requestMatchers(HttpMethod.GET, "/employees/**").hasAnyRole("ADMIN", "MANAGER", "EMPLOYEE")

						// Create and update for ADMIN and MANAGER
						.requestMatchers(HttpMethod.POST, "/employees/**").hasAnyRole("ADMIN", "MANAGER")
						.requestMatchers(HttpMethod.PUT, "/employees/**").hasAnyRole("ADMIN", "MANAGER")

						// Delete for ADMIN only
						.requestMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")

						// All other requests require authentication
						.anyRequest().authenticated())

				.httpBasic(); // Enable HTTP Basic auth for REST

		return http.build();
	}

	// In-memory users for testing
	@Bean
	UserDetailsService users() {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder().username("admin").password("adminpass").roles("ADMIN").build(),
				User.withDefaultPasswordEncoder().username("manager").password("managerpass").roles("MANAGER").build(),
				User.withDefaultPasswordEncoder().username("employee").password("employeepass").roles("EMPLOYEE")
						.build());
	}
}
