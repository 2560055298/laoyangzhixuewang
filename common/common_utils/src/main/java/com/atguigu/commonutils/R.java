package com.atguigu.commonutils;

import lombok.Data;

import java.util.HashMap;

/**
 * @Author HeJinYang
 * @Description 返回结果：json格式
 * @Date 2022/1/6 14:04
 * @Version 1.0
 */

@Data
public class R {
    private Boolean sccuess;        //成功状态
    private Integer code;           //状态码
    private String message;         //提示消息
    private HashMap<String, Object> map = new HashMap<>();

    //1、构造方法私有
    private R(){

    }

    //2、提供：成功返回的ok方法
    public static R ok(){
        R r = new R();
        r.setSccuess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    //3、提供：返回失败的error方法
    public static R error(){
        R r = new R();
        r.setSccuess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    //4、提供链式赋值
    public R success(Boolean sccuess){
        this.setSccuess(sccuess);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R message(String message){
        this.setMessage(message);
        return this;
    }

    public R data(String key, Object value){
        map.put(key, value);
        this.setMap(map);
        return this;
    }

    public R data(HashMap<String, Object> map){
        this.setMap(map);
        return this;
    }
}