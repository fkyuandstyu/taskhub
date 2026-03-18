package com.taskhub.server.repository;

import com.taskhub.server.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {
    // 繼承後，你就擁有 save(), findAll(), deleteById() 等功能了！
}