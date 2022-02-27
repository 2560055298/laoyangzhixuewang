package com.yyy.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/12 10:23
 * @Version 1.0
 */

public interface OssService {
    String uploadAvartarToAli(MultipartFile file);
}