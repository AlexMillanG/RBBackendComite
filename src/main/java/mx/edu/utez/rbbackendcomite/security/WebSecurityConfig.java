package mx.edu.utez.rbbackendcomite.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva protección CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/",              // API
                                "/swagger-ui/",      // Swagger UI
                                "/v3/api-docs/**"      // Documentación OpenAPI
                        ).permitAll()
                        .anyRequest().permitAll() // Permite todo lo demás
                );

        return http.build();
    }
}
