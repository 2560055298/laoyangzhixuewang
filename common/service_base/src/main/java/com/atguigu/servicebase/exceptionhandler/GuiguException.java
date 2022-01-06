package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/6 20:38
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuiguException extends RuntimeException {
    private Integer code;       //状态码
    private String msg;         //异常信息
}