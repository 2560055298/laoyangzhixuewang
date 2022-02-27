package com.yyy.eduservice.service;

import com.yyy.eduservice.entity.EduUser;
import com.yyy.eduservice.entity.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 老洋
 * @version 1.0
 */

public interface EduUserService extends IService<EduUser> {

    //通过：UserVo的（username 和 password）获取用户信息
    EduUser getUserByUserInfo(UserVo userInfo);
}
