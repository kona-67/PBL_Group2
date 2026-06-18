//Get・Post処理を定義しているファイル。

package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired  //DIコンテナの中に格納しているインスタンスの中からUserRepository型のインスタンスを取り出して、userRepositoryに代入する
    private UserRepository userRepository; // DB操作ツールを呼び出す

    // 1. 登録画面を表示する（リクエストハンドラ）
    @GetMapping("/test-page")
    public String showtestPage(@ModelAttribute UserForm userForm) {
        return "test"; // templates/test.html を開く
    }

    // 2. 登録ボタンが押された時の処理
    //@Validated：UserFormクラス内に記載しているアノテーション（＠）を起動するためのアノテーション
    //@ModelAttribute：HTMLの入力内容をUserFormクラスの変数に入れるためのアノテーション
    //BindingResult：@Validatedで起動したアノテーションのエラー情報を格納するための入れ物
    //Model：JavaからHTMLにデータを送る際に使用する倉庫
    @PostMapping("/test")
    public String addUser(@Validated @ModelAttribute UserForm userForm, BindingResult result, Model model) {
        
        // もし入力エラーがあれば登録画面に戻す処理
        if (result.hasErrors()) {
            return "test"; //HTMLを指定してreturnでそのHTMLページに遷移させる
        }


        // 画面の入力データを、DB保存用のEntityにコピーする
        User user = new User();             //Userインスタンスを作成
        user.setName(userForm.getName());   //setter
        user.setAge(userForm.getAge());

        // ★ここで実際にRDSへデータを保存（インサートSQLが自動で飛ぶ）
        userRepository.save(user);
        
        // 登録が終わったら、ホーム画面にリダイレクト（移動）する
        return "redirect:/home-page";
    }

    /* ★後で"ホーム表示"に変更★ 
    // 3. 一覧画面を表示する
    @GetMapping("/home-page")                                   //このリンクにアクセスしたらlist.htmlを開くという意味
    public String showListPage(Model model) {
        // ★RDSから全データを取ってくる（セレクトSQLが自動で飛ぶ）
        List<User> userList = userRepository.findAll();

        // 画面（HTML）にデータを渡す
        model.addAttribute("userList", userList);
        return "home"; // templates/home.html を開く
    }
    */
}