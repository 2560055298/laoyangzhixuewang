package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuiguException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
@Api(description = "小节管理")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    //1、添加：小节信息
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        System.out.println("添加小节：" + eduVideo);

        eduVideoService.save(eduVideo);
        return R.ok();
    }

    //2、查询：小节信息
    @GetMapping("selVideo/{videoId}")
    public R selVideo(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);

        return R.ok().data("eduVideo", eduVideo);
    }

    //3、修改：小节信息
    @PostMapping("updVideo")
    public R updVideo(@RequestBody EduVideo eduVideo){
        boolean result = eduVideoService.updateById(eduVideo);
        return result ? R.ok() : R.error().message("修改小节信息：失败");
    }

    //4、删除：小节信息
    @GetMapping("delVideo/{videoId}")
    public R delVideo(@PathVariable String videoId){
        EduVideo video = eduVideoService.getById(videoId);  //获取video对象
        String videoSourceId = video.getVideoSourceId();    //获取阿里云视频：ID

        System.out.println("小节视频的ID：" + videoSourceId);

        if(!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.delAliyunVideo(videoSourceId);  //删除：阿里云视频

            if(result.getCode() == 20001){
                throw new GuiguException(20001, "根据 videoSourceId 删除（阿里云视频）失败");
            }
        }

        boolean result = eduVideoService.removeById(videoId);
        return result ? R.ok() : R.error().message("删除小节信息：失败");
    }
}

