package com.taskhub.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 關閉 CSRF 防禦，這樣 POST 才能通
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // 允許所有請求，不需登入
                )
                .headers(headers -> headers.frameOptions(frame -> frame.disable())); // 方便之後看 H2 控制台

        return http.build();
    }
}