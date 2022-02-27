package com.yyy.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/12 15:16
 * @Version 1.0
 */
@Component
public class HystrixOrderClient implements OrderClient{

    @Override
    public boolean getOrderStatus(String courseId, String memberId) {
        return false;       //不能观看
    }
}