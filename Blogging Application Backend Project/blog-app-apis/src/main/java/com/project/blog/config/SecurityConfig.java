package com.project.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.blog.security.JwtAuthenticationEntryPoint;
import com.project.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	            // Specific endpoints must be matched before broader patterns
	            .requestMatchers("/api/users/login", "/api/verify/**").permitAll()
	            .requestMatchers("/api/users/create").permitAll()
	            .requestMatchers("/api/**", "/api/categories/**").authenticated()
	            .anyRequest().authenticated()
	        )
	        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

	    http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	    return http.build();
	}

	 
	 
	 
	 @Bean
	 public DaoAuthenticationProvider authenticationProvider() {
		 DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		 authenticationProvider.setUserDetailsService(userDetailsService);
		 authenticationProvider.setPasswordEncoder(passwordEncoder);
		 return authenticationProvider;
	 }
	 
	 
	

}
