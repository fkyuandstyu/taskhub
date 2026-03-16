package com.taskhub.server.entity;

// 這些 import 代表我們要用到別人寫好的工具箱
import jakarta.persistence.*; // Java 標準的資料庫存取工具 (JPA)
import lombok.Data;          // 幫我們減少重複代碼的工具
import java.time.LocalDateTime; // Java 處理日期與時間的標準類別

/**
 * Task 類別：這是一個 Entity (實體)
 * 它代表了資料庫中的一張資料表，也代表程式中的一個物件模具。
 */
@Entity
@Table(name = "tasks") // 明確指定資料庫裡的資料表名稱為 "tasks"
@Data // 重點：這會自動產生所有變數的 Getter/Setter、toString() 等方法
public class Task {

    /**
     * @Id: 代表這個變數是「主鍵」(Primary Key)，每個任務都有唯一的身份證。
     * @GeneratedValue: 告訴資料庫「你自己幫我數」，從 1, 2, 3 這樣自動加上去。
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 使用 Long (長整數) 是為了容納大量的任務資料

    /**
     * @Column: 用來定義資料庫欄位的細節。
     * nullable = false 代表在儲存時，這個標題絕對不能是空的。
     */
    @Column(nullable = false, length = 100)
    private String title;

    // 如果沒有加上 @Column，Spring 預設會自動產生一個跟變數同名的欄位
    private String description;

    // 預設值設為 false，代表任務剛建立時都是「未完成」狀態
    private boolean completed = false;

    // 用來記錄這筆資料是什麼時候建立的
    private LocalDateTime createdAt;

    /**
     * @PrePersist: 這是一個「生命週期鉤子」。
     * 意思是在這筆資料「正式存入」資料庫之前，Java 會自動執行這個方法。
     * 這樣我們就不用手動去設定時間，程式會幫我們抓取「當下」的時間。
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /* 隱藏的小祕密：
       因為上方寫了 @Data，雖然你看不到，但其實這程式碼裡已經存在了：
       public void setTitle(String title) { ... }
       public String getTitle() { ... }
       這就是 Java 的「封裝」體現。
    */
}