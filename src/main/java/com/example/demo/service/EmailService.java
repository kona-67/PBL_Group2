package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    //DIコンテナの中に格納しているインスタンスの中からJavaMailSender型のインスタンスを取り出して、mailSenderに代入する
    private JavaMailSender mailSender;

    //服用通知メールを送信するメソッド
    public void sendMail(String to, String medicineName) {
        try {
            //メール送信の処理
            SimpleMailMessage message = new SimpleMailMessage();

            //送新先、件名、本文を追加
            message.setTo(to);
            message.setSubject("服薬通知");
            message.setText(medicineName + " を服用する時間です。");

            //メールを送信
            mailSender.send(message);

        } catch (Exception e) {
            //メール送信に失敗した時の処理
            System.out.println("服薬通知メール送信失敗: " + e.getMessage());
        }
    }
}