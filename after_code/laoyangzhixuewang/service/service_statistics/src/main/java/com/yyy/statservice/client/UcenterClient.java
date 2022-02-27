package com.yyy.statservice.client;

import com.yyy.statservice.hystrix.UcenterHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 老洋
 * @version 1.0
 */
@Component
@FeignClient(value = "service-ucenter", fallback = UcenterHystrix.class)
public interface UcenterClient {
    //1、根据创建时间：获取注册的人数
    @GetMapping("api/ucenter/member/getCount/{gmtCreate}")
    public Integer getCount(@PathVariable(value = "gmtCreate") String gmtCreate);
}
