//登録するユーザ情報を定義しているファイル。DBのテーブル作成に使うJavaファイル。

package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Column;
import java.time.LocalDateTime;
import jakarta.persistence.PrePersist;


//DBのテーブルを作成する際はClassで生成する。
@Entity                //このクラスがDBのテーブル定義に使うクラスであるということを示している。
@Table(name = "users") // RDSの「users」テーブルと紐づきます
@Data                  //getter・setterを書かずに利用できるようにするためのアノテーション
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id; 

    private String name;
    private Integer age;


    private String email;

    @Column(name = "password_hash") // 💡 データベース上の列名を「password_hash」にします
    private String passwordHash;

    @Column(name = "display_name") // 💡 データベース上の列名を「display_name」にします
    private String displayName;

    @Column(name = "created_at", updatable = false) // 💡 データベース上の列名を「created_at」にします
    private LocalDateTime createdAt;

    // 💡 新規登録（保存）したときに、自動で現在日時をcreated_atにセットする魔法の処理
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ==========================================
    // ゲッターとセッター（名前が変わったので作り直しました）
    // ==========================================
    public Integer getUserId() { return id; }
    public void setUserId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}