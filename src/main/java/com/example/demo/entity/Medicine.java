package com.example.demo.entity;

import java.time.LocalDate;
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
@Table(name = "medicines") // RDSの「medicines」テーブルと紐づきます
@Data                  //getter・setterを書かずに利用できるようにするためのアノテーション

public class Medicine {
    @Id // この引数は主キーであることを定義するアノテーション。IDは主キーとして定義する。
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動連番(AI)idはデータを作る度に連番でIDが割り振られる
    @Column(name = "id") // DBのidと紐づけ
    private Integer medicineId;//薬のID autoIncrement 主キー

    @ManyToOne//一つのUserに対して多数のMedicineが紐づく
    @JoinColumn(name = "user_id")//DBのuser_idと紐づけ
    private User user;//Userオブジェクトの取得
    private String name;//薬名
    private int volume;//服用量
    private String unit;//薬の単位
    private String week;//服用する曜日
    @Column(name = "`time`")//timeが予約語なので念のため紐づけ
    private LocalTime time;//服用する時間
    @Column(name = "start_day")//DBのstart_dayと紐づけ
    private LocalDate startDay;//開始日
    @Column(name = "finish_day")//DBのfinish_dayと紐づけ
    private LocalDate finishDay;//終了日

}
