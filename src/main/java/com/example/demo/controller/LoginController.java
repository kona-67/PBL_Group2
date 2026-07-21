package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ==========================================
    // 1. ログイン画面を表示する
    // ==========================================
    @GetMapping("/user/login")
    public String showLoginPage() {
        return "login"; 
    }

    // ==========================================
    // 2. ログインボタンが押されたときの処理
    // ==========================================
    @PostMapping("/user/login")
    public String loginUser(@ModelAttribute LoginForm form, Model model, HttpSession session) {
        System.out.println("=== ログインチェック開始 ===");
        System.out.println("画面から入力されたメール: " + form.getUsername());
        
        Optional<User> userOpt = userRepository.findByEmail(form.getUsername());

        if (userOpt.isPresent()) {
            User dbUser = userOpt.get();
            System.out.println("DBから見つかったユーザーのメール: " + dbUser.getEmail());
            
            if (passwordEncoder.matches(form.getPassword(), dbUser.getPasswordHash())) {
                System.out.println("【判定】一致しました！ログイン成功！");
                session.setAttribute("user", dbUser);
                return "redirect:/home";
            } else {
                System.out.println("【判定】パスワードが一致しませんでした。");
            }
        } else {
            System.out.println("【判定】このメールアドレスのユーザーはDBにいません。");
        }

        model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
        return "login"; 
    }

    // ==========================================
    // 3. 新規登録画面を表示する
    // ==========================================
    @GetMapping("/user/register")
    public String showRegisterPage() {
        return "register";
    }

    // ==========================================
    // 4. 新規登録ボタンが押されたときの処理（仕様書テスト対応・エラー強化版）
    // ==========================================
    @PostMapping("/user/register")
    public String registerUser(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("passwordConfirm") String passwordConfirm, // 💡 確認用パスワードも受け取ります
            HttpSession session,
            Model model) {

        System.out.println("=== 新規登録処理を開始（テスト強化版） ===");
        System.out.println("登録メールアドレス: " + email);

        // 💡 1. 未入力チェック（テスト仕様書 No.10）
        if (email == null || email.trim().isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("registerError", "入力してください");
            return "register";
        }

        // 💡 2. 8文字未満チェック（テスト仕様書 No.11）
        if (password.length() < 8) {
            model.addAttribute("registerError", "パスワードは8文字以上で入力してください");
            return "register";
        }

        // 💡 3. パスワード一致チェック
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("registerError", "パスワードと確認用パスワードが一致しません。");
            return "register";
        }

        // 💡 4. メールアドレスの重複チェック（テスト仕様書 No.13）
        if (userRepository.findByEmail(email).isPresent()) {
            model.addAttribute("registerError", "このメールアドレスはすでに登録されています。");
            return "register";
        }

        // 新しいユーザーを作成して保存
        User newUser = new User();
        newUser.setEmail(email);
        
        // パスワードをBCryptでハッシュ化して保存！
        String encodedPassword = passwordEncoder.encode(password);
        newUser.setPasswordHash(encodedPassword); 
        newUser.setDisplayName("新規ユーザー"); 

        // AWSに保存
        User savedUser = userRepository.save(newUser);
        System.out.println("AWSへの新規登録成功！自動ログインします。");

        // 自動ログイン状態にしてホーム画面へ遷移
        session.setAttribute("user", savedUser);
        
        return "redirect:/home";
    }

    // ==========================================
    // データ受け取り用の箱 (Form)
    // ==========================================
    public static class LoginForm {
        private String username; 
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

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