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
public class EduUser implements Serializable {
    private String id;      //用户ID
    private String username;    //用户名
    private String password;    //用户密码
    private String nickName;    //用户昵称
    private String avatar;      //用户头像
    private String isDeleted;   //逻辑删除状态

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;     //创建时间

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;   //更新时间
}