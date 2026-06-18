package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 1. ホーム画面を表示する
    @GetMapping("/home")
    public String showHomePage() {
    return "home";
    }

    // 1. ログイン画面を表示する
    @GetMapping("/login") 
    public String showLoginPage() {
        return "login"; // templates/login.html を開く
    }

    // 1. 薬の登録情報一覧画面を表示する
    @GetMapping("/list") 
    public String showListPage() {
        return "list"; // templates/list.html を開く
    }
    
    // 1. 薬の登録画面を表示する
    @GetMapping("/add") 
    public String showAddPage() {
        return "add"; // templates/add.html を開く
    }

    // 1. 服用履歴画面を表示する
    @GetMapping("/history") 
    public String showHistoryPage() {
        return "history"; // templates/history.html を開く
    }    

}