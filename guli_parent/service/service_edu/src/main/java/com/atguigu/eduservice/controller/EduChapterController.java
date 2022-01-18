package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduChapter;
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

    //1、获取章、节信息
    @GetMapping("getList/{courseId}")
    public R getList(@PathVariable(required = false, name = "courseId") String courseId){
        List<ChapterVo> list = chapterService.getChapterAndVideoList(courseId);

        return R.ok().data("list", list);
    }

    //2、添加：章
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter chapter){
        boolean status = chapterService.save(chapter);

        return status ? R.ok() : R.error().message("添加章：失败");
    }

    //3、查询章信息
    @GetMapping("selChapter/{chapterId}")
    public R selChapter(@PathVariable String chapterId){
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter", eduChapter);
    }

    //4、修改：章
    @PostMapping("updChapter")
    public R updChapter(@RequestBody EduChapter chapter){
        System.out.println(chapter);
        boolean status = chapterService.updateById(chapter);
        return status ? R.ok() : R.error().message("修改章：失败");
    }

    //5、删除：章 (如果：该章, 下面没有（节）可以删除。 否则无法删除)
    @GetMapping("delChapter/{chapterId}")
    public R delChapter(@PathVariable String chapterId){
        boolean status = chapterService.isDelChapter(chapterId);

        return status ? R.ok() : R.error().message("删除章：失败, 该章（小节）");
    }
}

