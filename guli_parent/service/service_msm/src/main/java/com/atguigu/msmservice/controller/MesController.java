package com.atguigu.msmservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.msmservice.service.MesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/30 20:14
 * @Version 1.0
 */

@Api(description = "腾讯云发送短信")
@RestController
@RequestMapping("/msm/send")
@CrossOrigin
public class MesController {
    @Autowired
    private MesService mesService;

    //通过腾讯云：发送注册短信
    @ApiOperation("通过腾讯云：发送注册短信")
    @GetMapping("sendMessage/{phone}")
    public R sendMessage(@PathVariable String phone){
        boolean sendResult = mesService.sendMessageByTencent(phone);

        return sendResult ? R.ok() : R.error().message("腾讯云, 发送短信失败");
    }
}