package com.yyy.eduservice.service.impl;

import com.yyy.eduservice.entity.EduUser;
import com.yyy.eduservice.entity.vo.UserVo;
import com.yyy.eduservice.mapper.EduUserMapper;
import com.yyy.eduservice.service.EduUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/24 10:27
 * @Version 1.0
 */
@Service
public class EduUserServiceImpl extends ServiceImpl<EduUserMapper, EduUser> implements EduUserService {

    //通过：UserVo的（username 和 password）获取用户信息
    @Override
    public EduUser getUserByUserInfo(UserVo userInfo) {
        QueryWrapper<EduUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", userInfo.getUsername());
        wrapper.eq("password", userInfo.getPassword());
        return baseMapper.selectOne(wrapper);
    }
}