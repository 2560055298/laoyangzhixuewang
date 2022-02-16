package com.atguigu.ucenterservice.controller;

import com.atguigu.ucenterservice.service.UcenterMemberService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/2 21:00
 * @Version 1.0
 */

@Controller
@RequestMapping("/api/ucenter/wx/")
public class WxApiController {
    @Autowired
    private UcenterMemberService memberService;

    //1、获取：跳转二维码路径
    @GetMapping("wetChatQrConnect")
    public String wetChatQrConnect(){
        String qrcodeUrl = memberService.getQrPath();
        return "redirect:" + qrcodeUrl;
    }

    //2、通过code获取access_token
    @GetMapping("callback")
    public String callback(String code, String state){
        //获取token信息
        String accessTokenInfo = memberService.getAccessTokenInfo(code);
        Gson gson = new Gson();
        HashMap<String, String> tokenMap = gson.fromJson(accessTokenInfo, HashMap.class);

        String openid = tokenMap.get("openid");
        String access_token = tokenMap.get("access_token");


        //通过：openid、access_token 获取（微信用户信息） 并 （保存), 返回token用户token信息
        String token = memberService.saveWechatInfo(openid, access_token);


        return "redirect:http://localhost:3000?token=" + token;
    }
}