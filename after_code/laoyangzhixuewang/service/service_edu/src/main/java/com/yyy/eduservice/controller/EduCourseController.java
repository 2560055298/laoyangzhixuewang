package com.yyy.eduservice.controller;
import com.yyy.commonutils.R;
import com.yyy.eduservice.entity.EduCourse;
import com.yyy.eduservice.entity.vo.CourseInfoVo;
import com.yyy.eduservice.entity.vo.CoursePublishVo;
import com.yyy.eduservice.entity.vo.CourseQuery;
import com.yyy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
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

    //通过课程Id：查询课程信息
    @GetMapping("getCoursePublishVoById/{courseId}")
    public R getCoursePublishVoById(@PathVariable String courseId){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVoById(courseId);

        return R.ok().data("item", coursePublishVo);
    }

    //确认：发布课程
    @GetMapping("confirmCourse/{courseId}")
    public R confirmCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);

        return R.ok();
    }

    //分页查询：所有课程信息
    @PostMapping("getCourseList/{page}/{limit}")
    public R getCourseList(@PathVariable Integer page,      //当前页
                           @PathVariable Integer limit,     //每页显示条目数
                           @RequestBody(required = false) CourseQuery courseQuery){
        IPage<EduCourse> pageInfo = courseService.pageCourse(page, limit, courseQuery);
        return R.ok().data("rows", pageInfo.getRecords())
                     .data("total", pageInfo.getTotal());
    }

    //删除：课程信息
    @GetMapping("delCourse/{courseId}")
    public R delCourse(@PathVariable String courseId){
        boolean result = courseService.delCourseById(courseId);
        return result ? R.ok() : R.error().message("删除：课程信息, 失败");
    }
}

