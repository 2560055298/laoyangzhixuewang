package com.atguigu.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/12 9:50
 * @Version 1.0
 */


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class, scanBasePackages = {"com.atguigu"})
@EnableDiscoveryClient
public class OssApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssApplication.class);
    }
}