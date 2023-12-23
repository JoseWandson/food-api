package com.wandson.food.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic(Customizer.withDefaults()).authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
				.requestMatchers("/v1/cozinhas/**").permitAll().anyRequest().authenticated())
				.csrf(csrf -> csrf.disable());
		return http.build();
	}

}
