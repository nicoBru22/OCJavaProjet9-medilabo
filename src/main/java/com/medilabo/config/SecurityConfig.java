package com.medilabo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
	
	private Logger logger = LogManager.getLogger();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())              // désactive CSRF
                .authorizeHttpRequests(auth -> auth
                                .anyRequest().permitAll()  // autorise toutes les requêtes
                )
                .formLogin(login -> login.disable())         // désactive le formulaire de login
                .httpBasic(basic -> basic.disable());        // désactive l’authentification basique

        logger.info("initialisation de la sécurité réussie.");
        
        return http.build();
        
    }
}
