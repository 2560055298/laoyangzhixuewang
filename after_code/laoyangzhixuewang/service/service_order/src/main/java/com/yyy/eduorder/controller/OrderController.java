package com.yyy.eduorder.controller;

import com.yyy.commonutils.JwtUtils;
import com.yyy.commonutils.R;
import com.yyy.eduorder.entity.Order;
import com.yyy.eduorder.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/eduorder/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    //1、创建订单
    @PostMapping("createOrder/{courseId}")
    public R createOrder(@PathVariable("courseId") String courseId, HttpServletRequest request){
        String ucenterId = JwtUtils.getMemberIdByJwtToken(request);

        String orderNo = orderService.addOrder(courseId, ucenterId);    //返回：订单号

        return R.ok().data("orderNo", orderNo);
    }

    //2、根据订单号：查询订单信息
    @GetMapping("getOrderById/{orderNo}")
    public R getOrderById(@PathVariable("orderNo") String orderNo){
        QueryWrapper<Order> orderQueryWrapper = new QueryWrapper<>();
        orderQueryWrapper.eq("order_no", orderNo);
        return R.ok().data("item", orderService.getOne(orderQueryWrapper));
    }

    //3、根据(课程ID、用户ID)查询：订单状态 （status = 1 已购买; status = 0 未购买）
    @GetMapping("getOrderStatus/{courseId}/{memberId}")
    public boolean getOrderStatus(@PathVariable(value = "courseId") String courseId, @PathVariable(value = "memberId") String memberId){
        return orderService.isBuyStatus(courseId, memberId);          //购买为true, 否则为false
    }
}

