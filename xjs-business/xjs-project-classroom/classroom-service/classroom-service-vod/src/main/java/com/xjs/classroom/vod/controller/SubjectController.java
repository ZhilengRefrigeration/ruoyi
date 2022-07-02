package com.xjs.classroom.vod.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xjs.classroom.model.vod.Subject;
import com.xjs.classroom.vod.service.ISubjectService;
import com.xjs.exception.BusinessException;
import com.xjs.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author xiejs
 * @since 2022-06-30
 */
@RestController
@RequestMapping("/admin/vod/subject")
@Api(tags = "课程科目接口")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    //课程分类列表
    //懒加载，每次查询一层数据
    @ApiOperation("课程分类列表")
    @GetMapping("getChildSubject/{id}")
    public Result<List<Subject>> getChildSubject(@PathVariable Long id) {
        List<Subject> list = subjectService.selectSubjectList(id);
        return Result.ok(list);
    }

    @ApiOperation("课程分类添加")
    @PostMapping("addSubject/{pid}")
    public Result<Subject> addSubject(@PathVariable Long pid, @RequestParam("subjectName") String subjectName) {
        if (pid == null) {
            pid = 0L;
        }
        Subject subject = new Subject();
        subject.setParentId(pid);
        subject.setTitle(subjectName);
        subjectService.save(subject);

        return Result.ok(null);
    }

    @ApiOperation("课程分类删除")
    @DeleteMapping("delSubject/{id}")
    public Result<Subject> delSubject(@PathVariable("id") Long id) {
        Subject subject_p = subjectService.getById(id);

        List<Subject> list = subjectService.list(new LambdaQueryWrapper<Subject>().eq(Subject::getParentId, subject_p.getId()));
        if (CollUtil.isEmpty(list)) {
            subjectService.removeById(id);
            return Result.ok(null);
        }else {
            throw new BusinessException("存在子节点");
        }

    }

    //课程分类导出
    @ApiOperation("课程分类导出")
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    //课程分类导入
    @ApiOperation("课程分类导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importData(file);
        return Result.ok(null);
    }


}

