package com.yyy.ucenterservice.mapper;

import com.yyy.servicebase.vo.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author laoyang
 * @since 2022-01-31
 */


public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    //1、根据创建时间：获取注册的人数
    Integer selectCountByCreateTime(String gmtCreate);
}
