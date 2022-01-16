package com.atguigu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/13 10:11
 * @Version 1.0
 */

@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;      //一级分类

    @ExcelProperty(index = 1)
    private String twoSubjectName;      //二级分类
}