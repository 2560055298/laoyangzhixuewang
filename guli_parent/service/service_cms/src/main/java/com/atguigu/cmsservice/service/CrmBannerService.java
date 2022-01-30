package com.atguigu.cmsservice.service;

import com.atguigu.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-27
 */
public interface CrmBannerService extends IService<CrmBanner> {
    //1、查询banner集合（顾客）（限制为2条）记录。 按更新时间（降序）
    List<CrmBanner> selectListBanner();
}
