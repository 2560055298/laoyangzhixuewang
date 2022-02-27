package com.yyy.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.yyy.eduservice.entity.EduSubject;
import com.yyy.eduservice.entity.excel.SubjectData;
import com.yyy.eduservice.entity.subject.OneSubject;
import com.yyy.eduservice.entity.subject.TwoSubject;
import com.yyy.eduservice.listener.SubjectExcelListener;
import com.yyy.eduservice.mapper.EduSubjectMapper;
import com.yyy.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-13
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
            //读取上传的excel
            EasyExcel.read(inputStream , SubjectData.class, new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获取：课程分类（树型结构）
    @Override
    public List<OneSubject> getOneAndTwoSubjects() {
        List<OneSubject> result = new ArrayList<>();    //返回结果

        //查询出（1级）标题
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id", "0");
        List<EduSubject> oneSubject = baseMapper.selectList(oneWrapper);

        //查询出（2级）标题
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        twoWrapper.ne("parent_id", "0");
        List<EduSubject> twoSubject = baseMapper.selectList(twoWrapper);

        //遍历（1级标题）
        for(EduSubject father : oneSubject){
            OneSubject subject01 = new OneSubject();                //list集合的子元素
            ArrayList<TwoSubject> childrens = new ArrayList<>();    //一级标题的（孩子）

            //遍历（2级标题）
            for(EduSubject son : twoSubject){
                //subItem（2级标题ID） == item（1级标题ID）时
                if(son.getParentId().equals(father.getId())){
                    TwoSubject subject02 = new TwoSubject();    //返回二级标题（实体类）
                    BeanUtils.copyProperties(son, subject02);   //将数据库查出的二级标题（拷贝到）实体类中
                    childrens.add(subject02);               //将实体类：放入二级标题集合
                }
           }

        BeanUtils.copyProperties(father, subject01);      //设置1级标题完成
        subject01.setChildren(childrens);               //为（1级标题）实体类， 设置children
        result.add(subject01);                          //返回集合：添加（1级标题）实体类

        }
        return result;
    }
}
