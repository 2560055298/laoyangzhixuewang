package com.yyy.eduservice.controller.front;

import com.yyy.commonutils.R;
import com.yyy.eduservice.entity.EduCourse;
import com.yyy.eduservice.entity.EduTeacher;
import com.yyy.eduservice.service.EduCourseService;
import com.yyy.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/28 9:03
 * @Version 1.0
 */

@Api(description = "讲师管理(顾客端)")
@RestController
@RequestMapping("/eduservice/client/teacher")
@CrossOrigin
public class EduFrontTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    //1、降序排列：查询4位讲师
    @GetMapping("getListTeacher")
    public R getListTeacher(){
        List<EduTeacher> list = eduTeacherService.selectListLimitFour();

        return R.ok().data("items", list);
    }

    //2、分页查询：名师信息（每页8条）
    @GetMapping("getPageTeacher/{currentPage}/{limit}")
    public R getPageTeacher(@PathVariable Long currentPage, @PathVariable Long limit){
        Page<EduTeacher> page = new Page<>(currentPage, limit);
        return R.ok().data("data", eduTeacherService.selectPageTeacherMap(page));
    }

    //3、根据id：查询讲师信息
    @GetMapping("selTeacherById/{teacherId}")
    public R selTeacherById(@PathVariable String teacherId){
        EduTeacher teacher = eduTeacherService.getById(teacherId);
        List<EduCourse> courses =  eduCourseService.selCourseByTeacherId(teacherId);

        return R.ok().data("teacher", teacher).data("courses", courses).data("length", courses.size());
    }
}