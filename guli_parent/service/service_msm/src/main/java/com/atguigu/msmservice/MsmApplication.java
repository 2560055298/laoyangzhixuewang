package com.atguigu.msmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/29 16:03
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = "com.atguigu", exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class);
    }
}