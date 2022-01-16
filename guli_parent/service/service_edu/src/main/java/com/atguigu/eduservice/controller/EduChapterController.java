package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */

@Api(description = "章节接口")
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService chapterService;

    @GetMapping("getList/{courseId}")
    public R getList(@PathVariable(required = false, name = "courseId") String courseId){
        List<ChapterVo> list = chapterService.getChapterAndVideoList(courseId);

        return R.ok().data("list", list);
    }
}

