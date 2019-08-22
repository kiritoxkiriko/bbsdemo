package com.wxm.bbsdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.wxm.bbsdemo.mapper")
@ComponentScan("com.wxm.bbsdemo.service.impl")
@ComponentScan("com.wxm.bbsdemo")
public class BbsdemoApplication  {
    /**
     * 需要把web项目打成war包部署到外部tomcat运行时需要改变启动方式
     */
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(BbsdemoApplication.class);
//    }


    public static void main(String[] args) {
        SpringApplication.run(BbsdemoApplication.class, args);
    }

}

