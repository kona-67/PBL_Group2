package com.example.demo.repository;

import com.example.demo.entity.User;

//この親クラスを継承するだけで親クラス内に記載しているDB操作・SQL記述を継承してくれるから、私達はDB操作のためのコードを一から書かなくていいよ
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


// これを書くだけで、データの保存(save)や全件取得(findAll)などのテーブルやデータの操作などがおこなえるようになる
//この引数に記載しているファイル（テーブル）名と主キーの型をもとに、DB操作のためのSQL文を自動で生成してくれる。
@Repository //このクラスがDB操作用のJavaファイルであることを示すアノテーション。
public interface UserRepository extends JpaRepository<User, Integer> {
    // メールアドレスでユーザーを検索するためのメソッドを追加
    java.util.Optional<User> findByEmail(String email);
}