package com.taskhub.server.service;

import com.taskhub.server.repository.GreetingRepository; // 引入倉庫
import org.springframework.beans.factory.annotation.Autowired; // 引入自動連線
import org.springframework.stereotype.Service; // 標籤：宣告為大腦

@Service // 標籤：宣告這是一個業務邏輯元件
public class GreetingService {

    @Autowired // 叫 Spring 把剛才生成的倉庫機器人插進來
    private GreetingRepository greetingRepository;

    public String getHelloMessage() { // 定義一個簡單的邏輯
        return "來自 Service 層的訊息：Hello!";
    }
}