package com.atguigu.servicebase.exceptionhandler;


import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/6 19:57
 * @Version 1.0
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public R exceptionHandler(Exception e){
        e.printStackTrace();
        return R.error().message("当前服务繁忙, 请稍后再试~~~~");
    }
}