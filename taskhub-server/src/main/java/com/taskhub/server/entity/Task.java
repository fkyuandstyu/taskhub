package com.taskhub.server.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 任務標題

    private String description; // 任務內容

    private boolean completed = false; // 是否完成，預設為 false

    private LocalDateTime createdAt = LocalDateTime.now(); // 建立時間

    // --- JPA 必須的空建構子 ---
    public Task() {}

    // --- 方便新增資料的建構子 ---
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // --- Getter and Setter ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}