package com.yyy.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yyy.eduorder.entity.Order;
import com.yyy.eduorder.entity.PayLog;
import com.yyy.eduorder.mapper.PayLogMapper;
import com.yyy.eduorder.service.OrderService;
import com.yyy.eduorder.service.PayLogService;
import com.yyy.eduorder.utils.HttpClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-02-10
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {
    @Autowired
    private OrderService orderService;

    //1、生成（微信支付）：二维码
    @Override
    public Map<String, Object> createQr(String orderNo) {
        //1-1：根据订单号 (查询订单信息)
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(queryWrapper);

        //1-2：微信：官方代码（生成二维码） --->>> 设置支付参数
        HashMap<String, String> map = new HashMap<>();
        map.put("appid", "wx74862e0dfcf69954");
        map.put("mch_id", "1558950191");
        map.put("nonce_str", WXPayUtil.generateNonceStr());
        map.put("body", order.getCourseTitle());
        map.put("out_trade_no", orderNo);
        map.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue()+"");
        map.put("spbill_create_ip", "127.0.0.1");
        map.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify");
        map.put("trade_type", "NATIVE");

        //1-3：HTTPClient来根据URL访问第三方接口并且传递参数
        HttpClient client= new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");

        try {
            //1-4：将xml转换为map形式
            client.setXmlParam(WXPayUtil.generateSignedXml(map, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            //1-5：得到xml
            String xml = client.getContent();

            //1-6：将xml转换为Map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            //1-7: 返回结果集
            Map<String, Object> mapResult = new HashMap<>();
            mapResult.put("out_trade_no", orderNo); //订单号
            mapResult.put("course_id", order.getCourseId());
            mapResult.put("total_fee", order.getTotalFee());
            mapResult.put("result_code", resultMap.get("result_code"));
            mapResult.put("code_url", resultMap.get("code_url"));

            System.out.println("输出结果：" + mapResult);
            return mapResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }


    //2、查询支付信息
    @Override
    public Map<String, String> queryPayStatusInfo(String orderNo) {
        //2-1：获取订单信息
        try {
            //1、封装参数
            Map<String, String> m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());

            //2、设置请求
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            //3、返回第三方的数据
            String xml = client.getContent();

            //4、转换为Map返回
            return WXPayUtil.xmlToMap(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //3、支付成功：更新支付信息
    @Override
    public void updatePayStatusInfo(Map<String, String> map) {
        //3-1：根据map获取到order信息
        String orderId = map.get("out_trade_no");
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_no", orderId);
        Order order = orderService.getOne(queryWrapper);


        //3-2：判断order是status (1为：已支付; 0为：未支付)
        if(order.getStatus() == 1){     //已支付：直接可以观看了
            return;
        }

        //3-3: 更新订单表
        order.setStatus(1);             //设置为：已支付
        orderService.updateById(order);

        //3-4: 修改支付状态, 写入日志
        PayLog payLog = new PayLog();
        payLog.setOrderNo(order.getOrderNo());          //订单号
        payLog.setPayTime(new Date());                  //支付完成时间
        payLog.setTotalFee(order.getTotalFee());        //支付金额
        payLog.setTransactionId(map.get("transaction_id")); //交易流水号
        payLog.setTradeState(map.get("trade_state"));   //交易状态
        payLog.setPayType(1);                           //支付类型：1微信；2支付宝
        payLog.setAttr(JSONObject.toJSONString(map));   //其他属性

        baseMapper.insert(payLog);                      //添加：日志
    }
}
