package com.atguigu.vod.controller;

import com.atguigu.commonutils.R;
import com.atguigu.vod.service.VodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/24 15:02
 * @Version 1.0
 */

@Api(description = "小节视频管理")
@RestController
@RequestMapping("service/vod")
@CrossOrigin
public class VideoAdminController {
    @Autowired
    private VodService vodService;

    //添加视频到：阿里云
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){
        //上传视频到：阿里云
        String videoSourceId = vodService.uploadVideo(file);

        return R.ok().data("videoSourceId", videoSourceId);
    }

    //根据：小节视频Id 删除阿里云视频
    @DeleteMapping("delAliyunVideo/{videoSourceId}")
    public R delAliyunVideo(@PathVariable String videoSourceId){
        System.out.println("视频ID" + videoSourceId);
        vodService.delVideo(videoSourceId);

        return R.ok().message("视频删除成功~~~");
    }

    //根据：小节List<String>的ID集合, 删除阿里云视频
    @DeleteMapping("delAliyunVideo")
    public R delAliyunVideoByList(@RequestParam("videoIds") List<String> videoIds){
        System.out.println("视频ID集合" + videoIds);
        vodService.delVideoByList(videoIds);

        return R.ok().message("视频删除成功~~~");
    }
}