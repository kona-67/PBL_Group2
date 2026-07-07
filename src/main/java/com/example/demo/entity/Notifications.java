package com.example.demo.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

//DBのテーブルを作成する際はClassで生成する。
@Entity                //このクラスがDBのテーブル定義に使うクラスであるということを示している。
@Table(name = "notifications") // RDSの「notifications」テーブルと紐づきます
@Data                  //getter・setterを書かずに利用できるようにするためのアノテーション

public class Notifications {
    @Id //この引数は主キーであることを定義するアノテーション。IDは主キーとして定義する。
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動連番(AI)idはデータを作る度に連番でIDが割り振られる
    @Column(name = "id") //DBのidと紐づけ
    private Integer notificationId; //通知のID autoIncrement 主キー

    @ManyToOne//一つのUserに対して多数のMedicineが紐づく
    @JoinColumn(name = "user_id")//DBのuser_idと紐づけ
    private User user;//Userオブジェクトの取得

    @Column(name = "name")//DBのnameと紐づけ
    private String name;//薬名

    @Column(name = "time")//DBのtimeと紐づけ
    private LocalTime time;//服用する時間

    @Column(name = "weekday")//DBのweekdayと紐づけ
    private String weekday;//服用する曜日

    @Column(name = "enabled")//DBのenabledと紐づけ
    private Boolean enabled;//通知の有効化
}