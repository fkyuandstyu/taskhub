package com.taskhub.server.controller;

import com.taskhub.server.entity.User;
import com.taskhub.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // 顯示註冊頁面
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // 對應到 templates/register.html
    }

    // 處理註冊表單提交
    @PostMapping("/register")
    public String handleRegistration(User user) {
        userService.registerNewUser(user);
        return "redirect:/login"; // 註冊成功後導航到登入頁
    }
}