package com.yyy.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/24 15:18
 * @Version 1.0
 */

public interface VodService {

    //上传（小节视频）：返回（阿里云视频ID）
    String uploadVideo(MultipartFile file);

    //删除（小节视频）
    void delVideo(String videoSourceId);

    //删除（小节视频）, 根据Ids集合
    void delVideoByList(List<String> videoIds);
}