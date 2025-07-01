
package com.janoscars.janoscars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Habilita a segurança web do Spring Security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desabilita a proteção CSRF (necessário para Postman/APIs REST sem formulários HTML)
        http.csrf(csrf -> csrf.disable())
            // Permite todas as requisições (qualquer URL) sem autenticação
            .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll());

        return http.build();
    }
}