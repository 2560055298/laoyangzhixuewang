package com.atguigu.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/27 19:03
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = "com.atguigu")
@EnableDiscoveryClient
@MapperScan("com.atguigu.cmsservice.mapper")
public class CrmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class);
    }
}