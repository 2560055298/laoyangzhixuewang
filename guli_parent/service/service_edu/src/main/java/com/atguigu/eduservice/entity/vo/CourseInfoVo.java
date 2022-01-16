package com.atguigu.eduservice.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/14 10:22
 * @Version 1.0
 */

@Data
public class CourseInfoVo {
    @ApiModelProperty(value = "课程ID")
    private String id;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程价格")
    private BigDecimal price;

    @ApiModelProperty(value = "课时数")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "所属讲师ID")
    private String teacherId;

    @ApiModelProperty(value = "所属分类：父级ID")
    private String subjectParentId;

    @ApiModelProperty(value = "所属分类：子级ID")
    private String subjectId;
}