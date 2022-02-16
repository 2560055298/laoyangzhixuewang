package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.servicebase.vo.WebCourseVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */

public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo selectCoursePublishVoById(String courseId);

    WebCourseVo selCourseInfoByCourseId(String courseId);
}
