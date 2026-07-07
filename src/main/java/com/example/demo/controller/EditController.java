
package com.example.demo.controller;

import com.example.demo.entity.Medicine;
import com.example.demo.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;;

@Controller
public class EditController {

    @Autowired
    private MedicineRepository medicineRepository;

    // 編集画面を表示
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable("id") Integer id, Model model) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid ID: " + id));

        model.addAttribute("medicine", medicine);
        return "edit"; // edit.html を表示
    }

    // 更新処理（上書き）
    @PostMapping("/edit")
    public String updateMedicine(@ModelAttribute Medicine medicine) {

        // そのまま save すれば UPDATE になる
        medicineRepository.save(medicine);

        return "redirect:/list-page";
    }

    // 削除処理
    @PostMapping("/delete/{id}")
    public String deleteMedicine(@PathVariable("id") Integer id) {
        medicineRepository.deleteById(id);
        return "redirect:/list-page";
    }
}
