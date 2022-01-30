package com.atguigu.cmsservice.controller;


import com.atguigu.cmsservice.entity.CrmBanner;
import com.atguigu.cmsservice.service.CrmBannerService;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-27
 */
@Api(description = "banner管理（管理员）")
@RestController
@RequestMapping("/cmsservice/admin/banner")
@CrossOrigin
public class CrmBannerAdminController {
    @Autowired
    private CrmBannerService crmBannerService;

    //1、分页查询：banner（轮播图）
    @GetMapping("getListPageBanner/{page}/{limit}")
    public R getListPageBanner(@PathVariable Integer page,
                               @PathVariable Integer limit){
        Page<CrmBanner> crmBannerPage = new Page<>(page, limit);
        IPage<CrmBanner> pageInfo = crmBannerService.page(crmBannerPage, null);
        return R.ok().data("rows", pageInfo.getRecords()).data("total", pageInfo.getTotal());
    }

    //2、根据id查询：banner轮播图
    @GetMapping("getBanner/{bannerId}")
    public R getBanner(@PathVariable String bannerId){
        return R.ok().data("banner", crmBannerService.getById(bannerId));
    }

    //3、根据id修改：banner轮播图
    @PostMapping("updBanner")
    public R updBanner(@RequestParam CrmBanner banner){
        return crmBannerService.updateById(banner) ? R.ok() : R.error()
                .message("根据Id修改banner失败");
    }

    //4、添加banner
    @PostMapping("addBanner")
    public R addBanner(@RequestParam CrmBanner banner){
        return crmBannerService.save(banner) ? R.ok() : R.error()
                .message("根据Id修改banner失败");
    }


    //5、根据id删除：banner轮播图
    @GetMapping("delBanner/{bannerId}")
    public R delBanner(@PathVariable String bannerId){
        return crmBannerService.removeById(bannerId) ? R.ok() : R.error()
                .message("根据Id删除banner失败");
    }

}

