package com.yyy.msmservice.service;

/**
 * @author 老洋
 * @version 1.0
 */
public interface MesService {
    //通过腾讯云：发送注册短信
    boolean sendMessageByTencent(String phone);
}
