package com.yyy.eduorder.client;

import com.yyy.servicebase.vo.UcenterMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/2/10 16:08
 * @Version 1.0
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //1、根据用户ID：查询用户信息
    @GetMapping("api/ucenter/member/getMemberById/{id}")
    public UcenterMember getMemberById(@PathVariable("id") String id);

    @GetMapping("api/ucenter/member/sout/{id}")
    public String sout(@PathVariable(value = "id", required = false) String id);
}