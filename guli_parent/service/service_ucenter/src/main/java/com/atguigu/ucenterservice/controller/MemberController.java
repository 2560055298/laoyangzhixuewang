package com.atguigu.ucenterservice.controller;


import com.atguigu.commonutils.JwtUtils;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.vo.UcenterMember;
import com.atguigu.ucenterservice.entity.vo.LoginVo;
import com.atguigu.ucenterservice.entity.vo.RegisterVo;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-31
 */
@Api("用户管理(顾客端)")
@RestController
@RequestMapping("api/ucenter/member")
public class MemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    //1、（顾客）登录
    @ApiOperation("（顾客）登录")
    @PostMapping("memberLogin")
    public R memberLogin(@RequestBody LoginVo loginVo){
        String token = ucenterMemberService.login(loginVo);     //用户登录：返回token
        return R.ok().data("token", token);
    }

    //2、（顾客）注册
    @ApiOperation("（顾客）注册")
    @PostMapping("memberRegister")
    public R memberRegister(@RequestBody RegisterVo registerVo){
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    //3、根据（顾客携带token）实现：sso,  返回（顾客）信息
    @ApiOperation("根据token查询（顾客信息）")
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(memberId);
        return R.ok().data("memberInfo", member);
    }

    //4、根据用户ID：查询用户信息
    @ApiOperation("根据用户ID：查询用户信息")
    @GetMapping("getMemberById/{id}")
    public UcenterMember getMemberById(@PathVariable("id") String id){
        return ucenterMemberService.getById(id);
    }

    //5、根据创建时间：获取注册的人数
    @GetMapping("getCount/{gmtCreate}")
    public Integer getCount(@PathVariable(value = "gmtCreate") String gmtCreate){
        return ucenterMemberService.getNumberByCreateTime(gmtCreate);
    }
}

