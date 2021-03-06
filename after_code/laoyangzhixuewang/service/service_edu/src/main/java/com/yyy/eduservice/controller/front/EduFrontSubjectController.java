package com.yyy.eduservice.controller.front;

import com.yyy.commonutils.R;
import com.yyy.eduservice.entity.subject.OneSubject;
import com.yyy.eduservice.service.EduSubjectService;
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
 * @Date 2022/2/5 17:22
 * @Version 1.0
 */

@Api(description = "分类管理(顾客)")
@RestController
@RequestMapping("/eduservice/client/subject")
@CrossOrigin
public class EduFrontSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    @GetMapping("getSubjectList")
    public R getSubjectList(){
        List<OneSubject> oneAndTwoSubjects = subjectService.getOneAndTwoSubjects();
        return R.ok().data("oneAndTwoSubjects", oneAndTwoSubjects);
    }
}