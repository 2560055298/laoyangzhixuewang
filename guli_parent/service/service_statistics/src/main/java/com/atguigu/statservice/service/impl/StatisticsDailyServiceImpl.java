package com.atguigu.statservice.service.impl;

import com.atguigu.statservice.client.UcenterClient;
import com.atguigu.statservice.entity.StatisticsDaily;
import com.atguigu.statservice.mapper.StatisticsDailyMapper;
import com.atguigu.statservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-02-12
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;

    //1、根据某一天（yyyy-MM-dd） 添加注册人数
    @Override
    public void addStatisicsByDay(String day) {

        //先删除, 后添加：保证（statistics_daily表中）同一时间, 只有一条记录
        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("date_calculated", day);
        baseMapper.delete(queryWrapper);

        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setDateCalculated(day);     //设置统计日期
        statisticsDaily.setRegisterNum(ucenterClient.getCount(day));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100, 200));         //设置：登记人数
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100, 200));     //设置：每日（播放视频数）
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100, 200));        //设置：每日新增课程数

        baseMapper.insert(statisticsDaily);
    }

    //2、根据：字段类型、开始时间、截止时间 -->>>查询statisics表
    @Override
    public Map<String, List> getChartsInfo(String type, String begin, String end) {
        if(StringUtils.isEmpty(type) || StringUtils.isEmpty(begin) || StringUtils.isEmpty(end)){
            return new HashMap<>();
        }

        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", begin, end);
        queryWrapper.select(type, "date_calculated");
        List<StatisticsDaily> stats = baseMapper.selectList(queryWrapper);
        Map<String, List> map = new HashMap<>();
        List<Integer> rows = new ArrayList<>();
        List<String> cols = new ArrayList<>();

        for(StatisticsDaily s : stats){
            switch (type){
                case "register_num": rows.add(s.getRegisterNum());  break;
                case "video_view_num": rows.add(s.getVideoViewNum());  break;
                case "login_num": rows.add(s.getLoginNum());  break;
                case "course_num": rows.add(s.getCourseNum());  break;
            }

            cols.add(s.getDateCalculated());
        }

        map.put("rows", rows);
        map.put("cols", cols);

        return map;
    }
}
