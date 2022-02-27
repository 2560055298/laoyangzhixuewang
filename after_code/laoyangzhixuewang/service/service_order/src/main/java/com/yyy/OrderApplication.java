package com.yyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/11 8:07
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = "com.yyy")
@MapperScan("com.yyy.eduorder.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class);
    }
}