package com.yyy.eduorder.client;

import com.yyy.servicebase.vo.WebCourseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 老洋
 * @version 1.0
 */
@FeignClient(value = "service-edu")
@Component
public interface CourseClient {
    //1、根据课程Id查询：课程信息
    @GetMapping("eduservice/client/course/getCourseById/{courseId}")
    public WebCourseVo getCourseById(@PathVariable("courseId") String courseId);

}
