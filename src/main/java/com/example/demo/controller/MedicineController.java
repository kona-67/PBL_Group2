//Get・Post処理を定義しているファイル。

package com.example.demo.controller;

import com.example.demo.entity.Medicine;
import com.example.demo.form.MedicineForm;
import com.example.demo.repository.MedicineRepository;

//import com.example.demo.entity.Notifications;
//import com.example.demo.repository.NotificationsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.User;
import jakarta.servlet.http.HttpSession;
import java.util.List;


@Controller
public class MedicineController {

    @Autowired  //DIコンテナの中に格納しているインスタンスの中からMedicineRepository型のインスタンスを取り出して、userRepositoryに代入する
    private MedicineRepository medicineRepository; // DB操作ツールを呼び出す

    /* 
    @Autowired  //DIコンテナの中に格納しているインスタンスの中からNotificationsRepository型のインスタンスを取り出して、userRepositoryに代入する
    private NotificationsRepository notificationsRepository; // DB操作ツールを呼び出す
    */

    @Autowired
    private HttpSession session;

    // 1. 登録画面を表示する（リクエストハンドラ）
    @GetMapping("/add-page")
    public String showaddPage(@ModelAttribute MedicineForm MedicineForm) {
        return "add"; // templates/add.html を開く
    }

    // 2. 登録ボタンが押された時の処理
    //@Validated：MedicineFormクラス内に記載しているアノテーション（＠）を起動するためのアノテーション
    //@ModelAttribute：HTMLの入力内容をMedicineFormクラスの変数に入れるためのアノテーション
    //BindingResult：@Validatedで起動したアノテーションのエラー情報を格納するための入れ物
    //Model：JavaからHTMLにデータを送る際に使用する倉庫
    @PostMapping("/add")
    public String addMedicine(@Validated @ModelAttribute MedicineForm medicineForm, BindingResult result, Model model) {
        
        // もし入力エラーがあれば登録画面に戻す処理
        if (result.hasErrors()) {
            return "add"; //HTMLを指定してreturnでそのHTMLページに遷移させる
        }

        //sessionからログインユーザーを取得
        User loginUser = (User) session.getAttribute("loginUser");

        // 画面の入力データを、DB保存用のEntityにコピーする
        Medicine medicine = new Medicine();             //Medicineインスタンスを作成
        medicine.setMedicine_name(medicineForm.getName());   //setter
        medicine.setVolume(medicineForm.getVolume());
        medicine.setUnit(medicineForm.getUnit());
        if(medicineForm.getWeek() == null){//入力値なしの場合曜日指定なしとみなす
            medicine.setWeek("曜日指定なし");
        }else{
            medicine.setWeek(medicineForm.getWeek());
        }
        medicine.setTime(medicineForm.getTime());
        medicine.setStartDay(medicineForm.getStartDay());
        medicine.setFinishDay(medicineForm.getFinishDay());
        medicine.setUserId(loginUser.getUserId());

        // ★ここで実際にRDSへデータを保存（インサートSQLが自動で飛ぶ）
        medicineRepository.save(medicine);

        /* 以下、通知自動登録デモ版

        //入力値から情報を抜き出して通知を自動登録
        Notifications noti = new Notifications();//インスタンス作成
        noti.setUser(loginUser);
        noti.setName(medicineForm.getName());
        noti.setTime(medicineForm.getTime());
        if(medicineForm.getWeek() == null){//入力値なしの場合曜日指定なしとみなす
            noti.setWeekday("曜日指定なし");
        }else{
            noti.setWeekday(medicineForm.getWeek());
        }
        noti.setEnabled(true);

        // ★ここで実際にRDSへデータを保存（インサートSQLが自動で飛ぶ）
        notificationsRepository.save(noti);

         */


        // 登録が終わったら、ホーム画面にリダイレクト（移動）する
        return "redirect:/home";
    }

    //薬の登録情報一覧を表示
    @GetMapping("/list")
        public String showListPage(Model model) {

        // ★ログインユーザーをセッションから取得
        User loginUser = (User) session.getAttribute("loginUser");

        // ★ログインユーザーの user_id に一致するデータだけ取得
        List<Medicine> medicineList = medicineRepository.findByUserId(loginUser.getUserId());

        // ★画面へ渡す
        model.addAttribute("medicineList", medicineList);

        return "list"; // list.html を表示
    }

}