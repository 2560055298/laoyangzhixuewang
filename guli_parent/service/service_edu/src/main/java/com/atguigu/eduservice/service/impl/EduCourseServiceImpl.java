package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.client.VodClient;
import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuiguException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduVideoService eduVideoService;        //小节

    @Autowired
    private EduChapterService eduChapterService;    //章

    @Autowired
    private VodClient vodClient;                    //openfeign调用vod服务

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1、添加到：课程表中
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int resultNum = baseMapper.insert(eduCourse);

        if(resultNum <= 0){
            throw new GuiguException(20001, "课程信息：添加失败！！！");
        }

        //2、添加到：描述表中
        String countId = eduCourse.getId();     //课程ID
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(countId);    //描述ID为（课程ID）, 二者形成关联
        eduCourseDescription.setDescription(courseInfoVo.getDescription());

        boolean saveStatus = eduCourseDescriptionService.save(eduCourseDescription);
        if (!saveStatus){
            throw new GuiguException(20001, "描述信息：添加失败！！！");
        }

        return countId;     //返回课程ID
    }

    //2、根据CourseID 查询课程信息
    @Override
    public CourseInfoVo getCourseInfo(String courseID) {
        //返回结果
        CourseInfoVo courseInfoVo = new CourseInfoVo();

        //查询课程信息
        EduCourse eduCourse = baseMapper.selectById(courseID);

        //查询：课程简介
        EduCourseDescription description = eduCourseDescriptionService.getById(courseID);

        //为Vo：设置（课程信息、课程简介）
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        if(description != null){
            courseInfoVo.setDescription(description.getDescription());
        }

        return courseInfoVo;
    }

    //3、根据courseInfoVo：更新课程信息
    @Override
    public void updCourseInfo(CourseInfoVo courseInfoVo) {
        //更新课程
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        System.out.println(eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update <= 0){
            throw new GuiguException(20001, "更新课程失败~~~");
        }

        //更新简介
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());

        eduCourseDescriptionService.updateById(description);
    }

    //通过课程Id：查询课程信息
    @Override
    public CoursePublishVo getCoursePublishVoById(String courseId) {
        return baseMapper.selectCoursePublishVoById(courseId);
    }

    //通过课程Id：查询课程信息
    @Override
    public boolean delCourseById(String courseId) {

        try {
            //1、删除：所有小节（和）阿里云视频
            QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
            videoWrapper.eq("course_id", courseId);
            videoWrapper.select("video_source_id");
            List<EduVideo> list = eduVideoService.list(videoWrapper);

            System.out.println("我进行删除了~~~~");

            List<String> videoSourceId = new ArrayList<>(); //添加：阿里云视频Id

            if(list != null){
                for(EduVideo video : list){
                    if(!StringUtils.isEmpty(video.getVideoSourceId())){
                        videoSourceId.add(video.getVideoSourceId());
                    }
                }
            }

            System.out.println("videoSourceId ==>> " + videoSourceId);

            if(!StringUtils.isEmpty(videoSourceId)){
                vodClient.delAliyunVideoByList(videoSourceId);   //删除小节：对应阿里云视频
            }

            eduVideoService.remove(videoWrapper);

            //2、删除：所有的章
            QueryWrapper<EduChapter> characterWrapper = new QueryWrapper<>();
            characterWrapper.eq("course_id", courseId);
            eduChapterService.remove(characterWrapper);

            //3、删除：该课程描述
            eduCourseDescriptionService.removeById(courseId);

            //4、删除：课程
            baseMapper.deleteById(courseId);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    //分页查询：所有课程信息
    @Override
    public IPage<EduCourse> pageCourse(Integer page, Integer limit, CourseQuery courseQuery) {
        System.out.println("当前页：" + page);
        System.out.println("每页显示条目数：" + limit);
        System.out.println("courseQuery" + courseQuery);

        Page<EduCourse> pageInfo = new Page<>(page, limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();


        if(courseQuery != null && !courseQuery.getTitle().equals("")){
            queryWrapper.like("title", courseQuery.getTitle());
        }

        if(courseQuery != null && !courseQuery.getStatus().equals("")){
            queryWrapper.eq("status", courseQuery.getStatus());
        }

        return baseMapper.selectPage(pageInfo, queryWrapper);
    }

    //(顾客端) 降序排列：查询8门课程
    @Cacheable(key = "'client'", value = "selectListLimitEight")
    @Override
    public List<EduCourse> selectListLimitEight() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");     //按浏览量：降序排列
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }

}
