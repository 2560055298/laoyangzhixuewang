package com.yyy.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/12 11:36
 * @Version 1.0
 */

@Component
@FeignClient(value = "service-order", fallback = HystrixOrderClient.class)
public interface OrderClient {
    //根据(课程ID、用户ID)查询：订单状态 （status = 1 已购买; status = 0 未购买）
    @GetMapping("/eduorder/order/getOrderStatus/{courseId}/{memberId}")
    boolean getOrderStatus(@PathVariable(value = "courseId") String courseId, @PathVariable(value = "memberId") String memberId);
}