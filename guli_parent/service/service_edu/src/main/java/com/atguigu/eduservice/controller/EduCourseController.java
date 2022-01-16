package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    /**
     * @Author HeJinYang
     * @Description 添加课程信息
     * @Date 2022/1/16
     **/
    @PostMapping("addCourse")
    public R addCourse(@RequestBody CourseInfoVo courseInfoVo){
        String id = courseService.saveCourseInfo(courseInfoVo);

        return R.ok().data("courseId", id);
    }

    /**
     * @Author HeJinYang
     * @Description 根据CourseID 查询课程信息
     * @Date 2022/1/16
     **/
    @GetMapping("getCourse/{courseID}")
    public R getCourse(@PathVariable String courseID){
        CourseInfoVo  courseInfoVo = courseService.getCourseInfo(courseID);

        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    /**
     * @Author HeJinYang
     * @Description  根据CourseInfo 更新（课程、简介）信息
     * @Date 2022/1/16
     **/
    @PostMapping("updCourse")
    public R updCourse(@RequestBody CourseInfoVo courseInfoVo){
        courseService.updCourseInfo(courseInfoVo);
        return R.ok();
    }
}

