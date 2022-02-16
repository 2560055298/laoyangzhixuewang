package com.atguigu.eduorder.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduorder.service.PayLogService;
import com.atguigu.servicebase.exceptionhandler.GuiguException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *      支付日志表 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-02-10
 */
@RestController
@RequestMapping("/eduorder/payLog")
public class PayLogController {
    @Autowired
    private PayLogService payLogService;

    //1、生成（微信支付）：二维码
    @GetMapping("createQrCodePayment/{orderNo}")
    public R createQrCodePayment(@PathVariable String orderNo){
        Map<String, Object> map = payLogService.createQr(orderNo);
        return R.ok().data("map", map);
    }

    //2、通过orderNo查询支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo){
        //查询支付信息
        Map<String, String> map = payLogService.queryPayStatusInfo(orderNo);

        //支付失败
        if(map == null){
            return R.error().message("支付失败");
        }

        //支付成功：更新支付信息
        if(map.get("trade_state").equals("SUCCESS")){
            payLogService.updatePayStatusInfo(map);

            return R.ok().data("trade_state", "true").message("支付成功");
        }

        //正在支付
        return R.ok().code(25000).message("正在支付中....");
    }

}

