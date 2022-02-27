package com.yyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/12 22:12
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = "com.yyy")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
@MapperScan("com.yyy.statservice.mapper")
public class StatisticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatisticsApplication.class);
    }
}