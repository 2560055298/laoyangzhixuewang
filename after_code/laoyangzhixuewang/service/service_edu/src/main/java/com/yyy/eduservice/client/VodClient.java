package com.yyy.eduservice.client;

import com.yyy.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/25 19:13
 * @Version 1.0
 */

@Component
@FeignClient(value = "service-vod", fallback = HystrixVodClient.class)
public interface VodClient {
    //根据：小节视频Id 删除阿里云视频
    @DeleteMapping("service/vod/delAliyunVideo/{videoSourceId}")
    public R delAliyunVideo(@PathVariable("videoSourceId") String videoSourceId);


    //根据：小节List<String>的ID集合, 删除阿里云视频
    @DeleteMapping("service/vod/delAliyunVideo")
    public R delAliyunVideoByList(@RequestParam("videoIds") List<String> videoIds);
}