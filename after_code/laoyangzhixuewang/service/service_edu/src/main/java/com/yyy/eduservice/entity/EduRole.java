package com.yyy.eduservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/24 10:19
 * @Version 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EduRole implements Serializable {
    private String id;          //角色ID
    private String userId;      //用户ID
    private String roleName;    //角色名称
    private String des;         //角色描述

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;     //创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;   //更新时间
}