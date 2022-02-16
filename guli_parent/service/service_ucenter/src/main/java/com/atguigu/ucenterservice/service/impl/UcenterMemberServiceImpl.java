package com.atguigu.ucenterservice.service.impl;

import com.atguigu.commonutils.JwtUtils;
import com.atguigu.servicebase.exceptionhandler.GuiguException;
import com.atguigu.servicebase.vo.UcenterMember;
import com.atguigu.ucenterservice.entity.vo.LoginVo;
import com.atguigu.ucenterservice.entity.vo.RegisterVo;
import com.atguigu.ucenterservice.mapper.UcenterMemberMapper;
import com.atguigu.ucenterservice.service.UcenterMemberService;
import com.atguigu.ucenterservice.utils.ConstantPropertiesUtils;
import com.atguigu.ucenterservice.utils.HttpClientUtils;
import com.atguigu.ucenterservice.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

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
    public void register(RegisterVo registerVo) {
        //2-1：获取参数
        String mobile = registerVo.getMobile();         //手机号
        String password = registerVo.getPassword();     //密码
        String nickname = registerVo.getNickname();     //昵称
        String code = registerVo.getCode();             //验证码

        //2-2: 判断是否有误
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(nickname) || StringUtils.isEmpty(code)){
            throw  new GuiguException(20001, "(顾客) 用户注册, 参数有误");
        }

        //2-3：查询数据库, mobile是否存在
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<UcenterMember>().eq("mobile", mobile);
        Integer integer = baseMapper.selectCount(queryWrapper);
        if(integer > 0){
            throw new GuiguException(20001, "手机号已注册");
        }

        //2-4：查询code验证码, 是否与缓存一致
        String redisCode = redisTemplate.opsForValue().get(mobile);
        if(StringUtils.isEmpty(redisCode) || !redisCode.equals(code)){
            throw new GuiguException(20001, "验证码, 不匹配");
        }

        //2-5：保存
        UcenterMember ucenterMember = new UcenterMember();
        BeanUtils.copyProperties(registerVo, ucenterMember);
        ucenterMember.setPassword(MD5.encrypt(ucenterMember.getPassword()));
        ucenterMember.setIsDisabled(false);     //设置用户：未禁用
        ucenterMember.setAvatar("https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/2022/01/12/c319a3da734b4de8b829291800b4a806tuzi.jpg");            //头像
        baseMapper.insert(ucenterMember);
    }


    //3、获取：跳转二维码路径
    @Override
    public String getQrPath() {
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s"+
                "&redirect_uri=%s"+
                "&response_type=code"+
                "&scope=snsapi_login"+
                "&state=%s"+
                "#wechat_redirect";


        try {
            String redirectUri = ConstantPropertiesUtils.BASE_URL;
            redirectUri = URLEncoder.encode(redirectUri, "UTF-8");

            String qrcodeUrl = String.format(
                    baseUrl,
                    ConstantPropertiesUtils.APP_ID,
                    redirectUri,
                    "atguigu"
            );

            return qrcodeUrl;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            throw new GuiguException(20001, "生成二维码失败");
        }
    }


    //4、通过code获取access_token
    @Override
    public String getAccessTokenInfo(String code) {
        String baseUrl =
                "https://api.weixin.qq.com/sns/oauth2/access_token" +
                        "?appid=%s" +
                        "&secret=%s"+
                        "&code=%s" +
                        "&grant_type=authorization_code";

        String redirectUri = String.format(
                baseUrl,
                ConstantPropertiesUtils.APP_ID,
                ConstantPropertiesUtils.APP_SECRET,
                code
        );
        try {
            return HttpClientUtils.get(redirectUri);
        } catch (Exception e) {
            throw new GuiguException(20001, "根据code获取token失败~~~");
        }
    }


    //5、通过：openid、access_token 获取（微信用户信息） 并 （保存), 返回token用户token信息
    @Override
    public String saveWechatInfo(String openId, String access_token) {
        //通过openId查询数据库, 若用户不存在, 则查询, 保存
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openId);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);

        if(ucenterMember == null){
            String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                    "?access_token=%s" +
                    "&openid=%s";

            userInfoUrl = String.format(
                    userInfoUrl,
                    access_token,
                    openId
            );

            try {
                String userInfo = HttpClientUtils.get(userInfoUrl);
                Gson gson = new Gson();
                HashMap UserInfoMap = gson.fromJson(userInfo, HashMap.class);

                String openid = (String) UserInfoMap.get("openid");
                String nickname = (String) UserInfoMap.get("nickname");
                String headimgurl = (String) UserInfoMap.get("headimgurl");

                UcenterMember member = new UcenterMember();
                member.setOpenid(openid);
                member.setNickname(nickname);
                member.setAvatar(headimgurl);
                baseMapper.insert(member);

                ucenterMember = member;
            } catch (Exception e) {
                e.printStackTrace();
                throw new GuiguException(20001, "保存：微信用户信息, 失败~~~");
            }
        }

        return JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
    }


    //6、根据创建时间：获取注册的人数
    @Override
    public Integer getNumberByCreateTime(String gmtCreate) {
        return baseMapper.selectCountByCreateTime(gmtCreate);
    }
}
