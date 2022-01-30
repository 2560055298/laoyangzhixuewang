package com.atguigu.eduservice.controller.front;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
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
 * @Date 2022/1/28 9:03
 * @Version 1.0
 */

@Api(description = "讲师管理(顾客端)")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/client/teacher")
public class EduFrontTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    //1、降序排列：查询4位讲师
    @GetMapping("getListTeacher")
    public R getListTeacher(){
        List<EduTeacher> list = eduTeacherService.selectListLimitFour();

        return R.ok().data("items", list);
    }
}