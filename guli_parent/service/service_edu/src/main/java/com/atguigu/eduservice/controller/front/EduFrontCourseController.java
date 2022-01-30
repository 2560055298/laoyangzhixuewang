package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/28 8:46
 * @Version 1.0
 */

@Api(description = "课程管理(顾客)")
@RestController
@RequestMapping("/eduservice/client/course")
@CrossOrigin
public class EduFrontCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    //1、降序排列：查询8门课程
    @GetMapping("getListCourse")
    public R getListCourse(){
        List<EduCourse> list = eduCourseService.selectListLimitEight();
        return R.ok().data("items", list);
    }
}