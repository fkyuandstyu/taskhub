package com.taskhub.server.repository;

import com.taskhub.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 繼承後，你就擁有 save(), findAll(), deleteById() 等功能了！
    // 新增這行：Spring 會自動解析 "findBy" + "Username"
    // 它會自動生成 SQL: SELECT * FROM users WHERE username = ?
    Optional<User> findByUsername(String username);
}