package com.atguigu.eduorder.service;

import com.atguigu.eduorder.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-02-10
 */
public interface PayLogService extends IService<PayLog> {

    //1、生成（微信支付）：二维码
    Map<String, Object> createQr(String orderNo);

    //2、查询支付信息
    Map<String, String> queryPayStatusInfo(String orderNo);

    //3、支付成功：更新支付信息
    void updatePayStatusInfo(Map<String, String> map);
}
