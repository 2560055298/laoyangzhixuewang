package com.yyy.eduservice.service;

import com.yyy.eduservice.entity.EduChapter;
import com.yyy.eduservice.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-14
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterAndVideoList(String courseId);

    //删除：章 (如果：该章, 下面没有（节）可以删除。 否则无法删除)
    boolean isDelChapter(String chapterId);
}
