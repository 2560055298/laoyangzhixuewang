package com.yyy.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yyy.eduservice.entity.EduSubject;
import com.yyy.eduservice.entity.excel.SubjectData;
import com.yyy.eduservice.service.EduSubjectService;
import com.yyy.servicebase.exceptionhandler.GuiguException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/13 10:18
 * @Version 1.0
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService eduSubjectService;

    public SubjectExcelListener() {

    }

    //传入：EduSubjectService用于操作数据库
    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }


    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        //如果data为null直接返回
        if(data == null){
            throw new GuiguException(20001, "添加（分类Excel）无数据");
        }


        //获取第1列（oneSubjetName）： 判断是否存在（一级分类）
        String oneSubjectName = data.getOneSubjectName();
        EduSubject oneSubject = existOneSubjectName(oneSubjectName);   //一级标题对象

        if(oneSubject == null){     //添加一级标题
            EduSubject eduSubject = new EduSubject();
            eduSubject.setTitle(oneSubjectName);        //设置分类名称
            eduSubject.setParentId("0");                //作为父ID
            eduSubject.setSort(0);                      //排序字段：默认为0

            eduSubjectService.save(eduSubject);
        }


        //获取第2列（twoSubjetName）： 判断是否存在（二级分类）
        String twoSubjectName = data.getTwoSubjectName();
        String parentId = existOneSubjectName(oneSubjectName).getId();  //父ID
        EduSubject twoSubject = existTwoSubjectName(twoSubjectName,parentId);

        if(twoSubject == null){
            EduSubject subject = new EduSubject();
            subject.setTitle(twoSubjectName);        //设置分类名称
            subject.setParentId(parentId);           //作为父ID
            subject.setSort(0);                      //排序字段：默认为0

            eduSubjectService.save(subject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    //是否存在：一级分类
    public EduSubject existOneSubjectName(String oneSubjectName){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", oneSubjectName);     //存在该名称
        wrapper.eq("parent_id", 0);          //父ID为0
        return eduSubjectService.getOne(wrapper);
    }

    //是否存在：二级分类
    public EduSubject existTwoSubjectName(String twoSubjectName, String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", twoSubjectName);     //存在该名称
        wrapper.eq("parent_id", parentId);       //父ID为：一级标签ID

        return eduSubjectService.getOne(wrapper);
    }
}