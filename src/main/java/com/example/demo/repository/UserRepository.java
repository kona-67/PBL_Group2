package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    // 💡入力されたメールアドレスをもとに、MySQLからユーザーを1件取ってくる魔法のメソッドです
    Optional<User> findByEmail(String email);
}