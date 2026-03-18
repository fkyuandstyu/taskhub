package com.taskhub.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 關閉 CSRF 防護 (這是 POST 會失敗的罪魁禍首)
                .csrf(csrf -> csrf.disable())

                // 2. 設定權限：允許所有人存取 API，不需登入也能測 (開發最方便)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // 3. 雖然 permitAll 了，但我們保留 Basic Auth 的支援 (以防萬一)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}