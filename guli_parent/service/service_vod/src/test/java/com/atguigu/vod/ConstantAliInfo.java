package com.atguigu.vod;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/6 17:00
 * @Version 1.0
 */
public class ConstantAliInfo implements InitializingBean {
    private String accessKeyId="LTAI5tKgHGBRbR9aTdmVJ7BA";
    private String accessKeySecret="Fmms7vJ7K0WAPF3yKzCJpKYKKuj7iP";

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
    }
}