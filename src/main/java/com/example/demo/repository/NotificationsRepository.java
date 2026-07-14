package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Notifications;
import com.example.demo.entity.User;

@Repository
public interface NotificationsRepository extends JpaRepository<Notifications, Integer> {

    // enabled = true の通知だけ取得
    List<Notifications> findByEnabledTrue();

    @Transactional
    @Modifying
    void deleteByUser(User user);

}