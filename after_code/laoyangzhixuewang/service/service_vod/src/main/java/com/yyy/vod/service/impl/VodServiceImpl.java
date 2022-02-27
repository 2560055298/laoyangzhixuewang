package com.yyy.vod.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.yyy.servicebase.exceptionhandler.GuiguException;
import com.yyy.vod.service.VodService;
import com.yyy.vod.utils.ConstantPropertiesVodUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author HeJinYang
 * @Description
 * @Date 2022/1/24 15:20o
 * @Version 1.0
 */

@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file) {
        String accessKeyId = ConstantPropertiesVodUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesVodUtils.KEY_SECRET;

        String fileName = file.getOriginalFilename();       //
        String title = "";
        if(fileName != null){           //防止：fileName 出现null 报空指针异常
            title = fileName.substring(0, fileName.lastIndexOf('.'));
        }

        InputStream inputStream = null;
        try {

            System.out.println("fileName：" + fileName);
            System.out.println("title：" + title);
            inputStream = file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID

            if (response.isSuccess()) {
                System.out.print("VideoId=" + response.getVideoId() + "\n");
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                System.out.print("VideoId=" + response.getVideoId() + "\n");
                System.out.print("ErrorCode=" + response.getCode() + "\n");
                System.out.print("ErrorMessage=" + response.getMessage() + "\n");
            }

            return response.getVideoId();       //返回：视频ID
        } catch (IOException e) {
            e.printStackTrace();
            throw new GuiguException(20001, "阿里云（小节视频）：上传失败");
        }
    }


    //填入AccessKey信息
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入地域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

    //删除：小节视频
    @Override
    public void delVideo(String videoSourceId) {
        String accessKeyId = ConstantPropertiesVodUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesVodUtils.KEY_SECRET;

        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoSourceId);
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }


    //删除（小节视频）, 根据Ids集合
    @Override
    public void delVideoByList(List<String> videoIds) {
        String accessKeyId = ConstantPropertiesVodUtils.KEY_ID;
        String accessKeySecret = ConstantPropertiesVodUtils.KEY_SECRET;

        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            String ids = StringUtils.join(videoIds.toArray(), ",");
            request.setVideoIds(ids);
            response = client.getAcsResponse(request);
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}