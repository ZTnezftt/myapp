package com.example.demo;

import com.example.demo.Main.MainTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(scanBasePackages = "com.example.demo")
@MapperScan("com.example.demo.DAO")
@EnableCaching
@EnableAsync
public class  DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainTest.class, args);
    }
}
