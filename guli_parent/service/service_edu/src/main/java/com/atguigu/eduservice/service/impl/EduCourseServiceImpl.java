package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.servicebase.exceptionhandler.GuiguException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
