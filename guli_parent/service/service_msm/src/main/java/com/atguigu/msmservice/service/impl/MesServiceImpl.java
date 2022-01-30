package com.atguigu.msmservice.service.impl;

import com.atguigu.msmservice.service.MesService;
import com.atguigu.msmservice.utils.RandomUtil;
import com.atguigu.servicebase.exceptionhandler.GuiguException;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.sms.v20210111.SmsClient;
import com.tencentcloudapi.sms.v20210111.models.*;

import java.util.concurrent.TimeUnit;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/30 20:21
 * @Version 1.0
 */

@Service
public class MesServiceImpl implements MesService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //通过腾讯云：发送注册短信
    @Override
    public boolean sendMessageByTencent(String phone) {
        //判断：手机号码时候为空
        if(StringUtils.isEmpty(phone)){
            throw new GuiguException(20001, "手机号码为空");               //传递过来：手机号码为null Or 空
        }

        //从Redis查看是否有：手机对应验证码
        String status = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(status)){
            return true;        //缓存中有：验证码
        }

        //生成：四位验证码
        String fourBitRandom = RandomUtil.getFourBitRandom();

        //调用：腾讯云 (短信) 发送验证码
        boolean result = tencentMessageApi(phone, fourBitRandom);

        //将手机号码（为key）,  验证码为(value) 存到Redis中
        if(result){

            redisTemplate.opsForValue().set(phone, fourBitRandom, 1, TimeUnit.MINUTES);  //添加到缓存
            return true;        //返回
        }

        return false;
    }


    //调用腾讯云（短信）：接口
    public boolean tencentMessageApi(String phone, String fourBitRandom){
        try {
            Credential cred = new Credential("AKID50e29zZtvgAOL1QYQ1KM8droVv3uUnpa", "VjgMltkOlrhFWWFhsmQnsgDUUCdngIdi");

            // 实例化一个http选项，可选，没有特殊需求可以跳过
            HttpProfile httpProfile = new HttpProfile();
            // 设置代理
            // httpProfile.setProxyHost("真实代理ip");
            // httpProfile.setProxyPort(真实代理端口);
            /* SDK默认使用POST方法。
             * 如果你一定要使用GET方法，可以在这里设置。GET方法无法处理一些较大的请求 */
            httpProfile.setReqMethod("POST");
            /* SDK有默认的超时时间，非必要请不要进行调整
             * 如有需要请在代码中查阅以获取最新的默认值 */
            httpProfile.setConnTimeout(60);
            /* SDK会自动指定域名。通常是不需要特地指定域名的，但是如果你访问的是金融区的服务
             * 则必须手动指定域名，例如sms的上海金融区域名： sms.ap-shanghai-fsi.tencentcloudapi.com */
            httpProfile.setEndpoint("sms.tencentcloudapi.com");

            /* 非必要步骤:
             * 实例化一个客户端配置对象，可以指定超时时间等配置 */
            ClientProfile clientProfile = new ClientProfile();
            /* SDK默认用TC3-HMAC-SHA256进行签名
             * 非必要请不要修改这个字段 */
            clientProfile.setSignMethod("HmacSHA256");
            clientProfile.setHttpProfile(httpProfile);
            /* 实例化要请求产品(以sms为例)的client对象
             * 第二个参数是地域信息，可以直接填写字符串ap-guangzhou，或者引用预设的常量 */
            SmsClient client = new SmsClient(cred, "ap-guangzhou",clientProfile);
            SendSmsRequest req = new SendSmsRequest();

            /* 短信应用ID: 短信SdkAppId在 [短信控制台] 添加应用后生成的实际SdkAppId，示例如1400006666 */
            String sdkAppId = "1400627776";
            req.setSmsSdkAppId(sdkAppId);

            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，签名信息可登录 [短信控制台] 查看 */
            String signName = "老洋之家";
            req.setSignName(signName);

            /* 国际/港澳台短信 SenderId: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */
            String senderid = "";
            req.setSenderId(senderid);

            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */
            String sessionContext = fourBitRandom;
            req.setSessionContext(sessionContext);

            /* 模板 ID: 必须填写已审核通过的模板 ID。模板ID可登录 [短信控制台] 查看 */
            String templateId = "1293923";
            req.setTemplateId(templateId);

            String[] phoneNumberSet = {"+86" + phone};
            req.setPhoneNumberSet(phoneNumberSet);

            /* 模板参数: 若无模板参数，则设置为空 */
            String[] templateParamSet = {sessionContext};
            req.setTemplateParamSet(templateParamSet);

            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的
             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */
            SendSmsResponse res = client.SendSms(req);

            // 输出json格式的字符串回包
            System.out.println(SendSmsResponse.toJsonString(res));

            // 也可以取出单个值，你可以通过官网接口文档或跳转到response对象的定义处查看返回字段的定义
            System.out.println(res.getRequestId());

        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}