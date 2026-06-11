package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//DBのテーブルを作成する際はClassで生成する。
@Entity                //このクラスがDBのテーブル定義に使うクラスであるということを示している。
@Table(name = "medicines") // RDSの「medicines」テーブルと紐づきます
@Data                  //getter・setterを書かずに利用できるようにするためのアノテーション

public class Medicine {
    @Id // この引数は主キーであることを定義するアノテーション。IDは主キーとして定義する。
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動連番(AI)idはデータを作る度に連番でIDが割り振られる
    private Integer medicineId;//薬のID autoIncrement 主キー

    private Integer userId;//ユーザのID 外部キー
    private String name;//薬名
    private int volume;//服用量
    private String unit;//薬の単位
    private String week;
    private LocalTime time;
    private LocalDate startDay;
    private LocalDate finishDay;

}
