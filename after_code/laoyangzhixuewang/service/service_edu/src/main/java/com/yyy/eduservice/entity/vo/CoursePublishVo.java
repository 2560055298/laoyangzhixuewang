package com.yyy.eduservice.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/19 14:49
 * @Version 1.0
 */

@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;          //课程ID
    private String title;       //课程名称
    private String cover;       //封面URL
    private String teacherName; //老师名称
    private String subjectLevelOne;     //一级分类
    private String subjectLevelTwo;     //二级分类
    private Integer lessonNum;      //课程：节数
    private String price;       //课程价格（只用于显示）
}