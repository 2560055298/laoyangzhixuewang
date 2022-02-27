package com.yyy.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/6 16:20
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherQuery {
    @ApiModelProperty(name = "name", value = "讲师姓名")
    private String name;

    @ApiModelProperty(name = "level", value = "讲师级别")
    private Integer level;

    @ApiModelProperty(name = "begin", value = "创建时间")
    private String begin;

    @ApiModelProperty(name = "end", value = "结束时间")
    private String end;
}