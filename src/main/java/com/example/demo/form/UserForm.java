//フォームに入力する内容を格納するのに必要な変数(ユーザ情報)を定義するクラス

package com.example.demo.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

    //HTMLからの入力内容がUserControllerに送られた際に起動するアノテーション
    @NotNull(message = "名前を入力してください。")                              //testUserメソッドが起動した際に、nameに値が入っているかをチェックするアノテーション。HTMLで入力された内容は一時的にnameに入る。                                
    @Size(min = 1, max = 50, message = "名前は50文字以内で入力してください。")  //nameに入る文字数が1以上50以下であるかをチェックするアノテーション
    private String name;

    @NotNull(message = "年齢を入力してください。")                              //testUserメソッドが起動した際に、ageに値が入っているかをチェックするアノテーション。HTMLで入力された内容は一時的にageに入る。
    private Integer age;
}