package com.yyy.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.yyy.commonutils.R;
import com.yyy.vod.service.VodService;
import com.yyy.vod.utils.AliyunVodSDKUtils;
import com.yyy.vod.utils.ConstantPropertiesVodUtils;
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

    @GetMapping("getVideoPlayAuth/{videoSourceId}")
    public R getVideoPlayAuth(@PathVariable("videoSourceId") String videoSourceId) throws Exception {

        //获取阿里云存储相关常量
        String accessKeyId = ConstantPropertiesVodUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesVodUtils.KEY_SECRET;

        //初始化
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId, accessKeySecret);

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoSourceId);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return R.ok().message("获取凭证成功").data("playAuth", playAuth);
    }
}