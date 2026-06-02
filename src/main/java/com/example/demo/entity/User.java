//登録するユーザ情報を定義しているファイル。DBのテーブル作成に使うJavaファイル。

package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity                //このクラスがDBのテーブル定義に使うクラスであるということを示している。
@Table(name = "users") // RDSの「users」テーブルと紐づきます
@Data                  //getter・setterを書かずに利用できるようにするためのアノテーション
public class User {
    @Id                                                 // 主キーであることを定義するアノテーション
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動連番(AI)
    private Integer id;
    private String name;
    private Integer age;
}