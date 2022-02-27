package com.yyy.eduservice.controller;

import com.yyy.commonutils.R;
import com.yyy.eduservice.entity.subject.OneSubject;
import com.yyy.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-13
 */
@RestController
@Api(description = "课程分类管理")
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * @Author HeJinYang
     * @Description 将Excel中读取的课程信息：保存到数据库edu_subject表中
     * @Date
     **/
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        eduSubjectService.saveSubject(file, eduSubjectService);

        return R.ok();
    }

    /**
     * @Author HeJinYang
     * @Description 获取课程分类信息（树型结构）
     * @Date 2022/1/13
     **/
    @GetMapping("getSubjectList")
    public R getSubjectList(){
        List<OneSubject> subjects = eduSubjectService.getOneAndTwoSubjects();

        return R.ok().data("list", subjects);
    }
}

