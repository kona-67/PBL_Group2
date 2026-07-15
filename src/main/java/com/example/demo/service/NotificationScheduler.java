package com.example.demo.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Notifications;
import com.example.demo.repository.NotificationsRepository;

//通知スケジューラークラス
@Component
public class NotificationScheduler {

    //DIコンテナの中に格納しているインスタンスの中からNotificationsRepository型のインスタンスを取り出して、notificationsRepositoryに代入する
    @Autowired
    private NotificationsRepository notificationsRepository;

    //DIコンテナの中に格納しているインスタンスの中からEmailService型のインスタンスを取り出して、emailServiceに代入する
    @Autowired
    private EmailService emailService;
    
    // 1分ごとに通知をチェック
    @Scheduled(fixedRate = 60000)
    public void checkNotifications() {

        System.out.println("通知チェック実行：" + LocalTime.now());

        // 通知ONのデータを取得
        List<Notifications> notifications =
                notificationsRepository.findByEnabledTrue();

        // 現在時刻（秒・ナノ秒は切り捨て）
        LocalTime now = LocalTime.now().withSecond(0).withNano(0);

        // 今日の曜日
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        String today = getJapaneseWeekday(dayOfWeek);

        // 通知データを1件ずつチェック
        for (Notifications notification : notifications) {

            System.out.println("------------");
            System.out.println("薬：" + notification.getName());
            System.out.println("DB時間：" + notification.getTime());
            System.out.println("現在時間：" + now);
            System.out.println("DB曜日：" + notification.getWeekday());
            System.out.println("今日：" + today);
            
            String weekday = notification.getWeekday();

            // 今日が通知対象か判定
            boolean todayMatch =
                    weekday.equals("毎日")
                    || weekday.contains(today);

            System.out.println("曜日一致：" + todayMatch);
            System.out.println("時間一致：" + notification.getTime().equals(now));
            // 曜日・時間が一致したらメール送信
            if (todayMatch && notification.getTime().equals(now)) {

                System.out.println("メール送信開始");

                emailService.sendMail(
                        notification.getUser().getEmail(),
                        notification.getName());

                System.out.println(
                        "通知送信：" + notification.getName());
            }
        }
    }

    // 英語の曜日を日本語へ変換
    private String getJapaneseWeekday(DayOfWeek day) {

        switch (day) {
            case MONDAY:
                return "月";
            case TUESDAY:
                return "火";
            case WEDNESDAY:
                return "水";
            case THURSDAY:
                return "木";
            case FRIDAY:
                return "金";
            case SATURDAY:
                return "土";
            case SUNDAY:
                return "日";
            default:
                return "";
        }
    }
}