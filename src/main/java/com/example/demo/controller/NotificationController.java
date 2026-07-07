package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.service.NotificationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpSession;

import com.example.demo.entity.Notifications;
import com.example.demo.entity.User;

@Controller
public class NotificationController {

    //DIコンテナの中に格納しているインスタンスの中からNotificationService型のインスタンスを取り出して、notificationServiceに代入する
    @Autowired
    private NotificationService notificationService;

    //通知を保存するメソッド
    @PostMapping("/notifications")
    public String saveNotification(
        //通知の内容を受け取るための引数
        @ModelAttribute Notifications notification,HttpSession session) {

        // ログイン中のユーザーを取得
        User loginUser = (User) session.getAttribute("user");

        // 通知にユーザーを設定
        notification.setUser(loginUser);

        // DBへ保存
        notificationService.saveNotification(notification);

        // 保存後はホーム画面へ戻る
        return "redirect:/home";
    }

}