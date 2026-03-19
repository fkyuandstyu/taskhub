package com.taskhub.server.entity;

import jakarta.persistence.*;

@Entity // 告訴 Spring Boot，這是一個對應資料庫的實體類別
@Table(name = "users") // 在 MySQL 中建立一個名為 "users" 的資料表
public class User {

    @Id // 標記這行是主鍵 (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 設定為自動遞增 (Auto Increment)
    private Long id;

    @Column(nullable = false, unique = true, length = 50) // 帳號：不可為空、不可重複
    private String username;

    @Column(nullable = false, length = 100) // 密碼：存放雜湊後長字串，長度設 100 較保險
    private String password;

    @Column(nullable = false, unique = true, length = 100) // 信箱：不可為空、不可重複
    private String email;

    // --- 無參數建構子 (JPA 運作必須要有) ---
    public User() {
    }

    // --- 有參數建構子 (方便之後在程式裡建立使用者) ---
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // --- Getter 和 Setter (讓 Repository 和 Service 可以存取資料) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}