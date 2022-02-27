package com.yyy.ucenterservice.service;

import com.yyy.commonutils.R;
import com.yyy.servicebase.vo.UcenterMember;
import com.yyy.ucenterservice.entity.vo.LoginVo;
import com.yyy.ucenterservice.entity.vo.RegisterVo;
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
    R register(RegisterVo registerVo);

    //3、根据创建时间：获取注册的人数
    Integer getNumberByCreateTime(String gmtCreate);
}
