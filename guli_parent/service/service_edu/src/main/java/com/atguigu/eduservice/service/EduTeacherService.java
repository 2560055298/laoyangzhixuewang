package com.atguigu.eduservice.service;
import com.atguigu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-05
 */
public interface EduTeacherService extends IService<EduTeacher> {

    //1、降序排列：查询4位讲师
    List<EduTeacher> selectListLimitFour();
}
