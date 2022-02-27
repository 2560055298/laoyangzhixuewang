package com.yyy.eduservice.mapper;

import com.yyy.servicebase.vo.WebCourseVo;
import com.yyy.eduservice.entity.EduCourse;
import com.yyy.eduservice.entity.vo.CoursePublishVo;
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
