package com.xjs.classroom.vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjs.classroom.model.vod.Teacher;
import com.xjs.classroom.vo.vod.TeacherQueryVo;
import com.xjs.classroom.vod.service.TeacherService;
import com.xjs.utils.Result;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.SelectGroup;
import com.xjs.validation.group.UpdateGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xiejs
 * @since 2022-06-29
 */
@RestController
@Api(tags = "讲师管理接口")
@RequestMapping("/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // http://localhost:8301/admin/vod/teacher/findAll
    //1 查询所有讲师
//    @ApiOperation("查询所有讲师")
//    @GetMapping("findAll")
//    public List<Teacher> findAllTeacher() {
//        //调用service方法
//        List<Teacher> list = teacherService.list();
//        return list;
//    }
    @ApiOperation("查询所有讲师")
    @GetMapping("findAll")
    public Result<List<Teacher>> findAllTeacher() {
        //模拟异常
//        try {
//            int i = 10/0;
//        }catch (Exception e) {
//            //抛出异常
//            throw new GgktException(201,"执行自定义异常处理GgktException");
//        }
        //调用service方法
        List<Teacher> list = teacherService.list();
        return Result.ok(list).message("查询数据成功");
    }

    // remove/1
    //2 逻辑删除讲师
    @ApiOperation("逻辑删除讲师")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "ID", required = true)
                                @PathVariable(name = "id") Long id) {
        boolean isSuccess = teacherService.removeById(id);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //3 条件查询分页
    @ApiOperation("条件查询分页")
    @PostMapping("findQueryPage/{current}/{limit}")
    public Result findPage(@PathVariable long current,
                           @PathVariable long limit,
                           @RequestBody(required = false) @Validated(SelectGroup.class)  TeacherQueryVo teacherQueryVo) {
        //创建page对象
        Page<Teacher> pageParam = new Page<>(current, limit);

        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");

        //判断teacherQueryVo对象是否为空
        if (teacherQueryVo == null) {//查询全部
            IPage<Teacher> pageModel =
                    teacherService.page(pageParam, null);
            return Result.ok(pageModel);
        } else {
            //获取条件值，
            String name = teacherQueryVo.getName();
            Integer level = teacherQueryVo.getLevel();
            String joinDateBegin = teacherQueryVo.getJoinDateBegin();
            String joinDateEnd = teacherQueryVo.getJoinDateEnd();
            //进行非空判断，条件封装
            if (StringUtils.hasText(name)) {
                wrapper.like("name", name);
            }
            if (!ObjectUtils.isEmpty(level)) {
                wrapper.eq("level", level);
            }
            if (StringUtils.hasText(joinDateBegin)) {
                wrapper.ge("join_date", joinDateBegin);
            }
            if (StringUtils.hasText(joinDateEnd)) {
                wrapper.le("join_date", joinDateEnd);
            }
            //调用方法分页查询
            IPage<Teacher> pageModel = teacherService.page(pageParam, wrapper);
            //返回
            return Result.ok(pageModel);
        }
    }

    //4 添加讲师
    @ApiOperation("添加讲师")
    @PostMapping("saveTeacher")
    public Result saveTeacher(@RequestBody @Validated(AddGroup.class) Teacher teacher) {
        Teacher teacherOne = teacherService.getOne(new LambdaQueryWrapper<Teacher>()
                .eq(Teacher::getName, teacher.getName()), false);

        if (!ObjectUtils.isEmpty(teacherOne)) {
            return Result.fail("讲师"+teacher.getName()+"已存在");
        }

        boolean isSuccess = teacherService.save(teacher);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //5 修改-根据id查询
    @ApiOperation("根据id查询")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return Result.ok(teacher);
    }

    //6 修改-最终实现
    // {...}
    @ApiOperation("修改最终实现")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody @Validated(UpdateGroup.class) Teacher teacher) {
        boolean isSuccess = teacherService.updateById(teacher);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //7 批量删除讲师
    // json数组 [1,2,3]
    @ApiOperation("批量删除讲师")
    @DeleteMapping("removeBatch")
    public Result removeBatch(@RequestBody List<Long> idList) {
        boolean isSuccess = teacherService.removeByIds(idList);
        if (isSuccess) {
            return Result.ok(null);
        } else {
            return Result.fail(null);
        }
    }

    //根据id查询 远程调用
    @ApiOperation("根据id查询")
    @GetMapping("inner/getTeacher/{id}")
    public Teacher getTeacherInfo(@PathVariable Long id) {
        Teacher teacher = teacherService.getById(id);
        return teacher;
    }
}


