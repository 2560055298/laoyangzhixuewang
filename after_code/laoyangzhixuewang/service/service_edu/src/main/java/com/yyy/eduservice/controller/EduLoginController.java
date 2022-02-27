package com.yyy.eduservice.controller;

import com.yyy.commonutils.JwtUtils;
import com.yyy.commonutils.R;
import com.yyy.eduservice.entity.EduRole;
import com.yyy.eduservice.entity.EduUser;
import com.yyy.eduservice.entity.vo.UserVo;
import com.yyy.eduservice.service.EduRoleService;
import com.yyy.eduservice.service.EduUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/10 11:10
 * @Version 1.0
 */

@Api(description = "后台登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {
    @Autowired
    private EduUserService userService;

    @Autowired
    private EduRoleService roleService;

    /**
     * @Author HeJinYang
     * @Description 登录
     * @Date 2022/1/10
     **/
    @PostMapping("login")
    public R login(@RequestBody UserVo userInfo){

        //查询数据库：如果存在, 那么生成
        EduUser user = userService.getUserByUserInfo(userInfo);
        if(user != null){
            String token = JwtUtils.getJwtToken(user.getId(), userInfo.getUsername());
            return R.ok().data("token", token);
        }


        return R.error().message("账号或密码错误~~~");
    }

    @GetMapping("info")
    public R info(@RequestParam("token") String token){
        String userId = JwtUtils.getMemberIdByToken(token);
        EduUser user = userService.getById(userId);

        if(!StringUtils.isEmpty(userId) && user != null){
            QueryWrapper<EduRole> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            EduRole role = roleService.getOne(wrapper);

            return R.ok().data("roles", "[" + role.getRoleName() + "]")
                    .data("name", user.getUsername())
                    .data("avatar", user.getAvatar());
        }

        return R.ok();
    }
}