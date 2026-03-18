package com.taskhub.server.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "greetings")
public class Greeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    // --- 空參數建構子 (JPA 必須) ---
    public Greeting() {}

    // --- 有參數建構子 (方便建立資料) ---
    public Greeting(String message) {
        this.message = message;
    }

    // --- Getter and Setter (手動補齊) ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}