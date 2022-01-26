package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/5 20:57
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.atguigu"})
@EnableDiscoveryClient
@EnableFeignClients
public class EduTeacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduTeacherApplication.class);
    }
}