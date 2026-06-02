//登録するユーザ情報を定義しているファイル。DBにデータを保存する際に必要なクラス。

package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity                //このクラスがDB保存用のEntityであることを示すアノテーション
@Table(name = "users") // RDSの「users」テーブルと紐づきます
@Data                  //getter・setterを書かずに利用できるようにするためのアノテーション
public class User {
    @Id                // 主キーであることを示すアノテーション
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動連番(AI)
    private Integer id;
    private String name;
    private Integer age;
}