package com.taskhub.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // 必備
import org.springframework.security.crypto.password.PasswordEncoder;   // 必備
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //這會產生一個 PasswordEncoder 放在 Spring 容器裡
    // 這樣 UserService 才能透過 @Autowired 找到它
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // 將回傳的物件註冊為 Spring 容器中的一個 Bean，供系統使用。
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 1. 關閉 CSRF 保護 (在開發階段為了測試方便常先關閉，但生產環境建議開啟)
                //.csrf(csrf -> csrf.disable())

                // 2. 設定請求的授權規則
                .authorizeHttpRequests(auth -> auth
                        // 這些路徑（註冊、登入、靜態 CSS 檔案）「所有人」都能直接存取，不用登入。
                        .requestMatchers("/register", "/login", "/css/**").permitAll()
                        // 除了上面以外的所有請求，都必須「經過身份驗證（登入）」才能進入。
                        .anyRequest().authenticated()
                )

                // 3. 設定表單登入邏輯
                .formLogin(form -> form
                        .loginPage("/login") // 自定義的登入頁面網址
                        .loginProcessingUrl("/login") // HTML 表單提交 (POST) 的目標網址
                        .defaultSuccessUrl("/dashboard", true) // 登入成功後跳轉到的首頁
                        .failureUrl("/login?error") // 登入失敗後回到的頁面（帶上 error 參數）
                        .permitAll() // 允許所有人存取登入相關功能
                )

                // 4. 設定登出邏輯
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // 登出成功後跳轉到的頁面
                        .permitAll()
                );

        return http.build(); // 建構出這一套安全過濾鏈
    }
}