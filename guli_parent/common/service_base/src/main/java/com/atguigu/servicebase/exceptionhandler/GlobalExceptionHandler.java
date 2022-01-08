package com.atguigu.servicebase.exceptionhandler;


import com.atguigu.commonutils.R;
import org.springframework.beans.factory.annotation.Value;
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
    public R Globalexception(Exception e){
        e.printStackTrace();
        return R.error().message("全局Exception执行了~~~");
    }

    @ExceptionHandler(value = {ArithmeticException.class})
    @ResponseBody
    public R ArithException(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("特定异常：ArithException");
    }

    @ExceptionHandler(value = {GuiguException.class})
    @ResponseBody
    public R defineException(GuiguException e){
        return R.error().code(e.getCode()).message(e.getMsg());
    }
}