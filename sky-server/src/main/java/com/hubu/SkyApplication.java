package com.hubu;/*
 * @Auther:long
 * @Date:2025/7/1
 * @Description:
 * @VERSON:1.8
 */

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Slf4j
@MapperScan("com.hubu.mapper")
public class SkyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkyApplication.class,args);
        log.info("server start");
    }
}
