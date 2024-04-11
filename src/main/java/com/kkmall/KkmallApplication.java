package com.kkmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.kkmall.dao")
public class KkmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(KkmallApplication.class, args);
    }

}
