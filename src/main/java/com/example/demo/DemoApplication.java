//mainファイル。　一番最初に動く場所
//システムを起動して確認する際は、ここを起動してください。


package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        // ここがアプリ全体の起動スイッチになります
        SpringApplication.run(DemoApplication.class, args);
    }
}