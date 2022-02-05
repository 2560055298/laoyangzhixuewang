package com.atguigu.ucenterservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/31 8:06
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = "com.atguigu")
@MapperScan("com.atguigu.ucenterservice.mapper")
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class);
    }
}