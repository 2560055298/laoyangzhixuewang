package com.yyy.statservice.hystrix;

import com.yyy.statservice.client.UcenterClient;
import org.springframework.stereotype.Component;

/**
 * @author 老洋
 * @version 1.0
 */

@Component
public class UcenterHystrix implements UcenterClient {
    @Override
    public Integer getCount(String gmtCreate) {
        return 0;
    }
}
