package com.atguigu.ucenterservice.service;

import com.atguigu.ucenterservice.entity.UcenterMember;
import com.atguigu.ucenterservice.entity.vo.LoginVo;
import com.atguigu.ucenterservice.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-31
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    //1、用户登录：返回token
    String login(LoginVo loginVo);

    //2、用户注册
    void register(RegisterVo registerVo);

    //3、获取：跳转二维码路径
    String getQrPath();

    //4、通过code获取access_token
    String getAccessTokenInfo(String code);

    //5、通过：openid、access_token 获取（微信用户信息） 并 （保存), 返回token用户token信息
    String saveWechatInfo(String openid, String access_token);
}
