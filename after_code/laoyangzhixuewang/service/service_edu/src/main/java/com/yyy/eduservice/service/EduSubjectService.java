package com.yyy.eduservice.service;

import com.yyy.eduservice.entity.EduSubject;
import com.yyy.eduservice.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author laoyang
 * @since 2022-01-13
 */
public interface EduSubjectService extends IService<EduSubject> {

    public void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    List<OneSubject> getOneAndTwoSubjects();
}
