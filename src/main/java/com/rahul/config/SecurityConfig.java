package com.rahul.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 1) Allow all requests to be authentication
        // 2) if request is not authenticated, then use HTTP Basic authentication
        // 3) Disable CSRF protection : POST, PUT, DELETE requests from Postman will be blocked without CSRF token
        http
                .authorizeHttpRequests(auth ->
                        auth.anyRequest().authenticated()
                );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}
