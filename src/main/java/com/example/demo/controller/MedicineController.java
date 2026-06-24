//Get・Post処理を定義しているファイル。

package com.example.demo.controller;

import com.example.demo.entity.Medicine;
import com.example.demo.form.MedicineForm;
import com.example.demo.repository.MedicineRepository;
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
public class MedicineController {

    @Autowired  //DIコンテナの中に格納しているインスタンスの中からUserRepository型のインスタンスを取り出して、userRepositoryに代入する
    private MedicineRepository medicineRepository; // DB操作ツールを呼び出す

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


        // 画面の入力データを、DB保存用のEntityにコピーする
        Medicine medicine = new Medicine();             //Medicineインスタンスを作成
        medicine.setName(medicineForm.getName());   //setter
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

        // ★ここで実際にRDSへデータを保存（インサートSQLが自動で飛ぶ）
        medicineRepository.save(medicine);

        // 登録が終わったら、一覧表示画面にリダイレクト（移動）する
        return "redirect:/list-page";
    }

    // 3. 一覧画面を表示する
    @GetMapping("/list-page") //このリンクにアクセスしたらlist.htmlを開くという意味
    public String showListPage(Model model) {
        // ★RDSから全データを取ってくる（セレクトSQLが自動で飛ぶ）
        List<Medicine> medicineList = medicineRepository.findAll();

        // 画面（HTML）にデータを渡す
        model.addAttribute("medicineList", medicineList);
        return "list"; // templates/list.html を開く
    }
}