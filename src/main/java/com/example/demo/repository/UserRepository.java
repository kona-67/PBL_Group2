package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// これを書くだけで、データの保存(save)や全件取得(findAll)が使えるようになります
@Repository //このクラスがDB操作用のRepositoryであることを示すアノテーション
public interface UserRepository extends JpaRepository<User, Integer> {
}