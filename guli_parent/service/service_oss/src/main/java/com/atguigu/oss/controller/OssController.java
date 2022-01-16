package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/12 10:24
 * @Version 1.0
 */

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {
    @Resource
    private OssService ossService;      //按名称注入

    /**
     * @Author HeJinYang
     * @Description 上传头像到：阿里oss
     * @Date 2022/1/12
     **/
    @PostMapping("/uploadAvartar")
    public R uploadAvartar(MultipartFile file){
        return R.ok().data("url", ossService.uploadAvartarToAli(file));
    }
}