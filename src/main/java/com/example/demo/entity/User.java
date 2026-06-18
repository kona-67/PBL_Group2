//登録するユーザ情報を定義しているファイル。DBのテーブル作成に使うJavaファイル。

package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

//DBのテーブルを作成する際はClassで生成する。
@Entity                //このクラスがDBのテーブル定義に使うクラスであるということを示している。
@Table(name = "users") // RDSの「users」テーブルと紐づきます
@Data                  //getter・setterを書かずに利用できるようにするためのアノテーション
public class User {
    @Id                                                 // この引数は主キーであることを定義するアノテーション。IDは主キーとして定義する。
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自動連番(AI)idはデータを作る度に連番でIDが割り振られる
    private Integer id;

    private String email;
    private String password;
    private String name;
    private Integer age; // 💡追加：UserControllerが求めている「年齢」の変数
}