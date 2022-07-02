package com.xjs.classroom.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xjs.classroom.model.vod.Subject;
import com.xjs.classroom.vo.vod.SubjectEeVo;
import com.xjs.classroom.vod.listener.SubjectListener;
import com.xjs.classroom.vod.mapper.SubjectMapper;
import com.xjs.classroom.vod.service.ISubjectService;
import com.xjs.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xiejs
 * @since 2022-06-30
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {

    @Autowired
    private SubjectListener subjectListener;

    @Override
    public List<Subject> selectSubjectList(Long id) {

        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(Subject::getParentId, id);

        List<Subject> subjectList = baseMapper.selectList(wrapper);
        //subjectList遍历，得到每个subject对象，判断是否有下一层数据，有hasChildren=true
        for (Subject subject : subjectList) {
            //获取subject的id值
            Long subjectId = subject.getId();
            //查询
            boolean isChild = this.isChildren(subjectId);
            //封装到对象里面
            subject.setHasChildren(isChild);
        }
        return subjectList;
    }

    //课程分类导出
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            //查询课程分类表所有数据
            List<Subject> subjectList = baseMapper.selectList(null);

            //List<Subject> ---  List<SubjectEeVo>
            List<SubjectEeVo> subjectEeVoList = new ArrayList<>();
            for (Subject subject: subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
//                subjectEeVo.setId(subject.getId());
//                subjectEeVo.setParentId(subject.getParentId());
                BeanUtils.copyProperties(subject,subjectEeVo);
                subjectEeVoList.add(subjectEeVo);
            }

            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEeVoList);
        }catch(Exception e) {
            throw new BusinessException("导出失败");
        }
    }

    //课程分类导入
    @Override
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    SubjectEeVo.class,
                    subjectListener).sheet().doRead();
        } catch (IOException e) {
            throw new BusinessException("导入失败");
        }
    }


    //判断是否有下一层数据
    private boolean isChildren(Long subjectId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", subjectId);
        Long count = baseMapper.selectCount(wrapper);
        // 1>0  true   0>0 false
        return count > 0;
    }
}
