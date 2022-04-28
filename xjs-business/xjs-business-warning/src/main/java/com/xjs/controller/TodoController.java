package com.xjs.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.xjs.domain.todo.TodoCategory;
import com.xjs.service.TodoCateGoryService;
import com.xjs.validation.group.AddGroup;
import com.xjs.validation.group.UpdateGroup;
import com.xjs.web.MyBaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 待办控制器
 *
 * @author xiejs
 * @since 2022-04-28
 */
@RestController
@RequestMapping("todo")
@Api(tags = "业务模块-待办事项")
public class TodoController extends MyBaseController<TodoCategory> {

    @Autowired
    private TodoCateGoryService todoCateGoryService;

    @PostMapping("category/add")
    @ApiOperation("添加待办分类")
    @RequiresPermissions("todo:category:add")
    @Log(title = "添加待办分类", businessType = BusinessType.INSERT)
    public AjaxResult addTodoCategory(@RequestBody @Validated(AddGroup.class) TodoCategory todoCategory) {
        return toAjax(todoCateGoryService.save(todoCategory));
    }

    @GetMapping("category/list")
    @ApiOperation("查询所有待办分类")
    @RequiresPermissions("todo:category:list")
    public AjaxResult listTodoCategory() {
        LambdaQueryWrapper<TodoCategory> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(TodoCategory::getSort);
        return AjaxResult.success(todoCateGoryService.list(wrapper));
    }

    @DeleteMapping("category/remove/{id}")
    @ApiOperation("删除待办分类")
    @RequiresPermissions("todo:category:remove")
    @Log(title = "删除待办分类", businessType = BusinessType.DELETE)
    public AjaxResult removeTodoCategory(@PathVariable String id) {
        return toAjax(todoCateGoryService.removeById(id));
    }

    @PutMapping("category/edit")
    @ApiOperation("修改待办分类")
    @RequiresPermissions("todo:category:edit")
    @Log(title = "修改待办分类", businessType = BusinessType.UPDATE)
    public AjaxResult editTodoCategory(@RequestBody @Validated(UpdateGroup.class) TodoCategory todoCategory) {
        return toAjax(todoCateGoryService.updateById(todoCategory));
    }
}
