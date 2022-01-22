package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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
}
