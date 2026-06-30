package main.java.com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;

@Controller
public class HomeController {

    @Autowired
    private MedicineRepository medicineRepository;

    // 1. ホーム画面を表示する
    @GetMapping("/home")
    public String showHomePage(Model model) {

        // ★RDSから全データを取ってくる（セレクトSQLが自動で飛ぶ）
        List<Medicine> medicineList = medicineRepository.findAll();

        // ★HTMLにデータを渡す
        model.addAttribute("medicineList", medicineList);

        return "home"; // templates/home.html を開く
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