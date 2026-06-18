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

    // 2. ログインボタンが押されたときの処理
    @PostMapping("/user/login")
    public String loginUser(@ModelAttribute LoginForm form, Model model) {
        System.out.println("=== ログインチェック開始 ===");
        System.out.println("画面から入力されたメール: " + form.getUsername());
        System.out.println("画面から入力されたパスワード: " + form.getPassword());
        
        Optional<User> userOpt = userRepository.findByEmail(form.getUsername());

        if (userOpt.isPresent()) {
            User dbUser = userOpt.get();
            System.out.println("DBから見つかったユーザーのメール: " + dbUser.getEmail());
            System.out.println("DBに登録されているパスワード: " + dbUser.getPasswordHash()); // 💡 ここにid変更が反映されてるかチェック
            
            // 実際に比較してみる
            if (dbUser.getPasswordHash().equals(form.getPassword())) {
                System.out.println("【判定】一致しました！ログイン成功！");
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

 

        

    // 3. 新規登録画面を表示する
    @GetMapping("/user/register")
    public String showRegisterPage() {
        return "register";
    }

       // 4. 新規登録ボタンが押されたときの処理
    @PostMapping("/user/register")
    public String registerUser(@ModelAttribute RegisterForm form, Model model) {
        System.out.println("新規登録処理を開始: " + form.getEmail());

        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            model.addAttribute("registerError", "パスワードが一致しません。");
            return "register";
        }

        if (userRepository.findByEmail(form.getEmail()).isPresent()) {
            model.addAttribute("registerError", "このメールアドレスは既に登録されています。");
            return "register";
        }

        // 💡 新しい設計に合わせてデータをセットします
        User newUser = new User();
        newUser.setEmail(form.getEmail());
        newUser.setPasswordHash(form.getPassword()); // 💡 passwordHashに保存
        newUser.setDisplayName("新規ユーザー");        // 💡 displayNameに保存
        // ※ createdAt は自動で入るので書かなくてOK！

        userRepository.save(newUser);
        System.out.println("新規登録成功！ログイン画面へ移動します。");

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


