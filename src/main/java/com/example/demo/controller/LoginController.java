package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Optional;

@Controller
public class LoginController {

    // 💡 さっき作った UserRepository を自動で読み込んで使えるようにします
    @Autowired
    private UserRepository userRepository;

    
    // 1. ログイン画面を表示する
    @GetMapping("/user/login")
    public String showLoginPage() {
        return "login"; 
    }

    // 2. ログインボタンが押されたときの処理 💡ここを本物の照合ロジックに書き換えました！
    @PostMapping("/user/login")
    public String loginUser(@ModelAttribute LoginForm form, Model model) {
        System.out.println("ログイン試行中: " + form.getUsername());
        
        // ① 入力されたメールアドレス（username）でMySQLを検索する
        Optional<User> userOpt = userRepository.findByEmail(form.getUsername());

        // ② ユーザーがDBに存在し、かつパスワードも一致するかチェック
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(form.getPassword())) {
            System.out.println("ログイン成功！ホーム画面へリダイレクトします。");
            // ⭕ 一致したら、他の人が作ったホーム画面（/home）へジャンプ
            return "redirect:/home";
        } else {
            System.out.println("ログイン失敗：メールアドレスかパスワードが違います。");
            // ❌ 間違っていたら、エラーメッセージを画面に渡してログイン画面に戻す
            model.addAttribute("loginError", "メールアドレスまたはパスワードが違います。");
            return "login"; 
        }

        
    }

    // 3. 新規登録画面を表示する
    @GetMapping("/user/register")
    public String showRegisterPage() {
        return "register";
    }

    // 4. 新規登録ボタンが押されたときの処理 💡ここを本物の登録ロジックに書き換えます！
    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute RegisterForm form, Model model) {
        System.out.println("新規登録処理を開始: " + form.getEmail());

        // ① パスワードと確認用パスワードが一致しているかチェック
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            System.out.println("新規登録失敗：パスワードが一致しません。");
            // ❌ 違っていれば、エラーメッセージを画面に渡して登録画面に戻す
            model.addAttribute("registerError", "パスワードが一致しません。");
            return "register";
        }

        // ② 💡【応用】すでに同じメールアドレスが登録されていないかチェック
        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            System.out.println("新規登録失敗：既に登録されているメールアドレスです。");
            model.addAttribute("registerError", "このメールアドレスは既に登録されています。");
            return "register";
        }

        // ③ 新しいユーザーのデータを作ってMySQLに保存する
        User newUser = new User();
        newUser.setEmail(form.getEmail());
        newUser.setPassword(form.getPassword());
        newUser.setName("新規ユーザー"); // ※HTMLに名前入力欄があれば form.getName() 等に変えられます
        newUser.setAge(20);             // ※HTMLに年齢入力欄があれば form.getAge() 等に変えられます

        userRepository.save(newUser); // 🎯 MySQLへ保存！
        System.out.println("新規登録成功！ログイン画面へ移動します。");

        // ⭕ 登録できたら、次はログインしてもらうためにログイン画面へジャンプ
        return "redirect:/user/login";
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


