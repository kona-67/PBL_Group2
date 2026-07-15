package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;
import com.example.demo.service.NotificationService;
import com.example.demo.entity.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private NotificationService notificationService;

    // 1. ホーム画面を表示する
    @GetMapping("/home")
    public String showHomePage(Model model, HttpSession session) {

        List<Medicine> medicineList = medicineRepository.findAll();

        model.addAttribute("medicineList", medicineList);

        User loginUser = 
            (User) session.getAttribute("loginUser");
        // 通知一覧をHTMLへ渡す
        model.addAttribute(
            "notificationList",
            notificationService.getNotificationsByUser(loginUser)
        );

        return "home";
    }

    // 1. 薬の登録情報一覧画面を表示する
    @GetMapping("/list") 
    public String showListPage(Model model) {
        // ★RDSから全データを取ってくる（セレクトSQLが自動で飛ぶ）
        List<Medicine> medicineList = medicineRepository.findAll();

        // ★HTMLにデータを渡す
        model.addAttribute("medicineList", medicineList);
        return "list"; // templates/list.html を開く
    }

}