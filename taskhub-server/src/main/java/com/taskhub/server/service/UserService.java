package com.taskhub.server.service;

import com.taskhub.server.entity.User;
import com.taskhub.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 這是在 SecurityConfig 定義的加密工具
    // 取得所有任務
    public User registerNewUser(User user) {
        // 1. 將明文密碼進行 BCrypt 雜湊加密
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // 2. 把加密後的密碼塞回 User 物件
        user.setPassword(encodedPassword);

        // 3. 存入資料庫
        return userRepository.save(user);
    }
}