package com.yyy.eduservice.entity.vo;

import lombok.Data;

/**
 * @Author HeJinYang
 * @Description 视频的（小节类）
 * @Date 2022/1/15 18:33
 * @Version 1.0
 */

@Data
public class VideoVo {
    private String id;
    private String title;
    private String videoSourceId;       //阿里云：视频ID
    private Integer isFree;
}