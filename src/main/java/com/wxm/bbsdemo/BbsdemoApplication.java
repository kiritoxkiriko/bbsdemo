package com.wxm.bbsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.wxm.bbsdemo.mapper")
@ComponentScan("com.wxm.bbsdemo.service.impl")
@ComponentScan("com.wxm.bbsdemo")
public class BbsdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbsdemoApplication.class, args);
    }

}

