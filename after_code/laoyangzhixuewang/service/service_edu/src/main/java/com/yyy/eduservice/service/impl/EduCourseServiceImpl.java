package com.yyy.eduservice.service.impl;


import com.yyy.servicebase.vo.WebCourseVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyy.eduservice.client.VodClient;
import com.yyy.eduservice.entity.EduChapter;
import com.yyy.eduservice.entity.EduCourse;
import com.yyy.eduservice.entity.EduCourseDescription;
import com.yyy.eduservice.entity.EduVideo;
import com.yyy.eduservice.entity.vo.CourseInfoVo;
import com.yyy.eduservice.entity.vo.CoursePublishVo;
import com.yyy.eduservice.entity.vo.CourseQuery;
import com.yyy.eduservice.entity.vo.FrontCourseVo;
import com.yyy.eduservice.mapper.EduCourseMapper;
import com.yyy.eduservice.service.EduChapterService;
import com.yyy.eduservice.service.EduCourseDescriptionService;
import com.yyy.eduservice.service.EduCourseService;
import com.yyy.eduservice.service.EduVideoService;
import com.yyy.servicebase.exceptionhandler.GuiguException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
//    @Cacheable(key = "'client'", value = "selectListLimitEight")
    @Override
    public List<EduCourse> selectListLimitEight() {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");     //按浏览量：降序排列
        queryWrapper.last("limit 8");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<EduCourse> selCourseByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", teacherId);
        return baseMapper.selectList(queryWrapper);
    }

    //根据FrontCourseVo （分页查询）课程信息
    @Override
    public HashMap<String, Object> selPageCourseByFrontCourseVo(Page<EduCourse> page, FrontCourseVo frontCourseVo) {
        //声明：查询QueryWrapper
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        //设置查询条件
        if(!StringUtils.isEmpty(frontCourseVo)){
            String subjectParentId = frontCourseVo.getSubjectParentId();        //一级课程分类ID
            String subjectId = frontCourseVo.getSubjectId();                    //二级课程分类ID
            String buyCountSort = frontCourseVo.getBuyCountSort();              //关注度
            String gmtCreateSort = frontCourseVo.getGmtCreateSort();            //最新时间
            String priceSort = frontCourseVo.getPriceSort();                    //价格

            if(!StringUtils.isEmpty(subjectParentId)){  //当：一级分类（不为空）
                queryWrapper.like("subject_parent_id", subjectParentId);
            }

            if(!StringUtils.isEmpty(subjectId)){  //当：二级分类（不为空）
                queryWrapper.like("subject_id", subjectId);
            }

            if(!StringUtils.isEmpty(buyCountSort)){  //当：关注度（不为空）
                queryWrapper.orderByDesc("buy_count");
            }

            if(!StringUtils.isEmpty(gmtCreateSort)){  //当：最新时间（不为空）
                queryWrapper.orderByDesc("gmt_create");
            }

            if(!StringUtils.isEmpty(priceSort)){   //当：价格（不为空）
                queryWrapper.orderByDesc("price");
            }
        }

        baseMapper.selectMapsPage(page, queryWrapper);

        //构建：返回信息（课程map）
        HashMap<String, Object> courseMapInfo = new HashMap<>();

        System.out.println("总条数：" + page.getTotal());
        System.out.println("总页数：" + page.getPages());

        courseMapInfo.put("current", page.getCurrent());
        courseMapInfo.put("pages", page.getPages());
        courseMapInfo.put("total", page.getTotal());
        courseMapInfo.put("records", page.getRecords());
        courseMapInfo.put("hasPrevious", page.hasPrevious());
        courseMapInfo.put("hasNext", page.hasNext());

        return courseMapInfo;
    }

    //根据：课程ID查询课程信息
    @Override
    public WebCourseVo getCourseInfoByCourseId(String courseId) {
        return baseMapper.selCourseInfoByCourseId(courseId);
    }
}
