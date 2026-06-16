package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    // 1. ログイン画面を表示する (GET)
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // ★【新しく追加】ログインボタンが押されたときの処理 (POST)
    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginForm form, Model model) {
        
        // 入力値の空チェック
        if (form.getUsername().isEmpty() || form.getPassword().isEmpty()) {
            model.addAttribute("loginError", "メールアドレスとパスワードを入力してください。");
            return "login";
        }

        // 💡（簡易判定）メールアドレスとパスワードが入力されていれば、一旦全員ログイン成功にする処理
        System.out.println("ログイン試行: " + form.getUsername());
        
        // ─── ログイン成功！ ───
        // ホーム画面（/home）へ自動でジャンプ（リダイレクト）させる
        return "redirect:/home";
    }

    // 3. 新規登録画面を表示する (GET)
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // 4. 新規登録ボタンが押されたときの処理 (POST)
    @PostMapping("/register")
    public String registerUser(@ModelAttribute RegisterForm form, Model model) {
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            model.addAttribute("errorMessage", "パスワードと確認用パスワードが一致しません。");
            return "register"; 
        }
        if (form.getEmail().isEmpty() || form.getPassword().isEmpty()) {
            model.addAttribute("errorMessage", "メールアドレスとパスワードを入力してください。");
            return "register";
        }

        System.out.println("登録成功！ メールアドレス: " + form.getEmail());
        
        // 新規登録が成功したときもホーム画面（/home）へ直接ジャンプさせる
        return "redirect:/home";
    }

    // 5. 移動先となるホーム画面（home.html）を表示する窓口
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    // ==========================================================
    // 💡 ログインデータを受け取る用の箱（インナークラス）
    // ==========================================
    public static class LoginForm {
        // ※以前のlogin.htmlのinputタグのnameが "username" になっていたので合わせます
        private String username; 
        private String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // ==========================================================
    // 💡 新規登録データを受け取る用の箱（インナークラス）
    // ==========================================================
    public static class RegisterForm {
        private String email;
        private String password;
        private String passwordConfirm;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }

        public String getPasswordConfirm() { return passwordConfirm; }
        public void setPasswordConfirm(String passwordConfirm) { this.passwordConfirm = passwordConfirm; }
    }
}