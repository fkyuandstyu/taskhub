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
    public void saveUser(User user) {
        // 1. 將明文密碼進行雜湊處理 (Hashing)
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // 2. 把雜湊後的密碼設定回 User 物件
        user.setPassword(encodedPassword);

        // 3. 透過 Repository 存入 MySQL
        userRepository.save(user);
    }
}