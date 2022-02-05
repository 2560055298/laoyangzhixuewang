package com.atguigu.ucenterservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/2 20:41
 * @Version 1.0
 */

@Component
public class ConstantPropertiesUtils implements InitializingBean {
    @Value("${wx.app_id}")
    private String appId;

    @Value("${wx.app_secret}")
    private String appSecret;

    @Value("${wx.redirect_uri}")
    private String baseUrl;

    public static String APP_ID;
    public static String APP_SECRET;
    public static String BASE_URL;


    @Override
    public void afterPropertiesSet() throws Exception {
        APP_ID = appId;
        APP_SECRET = appSecret;
        BASE_URL = baseUrl;
    }
}