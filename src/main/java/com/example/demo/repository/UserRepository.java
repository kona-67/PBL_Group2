package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> { // 💡ここが「Long」ならLongにしてください
    
    // 💡 この下の1行を追加します！
    Optional<User> findByEmail(String email);
    
}