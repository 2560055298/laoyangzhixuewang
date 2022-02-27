package com.yyy.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/24 15:22
 * @Version 1.0
 */

@Component
public class ConstantPropertiesVodUtils implements InitializingBean {
    @Value("${aliyun.vod.file.keyid}")
    private String keyId;       //密钥：ID

    @Value("${aliyun.vod.file.keysecret}")
    private String keySecret;   //密钥：密码

    public static String KEY_ID;
    public static String KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_ID = keyId;
        KEY_SECRET = keySecret;
    }
}