package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/5 20:57
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.atguigu"})
public class EduTeacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduTeacherApplication.class);
    }
}