package com.taskhub.server.controller;

import com.taskhub.server.entity.User;
import com.taskhub.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // 顯示註冊頁面
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register"; // 對應到 templates/register.html
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // 只要回傳頁面就好，登入的 POST 動作由 Security 接手
    }

    // 處理註冊表單提交
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // 呼叫 Service 進行加密並儲存
        userService.saveUser(user);

        // 註冊成功後，導向登入頁面 (我們之後再寫 login.html)
        return "redirect:/login?success";
    }
}