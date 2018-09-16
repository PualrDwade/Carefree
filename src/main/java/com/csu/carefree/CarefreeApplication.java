package com.csu.carefree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.csu.carefree.Persistence")
// springboot启动项目
public class CarefreeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CarefreeApplication.class, args);
    }
}