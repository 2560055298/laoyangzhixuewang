package com.yyy.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author HeJinYang
 * @Description 一级标题
 * @Date 2022/1/13 17:14
 * @Version 1.0
 */

@Data
public class OneSubject {
    private String id;      //课程分类ID
    private String title;   //课程分类title

    List<TwoSubject> children = new ArrayList<>();       //二级子分类
}