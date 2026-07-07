package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Notifications;
import com.example.demo.repository.NotificationsRepository;

@Service
//通信サービスクラス
public class NotificationService {

    @Autowired
    //DIコンテナの中に格納しているインスタンスの中からNotificationsRepository型のインスタンスを取り出して、notificationsRepositoryに代入する
    private NotificationsRepository notificationsRepository;

    //全件取得
    public List<Notifications> getAllNotifications() {
        return notificationsRepository.findAll();
    }

    //通知を保存するメソッド
    public Notifications saveNotification(Notifications notification) {
        return notificationsRepository.save(notification);
    }

    //通知を更新するメソッド
    public Notifications updateNotification(Notifications notification) {
        return notificationsRepository.save(notification);
    }
    
    //通知を削除するメソッド
    public void deleteNotification(Integer notificationId) {
        notificationsRepository.deleteById(notificationId);
    }

}