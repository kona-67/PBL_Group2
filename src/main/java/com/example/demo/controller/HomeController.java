package com.example.demo.controller;

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


//ｓ


}