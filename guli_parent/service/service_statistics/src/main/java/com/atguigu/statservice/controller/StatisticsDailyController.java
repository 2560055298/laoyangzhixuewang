package com.atguigu.statservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.statservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author laoyang
 * @since 2022-02-12
 */
@RestController
@RequestMapping("/statservice/statisticsDaily")
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsService;

    //1、根据某一天（yyyy-MM-dd） 添加注册人数
    @GetMapping("addStatisics/{day}")
    public R addStatisics(@PathVariable(value = "day") String day){
        statisticsService.addStatisicsByDay(day);

        return R.ok();
    }

    //2、根据：字段类型、开始时间、截止时间 -->>>查询statisics表
    @GetMapping("queryStatisics/{type}/{begin}/{end}")
    public R queryStatisics(@PathVariable String type,
                            @PathVariable String begin,
                            @PathVariable String end){
        Map<String, List> map = statisticsService.getChartsInfo(type, begin, end);

        return R.ok().data("map", map);
    }
}

