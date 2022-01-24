package com.atguigu.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/24 14:59
 * @Version 1.0
 */

@SpringBootApplication(scanBasePackages = {"com.atguigu"}, exclude = {DataSourceAutoConfiguration.class})
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class);
    }
}