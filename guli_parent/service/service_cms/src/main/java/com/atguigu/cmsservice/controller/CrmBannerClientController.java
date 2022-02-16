package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-27
 */
@Api(description = "banner管理（顾客）")
@RestController
@RequestMapping("/cmsservice/client/banner")
public class CrmBannerClientController {
    @Autowired
    private CrmBannerService crmBannerService;

    //1、查询banner集合（顾客）（限制为2条）记录。 按更新时间（降序）
    @GetMapping("getListBanner")
    public R getListBanner(){
       return R.ok().data("items", crmBannerService.selectListBanner());
    }
}

