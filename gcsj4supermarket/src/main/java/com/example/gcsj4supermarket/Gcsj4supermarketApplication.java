package com.example.gcsj4supermarket;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@MapperScan("com.example.gcsj4supermarket.*.mapper")
public class Gcsj4supermarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(Gcsj4supermarketApplication.class, args);
    }

}
