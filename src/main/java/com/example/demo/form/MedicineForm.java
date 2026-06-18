//フォームに入力する内容を格納するのに必要な変数(薬情報)を定義するクラス

package com.example.demo.form;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class MedicineForm {

    //HTMLからの入力内容がMedicineControllerに送られた際に起動するアノテーション
    @NotNull(message = "薬の名前を入力してください。") //addMedicineメソッドが起動した際に、nameに値が入っているかをチェックするアノテーション。HTMLで入力された内容は一時的にnameに入る。                                
    @Size(min = 1, max = 50, message = "名前は50文字以内で入力してください。")  //nameに入る文字数が1以上50以下であるかをチェックするアノテーション
    private String name;

    @NotNull(message = "服用量を入力してください。")
    private Integer volume;

    @NotNull(message = "単位を入力してください")
    @Size(min = 1,max = 3, message = "3文字以内で入力してください")
    private String unit;
    
    //@NotNull(message = "服用する曜日を指定してください")
    private String[] week;//服用する曜日

    @NotNull(message = "服用する時間を指定してください")
    private LocalTime time;//服用する時間

    @NotNull(message = "開始日")
    private LocalDate startDay;

    @NotNull(message = "終了日")
    private LocalDate finishDay;

    public String getWeek() {
        // week 自体が null なら null を返す
        if (week == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean hasValue = false;
        for (String w : week) {
            if (w != null) {   // null の要素は無視
                sb.append(w);
                hasValue = true;
            }
        }
        if(!hasValue){//weekの中身が{null,null...}すべてnullならnullを返す
            return null;
        }
        // 連結結果を返す（中身が空でもそのまま返す）
        return sb.toString();
}

}

