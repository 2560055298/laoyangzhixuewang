package com.yyy.eduorder.service;

import com.yyy.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-02-10
 */
public interface OrderService extends IService<Order> {

    //1、保存订单
    String addOrder(String courseId, String ucenterId);

    //2、根据(课程ID、用户ID)查询：订单状态 （status = 1 已购买; status = 0 未购买）
    boolean isBuyStatus(String courseId, String memberId);
}
