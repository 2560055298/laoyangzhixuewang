package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-05
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    //1、降序排列：查询4位讲师
    @Cacheable(key = "'client'", value = "selectListLimitFour")
    @Override
    public List<EduTeacher> selectListLimitFour() {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");  //按讲师ID排序
        wrapper.last("limit 4");

        return baseMapper.selectList(wrapper);
    }


    //2、分页查询：名师信息（每页8条）
    @Override
    public Map<String, Object> selectPageTeacherMap(Page<EduTeacher> page) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        baseMapper.selectPage(page, queryWrapper);
        long current = page.getCurrent();   //当然页
        long pages = page.getPages();   //当前分页总页数
        long total = page.getTotal();   //总条数
        boolean hasPrevious = page.hasPrevious();
        boolean hasNext = page.hasNext();
        List<EduTeacher> records = page.getRecords();       //分页记录列表

        HashMap<String, Object> map = new HashMap<>();
        map.put("current", current);
        map.put("pages", pages);
        map.put("total", total);
        map.put("records", records);
        map.put("hasPrevious", hasPrevious);
        map.put("hasNext", hasNext);

        return map;
    }
}
