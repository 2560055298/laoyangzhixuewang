package com.yyy.ucenterservice.service.impl;

import com.yyy.commonutils.JwtUtils;
import com.yyy.commonutils.R;
import com.yyy.servicebase.exceptionhandler.GuiguException;
import com.yyy.servicebase.vo.UcenterMember;
import com.yyy.ucenterservice.entity.vo.LoginVo;
import com.yyy.ucenterservice.entity.vo.RegisterVo;
import com.yyy.ucenterservice.mapper.UcenterMemberMapper;
import com.yyy.ucenterservice.service.UcenterMemberService;
import com.yyy.ucenterservice.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-31
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //1、用户登录：返回token
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();        //手机号
        String password = loginVo.getPassword();    //密码

        //1-1：判断是否为空
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            throw new GuiguException(20001, "手机号 Or 密码, 不能为空");
        }

        //1-2：查询手机：数据库是否存在
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<UcenterMember>().eq("mobile", mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);

        //1-3: 判断ucenterMember 对象是否为空
        if(StringUtils.isEmpty(ucenterMember) || !ucenterMember.getPassword().equals(MD5.encrypt(password))){
            throw new GuiguException(20001, "手机 Or 密码：错误");
        }

        //1-4：生成token返回
        return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
    }


    //2、用户注册
    @Override
    public R register(RegisterVo registerVo) {
        //2-1：获取参数
        String mobile = registerVo.getMobile();         //手机号
        String password = registerVo.getPassword();     //密码
        String nickname = registerVo.getNickname();     //昵称
        String code = registerVo.getCode();             //验证码

        //2-2: 判断是否有误
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code)){
           return R.error().message("(顾客) 用户注册, 参数有误");
        }

        //2-3：查询数据库, mobile是否存在
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<UcenterMember>().eq("mobile", mobile);
        Integer integer = baseMapper.selectCount(queryWrapper);
        if(integer > 0){
            return R.error().message("手机号已注册");
        }

        //2-4：查询code验证码, 是否与缓存一致
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(StringUtils.isEmpty(redisCode) || !redisCode.equals(code)){
            return R.error().message("验证码, 不匹配");
        }

        //2-5：保存
        UcenterMember ucenterMember = new UcenterMember();
        BeanUtils.copyProperties(registerVo, ucenterMember);
        ucenterMember.setPassword(MD5.encrypt(ucenterMember.getPassword()));
        ucenterMember.setIsDisabled(false);     //设置用户：未禁用
        ucenterMember.setAvatar("https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/01/12/c319a3da734b4de8b829291800b4a806tuzi.jpg");            //头像
        baseMapper.insert(ucenterMember);

        return R.ok();
    }


    //3、根据创建时间：获取注册的人数
    @Override
    public Integer getNumberByCreateTime(String gmtCreate) {
        return baseMapper.selectCountByCreateTime(gmtCreate);
    }
}
