package com.taskhub.server.service; // 宣告這份檔案所在的資料夾位置

import com.taskhub.server.entity.User;
import com.taskhub.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 這是 Spring Security 專用的「查帳號服務」。
 * 它的任務：當有人在網頁輸入帳號，它負責去資料庫把這個人的資料「撈」出來。
 */
@Service // 標記為 Service，讓 Spring 容器自動掃描並管理這個物件
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // 注入資料庫工具，讓我們能對 MySQL 下指令

    /**
     * Spring Security 自動呼叫的方法
     * @param username 使用者在登入頁面輸入的帳號字串
     * @return 回傳一個 Security 看得懂的 UserDetails 物件（包含正確的密碼）
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. 去資料庫找看看有沒有這個帳號名
        // findByUsername 會回傳 Optional，所以我們用 .orElseThrow 處理「找不到」的情況
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("資料庫裡找不到這個人: " + username));

        // 2. 找到人之後，我們要把「我們的 User 實體」轉換成「Security 指定的格式」
        // 這裡回傳的是 org.springframework.security.core.userdetails.User
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), // 從資料庫撈出來的帳號
                user.getPassword(), // 從資料庫撈出來的 BCrypt 加密密碼 (亂碼)
                new ArrayList<>()   // 目前還沒設定權限角色（Role），先給一個空的清單
        );
    }
}