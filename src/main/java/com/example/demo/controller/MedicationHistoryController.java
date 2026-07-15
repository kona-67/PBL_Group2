package com.example.demo.controller;

import com.example.demo.entity.MedicationHistory;
import com.example.demo.repository.MedicationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MedicationHistoryController {

    @Autowired
    private MedicationHistoryRepository historyRepository;

    @GetMapping("/history-list")
    public String showHistoryPage(
            @RequestParam(name = "medicineName", required = false) String medicineName,
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "sort", defaultValue = "desc") String sort,
            Model model) {
        
        // 1. ソート順の決定
        Sort sortOrder = sort.equals("asc") 
            ? Sort.by(Sort.Direction.ASC, "takenAt") 
            : Sort.by(Sort.Direction.DESC, "takenAt");

        // 2. 全件取得（本来はSpecification等でDB層でフィルタリングするのが理想ですが、学習用にStreamでフィルタリングします）
        List<MedicationHistory> historyList = historyRepository.findAll(sortOrder);

        // 3. 検索条件によるフィルタリング（入力がある場合のみ実行）
        if (medicineName != null && !medicineName.isEmpty()) {
            historyList = historyList.stream()
                .filter(h -> h.getMedicineName().contains(medicineName))
                .collect(Collectors.toList());
        }

        if (date != null && !date.isEmpty()) {
            LocalDate searchDate = LocalDate.parse(date);
            historyList = historyList.stream()
                .filter(h -> h.getTakenAt().toLocalDate().equals(searchDate))
                .collect(Collectors.toList());
        }

        if (status != null && !status.isEmpty()) {
            historyList = historyList.stream()
                .filter(h -> h.getStatus().equals(status))
                .collect(Collectors.toList());
        }

        // 4. モデルに値をセット（検索条件を保持するためにも渡す）
        model.addAttribute("historyList", historyList);
        model.addAttribute("medicineName", medicineName);
        model.addAttribute("date", date);
        model.addAttribute("status", status);
        model.addAttribute("currentSort", sort);

        return "history";
    }
}