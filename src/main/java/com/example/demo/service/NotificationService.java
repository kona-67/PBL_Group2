package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Notifications;
import com.example.demo.repository.NotificationsRepository;
import com.example.demo.entity.User;

@Service
public class NotificationService {

    private final NotificationsRepository notificationsRepository;

    public NotificationService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }


    // 全件取得
    public List<Notifications> getAllNotifications() {
        return notificationsRepository.findAll();
    }


    // 保存
    public Notifications saveNotification(Notifications notification) {
        return notificationsRepository.save(notification);
    }


    // 更新
    public Notifications updateNotification(Notifications notification) {
        return notificationsRepository.save(notification);
    }


    // 削除
    public void deleteNotification(Integer id) {
        notificationsRepository.deleteById(id);
    }

    // ユーザーに紐づく通知を削除
    @Transactional
    public void deleteAllByUser(User user) {
        notificationsRepository.deleteByUser(user);
    }
}