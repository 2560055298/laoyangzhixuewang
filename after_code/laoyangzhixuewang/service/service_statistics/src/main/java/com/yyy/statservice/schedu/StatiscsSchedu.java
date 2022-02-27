package com.yyy.statservice.schedu;

import com.yyy.statservice.service.StatisticsDailyService;
import com.yyy.statservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/13 10:56
 * @Version 1.0
 */
@Component
public class StatiscsSchedu {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //每天凌晨1点：统计当天注册的人数
    @Scheduled(cron = "0 0 1 1/1 * ?")
    public void query(){
        statisticsDailyService.addStatisicsByDay(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}