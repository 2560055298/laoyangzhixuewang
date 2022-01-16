package com.atguigu.eduservice.entity.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author HeJinYang
 * @Description 视频（章类）
 * @Date 2022/1/15 18:33
 * @Version 1.0
 */

@Data
public class ChapterVo {
    private String id;
    private String title;

    private List<VideoVo> children = new ArrayList<>();
}