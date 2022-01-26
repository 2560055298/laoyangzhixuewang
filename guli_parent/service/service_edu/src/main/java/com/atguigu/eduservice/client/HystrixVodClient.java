package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/26 19:56
 * @Version 1.0
 */

@Component
public class HystrixVodClient implements VodClient{

    @Override
    public R delAliyunVideo(String videoSourceId) {
        return R.error().message("根据 videoSourceId 删除（阿里云视频失败）");
    }

    @Override
    public R delAliyunVideoByList(List<String> videoIds) {
        return R.error().message("根据 List<String> videoIds 删除（阿里云视频失败）");
    }
}