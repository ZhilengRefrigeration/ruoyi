package com.xjs.classroom.vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xjs.classroom.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author xiejs
 * @since 2022-06-30
 */
public interface ISubjectService extends IService<Subject> {

    /**
     *  查询科目集合
     * @param id
     * @return
     */
    List<Subject> selectSubjectList(Long id);

    //课程分类导出
    void exportData(HttpServletResponse response);

    //课程分类导入
    void importData(MultipartFile file);
}
