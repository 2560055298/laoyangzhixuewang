package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/12 10:23
 * @Version 1.0
 */

@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadAvartarToAli(MultipartFile file) {
        //Bucket所在地域对应的Endpoint
        String endpoint = ConstantPropertiesUtils.END_POINT;

        //阿里云账号AccessKey
        String accessKeyId = ConstantPropertiesUtils.KEY_ID;

        String accessKeySecret = ConstantPropertiesUtils.KEY_SECRET;

        String examplebucket = ConstantPropertiesUtils.BUCKET_NAME;

        //上传文件的名称
        String fileName = file.getOriginalFilename();

        //加UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        fileName = uuid + fileName;

        //添加时间/yyyy/MM/dd + UUID（无-） + 文件名
        fileName = DateTime.now().toString("yyyy/MM/dd") + "/" + fileName;

        InputStream inputStream = null;
        OSS ossClient = null;

        try {
            inputStream = file.getInputStream();

            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）
            ossClient.putObject(examplebucket, fileName, inputStream);

            //https://laoyangzhixuewang01.oss-cn-beijing.aliyuncs.com/1.png
            return "https://" + examplebucket + "." + endpoint + "/" + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            // 关闭OSSClient。
            ossClient.shutdown();
        }
    }
}