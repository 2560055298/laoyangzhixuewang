package com.yyy.eduservice.service;

import com.yyy.servicebase.vo.WebCourseVo;
import com.yyy.eduservice.entity.EduCourse;
import com.yyy.eduservice.entity.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseID);

    void updCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo getCoursePublishVoById(String courseId);

    //删除课程：ID
    boolean delCourseById(String courseId);

    //分页查询：所有课程信息
    IPage<EduCourse> pageCourse(Integer page, Integer limit, CourseQuery courseQuery);

    //(顾客端) 降序排列：查询8门课程
    List<EduCourse> selectListLimitEight();

    //根据讲师Id：查询课程
    List<EduCourse> selCourseByTeacherId(String teacherId);

    //根据FrontCourseVo （分页查询）课程信息
    HashMap<String, Object> selPageCourseByFrontCourseVo(Page<EduCourse> page, FrontCourseVo frontCourseVo);

    //根据：课程ID查询课程信息
    WebCourseVo getCourseInfoByCourseId(String courseId);
}
