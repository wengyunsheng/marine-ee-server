package com.marine;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.marine.mapper")
public class MarineApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarineApplication.class, args);
    }

}
