package com.yyy.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/24 14:59
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = {"com.yyy"}, exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class);
    }
}