package com.yyy.eduservice.controller.front;

import com.yyy.commonutils.JwtUtils;
import com.yyy.commonutils.R;
import com.yyy.eduservice.client.OrderClient;
import com.yyy.eduservice.entity.EduCourse;
import com.yyy.eduservice.entity.vo.ChapterVo;
import com.yyy.eduservice.entity.vo.FrontCourseVo;
import com.yyy.eduservice.service.EduChapterService;
import com.yyy.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyy.servicebase.vo.WebCourseVo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrderClient orderClient;

    //1、降序排列：查询8门课程
    @GetMapping("getListCourse")
    public R getListCourse(){
        List<EduCourse> list = eduCourseService.selectListLimitEight();
        return R.ok().data("items", list);
    }

    //2、根据FrontCourseVo （分页查询）课程信息
    @PostMapping("getPageCourse/{currentPage}/{limit}")
    public R getPageCourse(@PathVariable("currentPage") Long currentPage,
                           @PathVariable("limit") Long limit,
                           @RequestBody(required = false) FrontCourseVo frontCourseVo){

        Page<EduCourse> page = new Page<>(currentPage, limit);
        HashMap<String, Object> map = eduCourseService.selPageCourseByFrontCourseVo(page, frontCourseVo);

        return R.ok().data("courseMap", map);
    }

    //3、根据课程ID：查询课程信息
    @GetMapping("getCourse/{courseId}")
    public R getCourse(@PathVariable String courseId, HttpServletRequest request){
        WebCourseVo course = eduCourseService.getCourseInfoByCourseId(courseId); //根据：课程ID查询课程信息
        List<ChapterVo> chapterAndVideoList = chapterService.getChapterAndVideoList(courseId); //根据课程ID：查询章节信息

        String memberId = JwtUtils.getMemberIdByJwtToken(request);

        return R.ok().data("course", course).data("chapterAndVideoList", chapterAndVideoList).data("buyStatus", orderClient.getOrderStatus(courseId, memberId));
    }

    //4、根据课程Id查询：课程信息
    @GetMapping("getCourseById/{courseId}")
    public WebCourseVo getCourseById(@PathVariable("courseId") String courseId){
        return eduCourseService.getCourseInfoByCourseId(courseId);
    }
}