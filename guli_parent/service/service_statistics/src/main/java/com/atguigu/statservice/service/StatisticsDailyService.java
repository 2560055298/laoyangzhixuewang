package com.atguigu.statservice.service;

import com.atguigu.statservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-02-12
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    //1、根据某一天（yyyy-MM-dd） 添加注册人数
    void addStatisicsByDay(String day);

    //2、根据：字段类型、开始时间、截止时间 -->>>查询statisics表
    Map<String, List> getChartsInfo(String type, String begin, String end);
}
