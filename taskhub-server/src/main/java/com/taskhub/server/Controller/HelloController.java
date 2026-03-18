package com.taskhub.server.controller;

import com.taskhub.server.service.GreetingService; // 引入我們寫好的大腦
import org.springframework.beans.factory.annotation.Autowired; // 引入自動連線
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired // 叫 Spring 把寫好的大腦插進來
    private GreetingService greetingService;

    @GetMapping
    public String sayHello() {
        return greetingService.getHelloMessage(); // 接待員不去想邏輯，直接問大腦
    }
}