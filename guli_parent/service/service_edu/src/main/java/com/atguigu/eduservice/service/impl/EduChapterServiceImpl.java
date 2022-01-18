package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.vo.ChapterVo;
import com.atguigu.eduservice.entity.vo.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterAndVideoList(String courseId) {
        ArrayList<ChapterVo> result = new ArrayList<>();

        //1、根据课程ID查询：所有的章
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id", courseId);
        List<EduChapter> chaptersList = baseMapper.selectList(chapterWrapper);

        //2、根据课程ID查询: 所有的节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id", courseId);
        List<EduVideo> videosList = eduVideoService.list(videoWrapper);

        //3、遍历章, 设置节， 返回结果
        for(EduChapter eduChapter : chaptersList){
            ChapterVo chapterVo = new ChapterVo();
            List<VideoVo>  children = new ArrayList<>();
            BeanUtils.copyProperties(eduChapter, chapterVo);    //拷贝到：Vo中

            //设置节
            for(EduVideo eduVideo : videosList){
                if(eduVideo.getChapterId().equals(chapterVo.getId())){  //节：对应章
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);        //将节：加入到Vo中
                    children.add(videoVo);                             //将：VideoVo加入children中
                }
            }

            chapterVo.setChildren(children);    //为章：设置节
            result.add(chapterVo);              //将（章Vo）加到result中
        }

        return result;
    }

    //删除：章 (如果：该章, 下面没有（节）可以删除。 否则无法删除)
    @Override
    public boolean isDelChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);

        int count = eduVideoService.count(wrapper);

        int result = 0;     //删除：影响的行数

        if(count <= 0){     //章下：没有小结（可以删除）
            result = baseMapper.deleteById(chapterId);
        }

        return result > 0;  //删除成功~~~~
    }
}
